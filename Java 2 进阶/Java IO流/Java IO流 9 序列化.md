# 序列化

## 1.1 概述

Java 提供了一种**对象序列化**的机制；用一个字节序列可以表示一个对象，该字节对象包含`对象的数据`、`对象的类型`、`对象中存储的属性`等信息

反之，可以从文件中读取这种字节序列，重构对象，对其进行**反序列化**

## 2.1 序列化操作

- 要进行序列化和反序列话的类，必须**实现`Serializable`接口**
- 序列化和反序列话的时候，如果抛出`NotSerializableException`则代表该类没有实现序列化接口
- 在类中写入**`serialVersionUID`**
- 该类的所有属性必须是可序列化的；如果有一个属性不需要可序列化的，则该属性必须注明是瞬态的，使用**`transient`**关键字修饰
- 被 static 修饰的成员变量不能被序列化，序列化的都是对象



### 2.2 **serialVersionUID**

#### 2.2.1 概述

`serialVersionUID`适用于 Java 序列化机制。简单来说，Java 序列化的机制是通过 判断类的`serialVersionUID`来验证的版本一致的。在进行反序列化时，JVM 会把传来的字节流中的`serialVersionUID`与本地相应实体类的serialVersionUID进行比较。如果相同说明是**一致的，可以进行反序列化**，否则会出现反序列化**版本不一致的异常**，即是`InvalidCastException`。

即，声明`SerialVersionUID`可以**避免对象的不一致**

#### 2.2.2 serialVersionUID有两种显示的生成方式

一、默认的1L，比如：private static final long serialVersionUID = 1L;    

二、根据包名，类名，继承关系，非私有的方法和属性，以及参数，返回值等诸多因子计算得出的，极度复杂生成的一个64位的哈希字段。基本上计算出来的这个值是唯一的。比如：private static final long serialVersionUID = xxxxL;

**`serialVersionUID`必须是静态的（static）、最终的（fianl）， long 型字段显示声明**

### 2.3 transient  瞬态关键字

被 transient 修饰的成员变量不能被序列化



## 3.1 ObjectOutputStream

`java.io.ObjectOutputStream`类，将 Java 对象的原始数据类型写出到文件，实现对象的持久储存

### 3.2 构造方法

- `public ObjectOutputStream(OutputStream out)`：创建一个指定`OutputStream`的`ObjectOutputStream`

例如

```java
FileOutputStream fos = new FileOutputStream("src\\a.txt");
ObjectOutputStream oos=new ObjectOutputStream(fos);
```

### 3.3 特有的成员方法

`public void writeObject(Object obj)`：将指定的对象写入 `ObjectOutputStream`

### 3.4 实例

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

public class People implements Serializable {  // 实现序列化接口
    private static final long serialVersionUID = 1L;  // 设定序列化版本UID
    int age;
    String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "People [age=" + age + ", name=" + name + "]";
    }
}
```

## 4.1 ObjectInputStream

`ObjectInputStream extends InputStream`

### 4.2 构造方法

`public ObjectInputStream(InputStream io)`

**会抛出`IOException`和`ClassNotFoundException` 异常**

### 4.3 特有成员方法

`public Object readObject()`

### 4.4 实例

```java
public class IOTest {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\a.txt"))) {
            Object obj = ois.readObject();
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

public class People implements Serializable {
    private static final long serialVersionUID = 1L;
    int age;
    String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "People [age=" + age + ", name=" + name + "]";
    }
}
```

## 5.1 练习：序列化集合

当我们想在文件中保存多个对象的时候，可以把多个对象存储到一个集合中，对集合进行序列化和反序列化

```java
public class IOTest {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(17, "张三"));
        list.add(new Person(16, "李四"));
        list.add(new Person(18, "王五"));
        ArrayList<Person> list_in = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\a.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\a.txt"))) {
            oos.writeObject(list);
            Object obj = ois.readObject();
            list_in = (ArrayList<Person>) obj;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        for (Person person : list_in) {
            System.out.println(person);
        }
    }
}

public class Person implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int age;
    String name;

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
}
```

