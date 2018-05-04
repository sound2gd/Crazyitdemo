package main

import (
	"fmt"
	"reflect"
)

type User struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func (user User) Print(prefix string) {
	fmt.Printf("%s: Name is %s, Age is %d\n", prefix, user.Name, user.Age)
}

func main() {
	u := User{"Chris", 18}
	t := reflect.TypeOf(u)
	fmt.Println(t)

	v := reflect.ValueOf(u)
	fmt.Println(v)
	fmt.Printf("%T\n", u)
	fmt.Printf("%v\n", u)

	u1 := v.Interface().(User)
	fmt.Println(u1)

	t1 := v.Type()
	fmt.Println(t1)

	// 获取底层类型
	k := t.Kind()
	fmt.Println(k) // struct

	// 获取结构体导出的字段和方法
	for i := 0; i < t.NumField(); i++ {
		fmt.Println(t.Field(i).Name)
	}

	for i := 0; i < t.NumMethod(); i++ {
		fmt.Println(t.Method(i).Name)
	}

	x := 2
	vx := reflect.ValueOf(&x)
	vx.Elem().SetInt(100)
	fmt.Println(x)

	mPrint := v.MethodByName("Print")
	if mPrint.IsValid() {
		args := []reflect.Value{reflect.ValueOf("前缀")}
		fmt.Println(mPrint.Call(args))
	}

	// 获取tag
	for i := 0; i < t.NumField(); i++ {
		f := t.Field(i)
		fmt.Println(f.Name)
		fmt.Println(f.Tag)
		fmt.Println(f.Tag.Get("json"))
	}
}
