# ArrayList 类

## 基本概念

ArrayList 是Java 函数库中的一个类，不是数组，常常用来进行数组操作

## 基础操作

```java
// 创建
ArrayList<Integer> myList = new ArrayList<Integer>();  // <> 里的内容为你要创建的 ArrayList 数组的基本元素是哪个对象

// 加入元素  add()
int i = 0;
myList.add(i);

// 删除元素  remove()
myList.remove(s);

// 查询大小  size()
int theSize = myList.size();

// 查询特定元素  contain()
boolean isIn = myList.contains(i);  // 返回值为 true 或 false

// 查询特定元素的位置  indexOf
int idx = myList.indexOf(a);  // 相当于返回数组下标

// 判断集合是否为空  isEmpty()
boolean empty = myList.isEmpty();  // 返回值为 true 或 false
```

## 基础特性

- ArrayLIst 可以将引用删除掉，并动态的改变大小
- 创建的时候不需要固定大小
- 和数组不一样，没有特殊语法[ ]