# Java 集合概述

## 集合概述

- 集合实际上是一个容器，用来容纳其他类型的数据
- 集合里存储的是对象的引用，而不是基本数据类型
- 每个不同的集合，底层会对应不同的数据结构，使用不同的集合，相当于使用了不同的数据结构
- 学习 Java 集合，学习的是掌握这些常用的集合类，以及在什么情况下用什么集合
- 所有的集合类和集合接口，都在 java.util 包下

## 基本框架

![集合框架图](../picture\1010726-20170621004734695-988542448.png)	

### Iterable 接口

超级父接口，所有集合都实现了该接口，表示集合元素都是可迭代的，可遍历的。

### Iterator 接口

Iterator接口， 集合的==迭代器对象==。这是一个用于==遍历==集合中元素的接口，主要包含 hashNext()，next()，remove() 三种方法。它的一个子接口 LinkedIterator 在它的基础上又添加了三种方法，分别是 add()，previous()，hasPrevious()。也就是说如果是先Iterator接口，那么在遍历集合中元素的时候，只能往后遍历，被遍历后的元素不会在遍历到，通常无序集合实现的都是这个接口，比如 HashSet，HashMap；而那些元素有序的集合，实现的一般都是 LinkedIterator 接口，实现这个接口的集合可以双向遍历，既可以通过next()访问下一个元素，又可以通过previous()访问前一个元素，比如 ArrayList。

### ==Collection 接口==

超级父接口，继承了 Collection 接口的以单个方式储存元素，类似于数组、链表的数据结构

Collection 集合框架的共性功能

| 操作类型 | 添加                        | 删除                                         | 判断                     | 获取                 | 获取交集     | 集合变数组 |
| -------- | --------------------------- | -------------------------------------------- | ------------------------ | -------------------- | ------------ | ---------- |
| 函数     | add(e); addAll(collection); | remove(e);  removeAll(collection);  clear(); | contains(e);  isEmpty(); | iterator();  size(); | retainAll(); | toArray(); |

add(e) 方法的参数类型为 Object ，用来接收任意 Collection 集合

#### List 接口

List 集合存储元素特点：有序、可重复、存储的元素有下标。（有序不是指按照大小排序，而是存进去是什么顺序，拿出来就是什么顺序，不会被打乱）

List 集合框架的共性功能

| 操作类型 | 添加                                             | 删除           | 修改                 | 查找                                              |
| -------- | ------------------------------------------------ | -------------- | -------------------- | ------------------------------------------------- |
| 函数     | add(index, element);  addAll(indext, collecion); | remove(index); | set(index, element); | get(indext);  subList(from, to);  listIterator(); |

常用子类：

- ArrayList	数组的数据结构，非线程安全，线程不安全，查询速度快
- LinkedList  双向链表的数据结构，增删速度快
- Vector        数组的数据结构，线程安全，查询速度慢，效率低，有别的方案替代，使用较少


#### Set 接口

Set 集合存储元素特点：不可重复、存储的元素没有下标

常用子类：

- HashSet		底层数据结构为哈希表，线程不安全，不同步；实例化的时候生成的是个 HashMap；无序
- TreeSet		 底层数据结构为二叉排序树，有序的存放，可以对集合中的元素进行排序

### ==Map 接口==

超级父接口，继承了 Map 接口的以键值对（key 和 value）的方式来存储元素，比如哈希表

- HashMap		底层是哈希表数据结构，允许使用 null 值和 null 键，该集合是不同步的；将hashtable替代
- TreeMap		底层是二叉树数据结构；线程不同步；可以用于给map集合中的键进行排序

## 总结

通常常用的集合都是线程不安全的。因为要提高效率。如果多线程操作这些集合时，可以通过该工具类中的同步方法，将线程不安全的集合，转换成安全的。

List：add/remove/get/set。

　　　　1，ArrayList：其实就是数组，容量一大，频繁增删就是噩梦，适合随机查找；

　　　　2，LinkedList：增加了push/[pop|remove|pull]，其实都是removeFirst；

　　　　3，Vector：历史遗留产物，同步版的ArrayList，代码和ArrayList太像；

　　　　4，Stack：继承自Vector。Java里其实没有纯粹的Stack，可以自己实现，用组合的方式，封装一下LinkedList即可；

　　　　5，Queue：本来是单独的一类，不过在SUN的JDK里就是用LinkedList来提供这个功能的，主要方法是offer/pull/peek，因此归到这里呢。

　　Set：add/remove。可以用迭代器或者转换成list。

　　　　1，HashSet：内部采用HashMap实现的；

　　　　2，LinkedHashSet：采用LinkedHashMap实现；

　　　　3，TreeSet：TreeMap。

　　Map：put/get/remove。

　　　　1，HashMap/HashTable：散列表，和ArrayList一样采用数组实现，超过初始容量会对性能有损耗；

　　　　2，LinkedHashMap：继承自HashMap，但通过重写嵌套类HashMap.Entry实现了链表结构，同样有容量的问题；

　　　　3，Properties：是继承的HashTable。

## 其他

### HashMap 和 HashSet 的区别

 HashSet：

​		HashSet实现了Set接口，它不允许集合中出现重复元素。当我们提到HashSet时，第一件事就是在将对象存储在

HashSet之前，要确保重写hashCode（）方法和equals（）方法，这样才能比较对象的值是否相等，确保集合中没有

储存相同的对象。如果不重写上述两个方法，那么将使用下面方法默认实现：

​		public boolean add(Object obj)方法用在Set添加元素时，如果元素值重复时返回 "false"，如果添加成功则返回"true"

HashMap：

　　HashMap实现了Map接口，Map接口对键值对进行映射。Map中不允许出现重复的键（Key）。Map接口有两个基本的实现

TreeMap和HashMap。TreeMap保存了对象的排列次序，而HashMap不能。HashMap可以有空的键值对（Key（null）-Value（null））

HashMap是非线程安全的（非Synchronize），要想实现线程安全，那么需要调用collections类的静态方法synchronizeMap（）实现。

public Object put(Object Key,Object value)方法用来将元素添加到map中。

区别示意表：

| HashMap                                                | HashSet                                                      |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| 实现了Map接口                                          | 实现Set接口                                                  |
| 存储键值对                                             | 仅存储对象                                                   |
| 调用put（）向map中添加元素                             | 调用add（）方法向Set中添加元素                               |
| HashMap使用键（Key）计算Hashcode                       | HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false |
| HashMap相对于HashSet较快，因为它是使用唯一的键获取对象 | HashSet较HashMap来说比较慢                                   |