# Java 集合 迭代器

Iterator it = c.iterator()

只能用于 Collection 接口所衍生出的集合类

```java
public class CollectionDemo1 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add(100);
        c.add("abc");
        c.add("efg");
        // 第一步：获取集合对象的迭代器对象Iterator
        Iterator it = c.iterator();
        // 通过 hasNest() 方法来判断集合里是否还有未遍历的数据
        while (it.hasNext()) {
            System.out.println(it.next());  // 通过 next() 方法来获取下一个元素
        }
    }
}
```

