package main

import (
	"fmt"
)

func main() {
	var c cat
	// 值作为参数传递
	// 以下都可以执行
	invoke(&c)
	invoke(c)

	// 指针接收者会报错
}

func invoke(a animal) {
	a.printInfo()
}

type animal interface {
	printInfo()
}

type cat int

// 值接收者
func (c cat) printInfo() {
	fmt.Println("a cat")
}

// 指针接收者
// func (c *cat) printInfo() {
// 	fmt.Println("a cat")
// }
