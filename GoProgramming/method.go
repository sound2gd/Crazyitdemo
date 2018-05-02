package main

import (
	"fmt"
)

type Persion struct {
	name string
}

func main() {
	p := Persion{name: "Cris"}
	p.Rename("Mark")
	// (&p).Rename("Mark")
	fmt.Println(p.name)
	// Output: Cris
}

func (p *Persion) Rename(s string) {
	p.name = s
}
