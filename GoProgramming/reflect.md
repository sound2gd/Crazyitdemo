# go反射

在go的反射定义中，任何接口都会有2部分组成，一个是接口的动态类型，一个是接口的值
`interface{}`类型可以表示任何类型， 类似于`java.lang.Object`

`reflect.Value`表示值，`reflect.Type`表示类型, 提供2个函数来获取任意对象的`Value`和`Type`, 分别是:

1. reflect.TypeOf
2. reflect.ValueOf

具体可以看看[reflectbase.go](reflectbase.go)


## 逆转

从`reflect.Value`转换回原有类型，先转成interface{}然后再使用类型断言
转回`Type`, 直接使用`v.Type()`就可以

```golang
    u1 := v.Interface().(User)
	t1 := v.Type()
```


## 底层类型

基础类型，接口，结构体，指针这些，可以通过`reflect.Type`的`Kind()`方法获取到

go提供了下面的基础类型

```golang
const (
	Invalid Kind = iota
	Bool
	Int
	Int8
	Int16
	Int32
	Int64
	Uint
	Uint8
	Uint16
	Uint32
	Uint64
	Uintptr
	Float32
	Float64
	Complex64
	Complex128
	Array
	Chan
	Func
	Interface
	Map
	Ptr
	Slice
	String
	Struct
	UnsafePointer
)
```


## 字段和方法, 修改字段值, 动态调用方法

反射可以获取一个结构体类型的字段以及导出方法

```golang
	// 获取结构体导出的字段和方法
	for i := 0; i < t.NumField(); i++ {
		fmt.Println(t.Field(i).Name)
	}

	for i := 0; i < t.NumMethod(); i++ {
		fmt.Println(t.Method(i).Name)
	}
```

ValueOf获取的是值的拷贝，修改值需要传入变量地址, 然后使用`Elem`方法找到这个指针指向的值
`CanSet`可以判断是否允许修改该对象


动态调用方法也是一样的，使用`reflect.Value`的`MethodByName`就可以拿到方法,然后使用`IsValid`判断是否合法，最后调用

```golang
	mPrint := v.MethodByName("Print")
	if mPrint.IsValid() {
		args := []reflect.Value{reflect.ValueOf("前缀")}
		fmt.Println(mPrint.Call(args))
	}
```


## 反射获取tag

这可能是最实用的反射了，各种ORM框架都是靠这个，估计go里面会tag爆炸
字段的tag是标记在字段上的，所以要先获取字段，再获取字段上的tag

```golang
	// 获取tag
	for i := 0; i < t.NumField(); i++ {
		f := t.Field(i)
		fmt.Println(f.Tag)
	}

```

tag可以是键值对，可以用`StructTag`的`Get`方法直接取
多个key以多个空格分开

