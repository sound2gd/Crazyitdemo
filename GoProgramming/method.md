# go中的函数

有`Receiver`的是方法，没有`Receiver`的是函数, 
`Receiver`就是`func`关键字和函数方法名之间的那一段参数


```golang
// 函数
func main() {
}

type Person struct{
  name string
}

// 方法, Expoted
func (p Person) Rename(name string) {
}
```

首字母大写的则为 `Expoted`，可以被别的包使用

Golang有2种类型的`Receiver`:

1. Value Receiver, 值接收者
2. Pointer Receiver, 指针接收者

使用值类型接收者定义的方法，在调用的时候，使用的其实是接收者的一个副本，
对该值的任何操作，不会影响原来的类型变量

```golang
type Person struct{
  Name string
}

// 方法, Expoted
func (p Person) Rename(name string) {
  // 不会修改原有对象
  p.Name = "Mark";
}

func (p *Person) Rename2(name string) {
  // 修改原有对象
  p.Name = "Mark";
}

```


> 在调用的方法的时候，传递的Receiver本质上都是值的拷贝，只不过一个是对象数据的拷贝，一个是对象指针的拷贝,指针里存储的原有对象的地址，所以修改的时候也修改到了原来的对象

**golang里方法的调用，既可以使用值， 也可以使用指针**。编译器会自动作处理

## 可变参数var args

函数方法的参数，可以是任意多个, 但是只能放在最后一个参数上, 定义的方法为

```golang
func awesomeFunc(a,b int, c string, others ...interface{}) {
}
```
