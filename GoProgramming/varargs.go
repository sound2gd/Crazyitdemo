package main

import (
	"fmt"
)

func main() {
	print("a", 1, "c", "d", "e")
}

func print(a string, b int, c ...interface{}) {
	fmt.Println(a, b)
	for _, v := range c {
		fmt.Println(v)
	}
}
