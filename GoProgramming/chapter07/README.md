# 第七章 接口

接口类型是对其他类型行为的抽象和概括。go语言的接口类型满足隐式实现，没有必要对于给定的具体类型定义所有满足的接口类型

## 1. 约定

接口类型代表一种抽象的类型，不会暴露出所代表的对象的内部值的结构和这个对象支持的基础操作的集合, 只会暴露出自己的方法。

## 2. 接口类型

接口类型描述了一系列方法的集合，一个实现了这个方法的具体类型的实例

io.Writer类型是用的最广泛的接口之一，因为它提供了所有的类型写入`bytes`的抽象，包括

- 文件类型
- 内存缓冲区
- 网络连接
- Http客户端
- 压缩工具
- 哈希

`Closer`可以是任意可以关闭的值，例如一个文件或者是网络连接

```golang
package io

type Reader interface {
  Read(p []byte) (n int, err error)
}
type Closer interface {
  Close() error
}
```

有些新接口通过`接口内嵌`来定义

```golang
type ReadWriter interface {
 Reader
 Writer
}

type ReadWriteCloser interface {
 Reader
 Writer
 Closer
}
```

## 3. 实现接口的条件

如果一个类型拥有了接口锁需要的所有方法，那么这个类型就实现了这个接口

interface{}类型称为`空接口类型`
空接口类型对于它的实现类行没有要求，所以我们可以将任意一个值赋给空接口类型

```golang
var any interface{}
any = true
any = 12.34
any = map[string]int{"one": 1}
any = new(bytes.Buffer)
```

每一个具体类型的组基于它们相同的行为可以表示成一个接口类型。不像基于类的语言，他们一个类实现的接口集合需要进行显式的定义，在Go语言中我们可以在需要的时候定义一个新的抽象或者特定特点的组，而不需要修改具体类型的定义。


## 4. 接口值

一个接口的值由俩部分组成

1. 动态类型, 也就是`类型描述符`
2. 动态值, 基于动态类型描述为`空`或者`非空`, **调用值为nil的接口上的任意方法都会导致panic**

![](https://yar999.gitbooks.io/gopl-zh/content/images/ch7-01.png)

类型是编译期的概念，类型信息的值称为`类型描述符`, 比如类型的名称和方法
一个接口上的调用必须是动态分配，因为我们不能在编译期确定接口的值的动态类型.
编译器会把代码生成在类型描述符方法上的接口方法上，然后间接调用那个地址.

### 接口值比较

接口值可以和nil比较
接口值之间能比较的前提是，他们的动态类型相同并且动态之也根据这个动态类型`==`相等

```golang
var x interface{} = []int{1, 2, 3}
x == nil // 可以比较
x == x // slice之间不能比较,会报错
```

**警告：一个包含nil指针的接口不是nil接口**


## 5. 常用接口
### 5.1 sort.Interface

排序接口需要3个方法, 分别代表序列的长度, 相邻俩元素的比较结果， 交换俩元素的方式

```golang
type Interface interface{
  Len() int
  Less(i, j int) bool // i和j是序列的元素
  Swap(i, j int)
}
```

示例实现:

```golang
type Track struct {
    Title  string
    Artist string
    Album  string
    Year   int
    Length time.Duration
}

var tracks = []*Track{
    {"Go", "Delilah", "From the Roots Up", 2012, length("3m38s")},
    {"Go", "Moby", "Moby", 1992, length("3m37s")},
    {"Go Ahead", "Alicia Keys", "As I Am", 2007, length("4m36s")},
    {"Ready 2 Go", "Martin Solveig", "Smash", 2011, length("4m24s")},
}

func length(s string) time.Duration {
    d, err := time.ParseDuration(s)
    if err != nil {
        panic(s)
    }
    return d
}
```

为了使用方便，golang默认实现了`[]int`,`[]string`, `[]float64`的排序

### 5.2 http.Handler



```golang
package http

type Handler interface {
    ServeHTTP(w ResponseWriter, r *Request)
}

func ListenAndServe(address string, h Handler) error
```

### 5.3 error接口

```golang
type error interface {
    Error() string
}
```

一般也很少会用`errors.New`, 有一个`fmt.Errorf`

```golang
package fmt

import "errors"

func Errorf(format string, args ...interface{}) error {
    return errors.New(Sprintf(format, args...))
}
```

## 6. 类型断言

类型断言是一个使用在接口值上的操作，语法类似 x.(T) 看起来有点像java的强转，
如果断言的类型T是一个具体类型，类型断言检查x的动态类型是否和T相同，如果检查成功了，类型断言的结果是x的动态值
如果检查失败，这个操作会抛出panic

```golang
var w io.Writer
w = os.Stdout
f := w.(*os.File)      // success: f == os.Stdout
c := w.(*bytes.Buffer) // panic: interface holds *os.File, not *bytes.Buffer
```

第二种，如果相反地断言的类型T是一个接口类型，然后类型断言检查是否x的动态类型满足T,
如果这个检查成功了，动态值没有获取到，这个结果仍然是一个有相同类型和值部分的接口值，但是结果类型为T

```golang
var w io.Writer
w = os.Stdout
rw := w.(io.ReadWriter) // success: *os.File has both Read and Write
w = new(ByteCounter)
rw = w.(io.ReadWriter) // panic: *ByteCounter has no Read method
```

类型断言有些好处，可以拿来判断传入的类型是不是某个类型

```golang
if sw, ok := w.(stringWriter); ok {
        return sw.WriteString(s) // avoid a copy
}
```

## 小结

go语言对于抽象的时机，是2个以上的地方用到同一种处理方式的时候才抽象，避免过度设计
