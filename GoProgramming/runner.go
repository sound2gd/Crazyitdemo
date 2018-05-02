package main

import (
	"errors"
	// "fmt"
	"log"
	"os"
	"os/signal"
	"time"
)

var (
	ErrTimeout   = errors.New("执行者执行超时")
	ErrInterrupt = errors.New("执行者被中断")
)

type Runner struct {
	tasks     []func(int)
	complete  chan error
	timeout   <-chan time.Time
	interrupt chan os.Signal
}

func New(tm time.Duration) *Runner {
	return &Runner{
		complete:  make(chan error),
		timeout:   time.After(tm),
		interrupt: make(chan os.Signal, 1),
	}
}

func (r *Runner) Add(tasks ...func(int)) {
	r.tasks = append(r.tasks, tasks...)
}

func (r *Runner) run() error {
	for id, task := range r.tasks {
		if r.isInterrupt() {
			return ErrInterrupt
		}
		task(id)
	}
	return nil
}

func (r *Runner) isInterrupt() bool {
	select {
	case <-r.interrupt:
		signal.Stop(r.interrupt)
		return true
	default:
		return false
	}
}

func (r *Runner) Start() error {
	signal.Notify(r.interrupt, os.Interrupt)

	go func() {
		r.complete <- r.run()
	}()

	select {
	case err := <-r.complete:
		return err
	case <-r.timeout:
		return ErrTimeout
	}
}

func main() {
	log.Println("开始执行任务")

	timeout := 3 * time.Second
	r := New(timeout)

	r.Add(createTask(), createTask(), createTask(), createTask(), createTask())
	if err := r.Start(); err != nil {
		switch err {
		case ErrTimeout:
			log.Println(err)
			os.Exit(1)
		case ErrInterrupt:
			log.Println(err)
			os.Exit(2)
		}
	}

	log.Println("...任务执行结束...")
}

func createTask() func(int) {
	return func(id int) {
		log.Printf("正在执行任务%s\n", id)
		time.Sleep(time.Duration(id) * time.Second)
	}
}
