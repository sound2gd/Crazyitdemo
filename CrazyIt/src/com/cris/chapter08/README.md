## 第八章　Java集合


### 8.1　集合体系
Java中的集合分两种:Collection和Map。
他俩都是接口，是最上层的存在，所有的集合类，大都是这两个接口的实现类。

Collection的子接口包括Set,List,Queue。

Set,List,Queue,Map就是Java中最常用的集合，也是最常用的数据结构
常用的实现类有:HashSet,ArrayList,ArrayDeque,LinkedList,HashMap,TreeMap

### 8.2　常用集合
Set中常用的集合有:HashSet(最常用),LinkedHashSet,TreeSet,EnumSet
List中常用的集合有:ArrayList,LinkedList
Queue常用的集合有:PriorityQueue,ArrayDeque,LinkedList
Map常用的集合有:HashMap,LinkedHashMap,TreeMap,EnumMap,WeakHashMap,IdentityHashMap

#### 8.2.1 Set
Set是一种不可重复，无序的集合。
HashSet是根据存放元素的Hash码来确定位置的，内部是使用HashMap的keySet。
通常来说，HashSet是首选的Set集合，其为查找而设计，性能相对较高。但是不会保存元素的插入顺序。
HashSet判断两个元素是否相等的标准是:equals返回true并且hashCode相等

LinkedHashSet会保存元素的插入顺序，所以性能相较于HashSet差了一点。其内部元素的
插入顺序使用一个链表来维护。

TreeSet是排序后的Set，其内部元素默认会按照升序来排序。TreeSet中的元素必须实现Comparable
接口，因为往TreeSet存取元素的时候，确定元素的位置需要调用compareTo方法。同理，不能放null元素。
TreeSet内部采用红黑树来存放元素。TreeSet内部元素判断相等的唯一规则是:compareTo方法返回０

EnumSet是枚举Set,其内部元素只能是枚举值，是Set中性能最高了。EnumSet没有构造器，
其只能通过EnumSet的静态方法来创建实例。

#### 8.2.2 List
List是有序，可重复的集合。

ArrayList内部使用数组来保存集合元素,在随机查找元素的时候，ArrayList的性能是最好的。
ArrayList判断元素相等的规则是equals方法返回true

LinkedList不仅实现了List接口，还实现了Deque接口，所以它既可以当成List使用，有可以当成
队列，栈，双端队列使用。

#### 8.2.3 Queue
队列是一种先进先出FIFO的数据结构,栈是一种先进后出FILO的数据结构。
在Java语言中，栈的实现有Stack，ArrayDeque等，但是不推荐用Stack,其父类是Vector。
这是一个自JDK1.0就有的类，非常古老，而且设计的也不好。

Queue最常用的子接口是Deque。
在Java中要使用栈推荐使用Deque的实现类,如ArrayDeque，它既可以当做队列使用，也可以当成栈使用。

PriorityQueue是Queue的一个实现类，但是它不是一个标准的队列，元素放入其中会排序。
PriorityQueue的注意细节和TreeSet一样。
排序的规则默认是自然排序法，但是可以自定义排序规则，JAVA8可以很方便的使用lambda来设置排序规则。

Deque是代表双端队列的一种数据结构，数据既可以从队首插入，也可以从队尾插入
ArrayDeque是Deque的一个常用实现，它既可以当做队列使用，又可以当做栈使用。
其内部使用的是数组来存放元素

LinkedList是Deque的另一个常用实现，它也是List的常用实现。
LinkedList内部使用链表来存储数据。在遍历，插入，删除的时候性能比较高。
LinkedList既可以当做队列使用，也可以当成栈使用，还可以当成List使用

#### 8.2.4 Map
Map是一种保存有映射关系的两组数据的集合。

HashMap是最常用的Map实现，其性能比较高，HashMap根据key的hashCode来存放元素，其内部是
存放的Entry数组，Entry是键值对的数据结构。当Hash槽没有碰撞的时候，HashMap的性能是最高的
如果发生了Hash碰撞，那么会以链表的形式存储多余的Entry
HashMap判断两个元素是否相等的标准和HashSet一样。HashSet是HashMap的keySet()

LinkedHashMap是保存了元素插入顺序的HashMap,它内部以链表的形式来维护键值对的插入顺序
所以性能比HashMap略低。

TreeMap是内部对key进行排序的Map，默认是按照升序排列的自然排序法。
TreeMap内部元素key是否相等的标准和TreeSet一样，value是否相等的规则就是equals方法返回true

EnumMap的键只能是枚举类型，是性能最高的Map

WeakHashMap的键都是弱引用，一旦发生gc，key都会被回收。不要用有强引用的对象来作为key，否则WeakHashMap就失去了意义

IdentityHashMap和HashMap的性能差不多，只是判断key元素是否相等的标准除了HashMap的标准外还要用`==`操作符判断。 obj1 == obj2;


### 8.3　性能考虑
#### 8.3.1 Set性能






