# 序列化

## 概述

Java 提供了一种**对象序列化**的机制；用一个字节序列可以表示一个对象，该字节对象包含`对象的数据`、`对象的类型`、`对象中存储的属性`等信息

反之，可以从文件中读取这种字节序列，重构对象，对其进行**反序列化**

序列化和反序列话的时候，如果抛出`NotSerializableException`则代表该类没有实现序列化接口

要进行序列化和反序列话的类，必须实现`Serializable`接口

## ObjectOutputStream

`java.io.ObjectOutputStream`类，将 Java 对象的原始数据类型写出到文件，实现对象的持久储存

### 构造方法

- `public ObjectOutputStream(OutputStream out)`：创建一个指定`OutputStream`的`ObjectOutputStream`

例如

```java
FileOutputStream fos = new FileOutputStream("src\\a.txt");
ObjectOutputStream oos=new ObjectOutputStream(fos);
```

### 特有的成员方法

`public void writeObject(Object obj)`：将指定的对象写入 `ObjectOutputStream`

### 实例

```java
public class IOTest {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\a.txt"))) {
            oos.writeObject(new People(17, "好家伙"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class People implements Serializable{  // 实现序列化接口
    int age;
    String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
```

