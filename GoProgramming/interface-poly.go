package main

import (
	"fmt"
)

type animal interface {
	printInfo()
}

type dog int
type cat int

func (d dog) printInfo() {
	fmt.Println("a dog")
}

func (c cat) printInfo() {
	fmt.Println("a cat")
}

func invoke(a animal) {
	a.printInfo()
}

func main() {
	// 示例用golang实现运行时多态
	var a animal

	var c cat
	a = c
	a.printInfo()

	var d dog
	a = d
	a.printInfo()
}
