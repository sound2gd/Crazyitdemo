# 复合数据类型

以前写java的时候，java里只有2种数据类型：`基础数据类型`和`引用数据类型`
golang里也差不多，叫`复合数据类型`,主要包括4种

- array
- slice
- map
- struct

数组是由同类型的元素组成, 结构体可以由不同的类型组成。它们都是固定内存大小的数据结构
slice和map是动态的数据结构

## 数组

数组是由固定长度的同类元素组成的序列，一个数组可以由0个或者多个元素组成。
和数组对应的类型是slice(切片)，切片是可以增长和收缩的动态序列,理解slice的前提是要先理解数组

### 数组创建

直接声明一个数组就可以创建, 默认会用对应类型的初始化值来填充

```golang
var a [3]int // 长度为3的int数组
```

也可以使用字面量语法来初始化数组

```golang
var q [3]int = [3]int{1, 2, 3}
var r [3]int = [3]int{1, 2} // 指定俩，最后让它默认初始化
```

如果要根据初始化值的个数来计算长度，可以使用

```golang
q := [...]int{1, 2, 3}
fmt.Printf("%T\n", q)
```

**数组长度是数组类型的一部分**, `[3]int`和`[4]int`是两种不同的数组类型

```golang
q := [3]int{}
q = [4]int //cannot use [4]int literal (type [4]int) as type [3]int in assignment
```

使用索引和对应值的列表初始化数组

```golang
r := [...]int{99: -1} // 定义1个100个元素的数组，前99个用0初始化，最后一个用-1初始化
```

如果一个数组的元素类型是可以相互比较的，那么数组类型也是可以相互比较的。这时候我们可以直接通过`==`来比较2个数组

```golang
a := [2]int{1, 2}
b := [...]int{1, 2}
c := [2]int{1, 3}
fmt.Println(a == b, a == c, b == c) // true false false

d := [3]int{1, 2}
fmt.Println(a == d) // invalid operation: a == d (mismatched types [2]int and [3]int)
```

### 数组小结

通过指针来传递数组参数是高效的，但是数组不好操作元素，长度也是固定的，不适合作为函数参数
一般会用slice来代替


## Slice

Slice(切片)代表可以变长的序列, 序列中每个元素都有相同的类型. Slice是一种轻量级的数据结构，提供了访问数组子序列元素
的功能。slice底层引用一个数组对象。

Slice由三个部分组成:`指针`,`长度`,`容量`
指针指向第一个Slice元素对应底层数组元素的地址
`len`和`cap`可以分别返回slice的长度和容量

通过slice[m:n]可以获取子序列
```golang
a := []string{1:"Jan", 12: "Dec"}
b := a[3:5]
```

**子序列元素改变，父序列会跟着改**

Slice之间不能相互比较

添加元素用append
```golang
var runes []rune
for _, r := range "Hello World" {
  runes = append(runes, r)
}
fmt.Printf("%q \n", runes) // ['H' 'e' 'l' 'l' 'o' ' ' 'W' 'o' 'r' 'l' 'd']
```

## Map

go语言中map是一个哈希表的引用, key必须是支持`==`比较运算符的数据类型

### 创建map

```golang
ages := make(map[string]int) // 定义 k/v 为 string int的map
```

也可以使用字面量语法创建map,指定初始的kv

```golang
ages := map[string]int{
  "alice": 31,
  "charlie": 34,
}
// 相当于
ages := make(map[string]int)
ages["alice"]=31
ages["charlie"]=34
```

delete可以删除元素

```golang
delete(ages, "alice")
```

在向map中存数据之前必须先创建map, 通过key做索引下标来访问map将产生一个value,
如果key在map中是存在的，那么将得到与key对应的value,如果key不存在，那么将会得到value对应的零值

```golang
age, ok := ages["bob"]
if !ok {} 

// 合起来写
if age, ok := ages["bob"]; !ok {}
```

golang中没有set，只能用map来模拟set

## 结构体

结构体是一种由任意类型数据聚合的实体。每个值称为结构体的成员。

字面量表示

```golang
type Point struct{x, y int}

p := &Point{} // 初始化结构体，返回指针
p = &Point{x: 1, y: 2} // 带名字初始化结构体
p = &Point{1, 2} //得记住结构体成员顺序
```

如果结构体的全部成员是可以比较的，那么结构体也是可以比较的。

```golang
p := Point{1, 2}
q := Point{2, 1}
fmt.Println(p == q) // false
```

go语言有个特性让我们只声明一个成员对应的数据类型而不用指定成员的名字,这类成员就叫`匿名成员`.

```golang
type Circle struct{
  Point
  Radius int
}
```

这也是go语言实现继承的方法。

## JSON支持

go语言提供了json包，把结构体转换成json字符串的过程叫做`编组(Marshaling)`, 编码的逆操作是解码，go语言里称为`unmarshaling`

```golang
type Movie struct {
    Title  string
    Year   int  `json:"released"`
    Color  bool `json:"color,omitempty"`
    Actors []string
}

var movies = []Movie{
    {Title: "Casablanca", Year: 1942, Color: false,
        Actors: []string{"Humphrey Bogart", "Ingrid Bergman"}},
    {Title: "Cool Hand Luke", Year: 1967, Color: true,
        Actors: []string{"Paul Newman"}},
    {Title: "Bullitt", Year: 1968, Color: true,
        Actors: []string{"Steve McQueen", "Jacqueline Bisset"}},
    // ...
}

data, err := json.Marshal(movies)
if err != nil {
    log.Fatalf("JSON marshaling failed: %s", err)
}
fmt.Printf("%s\n", data)
```

缩进可以使用MarshalingIntent
结构体成员的Tag可以在编译阶段关联到该成员的元信息字符串
