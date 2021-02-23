# Java 集合 泛型

## 一、什么是泛型

泛型：**把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型**

## 二、泛型的意义

- 代码更加简洁【不用强制转换】
- 程序更加健壮【只要编译时期没有警告，那么运行时期就不会出现ClassCastException异常】
- 可读性和稳定性【在编写集合的时候，就限定了类型】，使代码更**安全**
- 可以用 for each 来遍历集合

## 三、泛型基础

#### 3.1 泛型类

**泛型类就是把泛型定义在类上，用户使用该类的时候，才把类型明确下来**....这样的话，用户明确了什么类型，该类就代表着什么类型...用户在使用的时候就不用担心强转的问题，运行时转换异常的问题了。

- **在类上定义的泛型，在类的方法中也可以使用！**

```java
/*
    1:把泛型定义在类上
    2:类型变量定义在类上,方法中也可以使用
 */
public class ObjectTool<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
```

- 测试代码：

**用户想要使用哪种类型，就在创建的时候指定类型。使用的时候，该类就会自动转换成用户想要使用的类型了。**

```java
public static void main(String[] args) {
    //创建对象并指定元素类型
    ObjectTool<String> tool = new ObjectTool<>();

    tool.setObj(new String("bencyq"));
    String s = tool.getObj();
    System.out.println(s);

    //创建对象并指定元素类型
    ObjectTool<Integer> objectTool = new ObjectTool<>();
    /**
         * 如果我在这个对象里传入的是String类型的,它在编译时期就通过不了了.
         */
    objectTool.setObj(10);
    int i = objectTool.getObj();
    System.out.println(i);
}
```

#### 3.2 泛型方法

- 定义泛型方法....**泛型是先定义后使用的**

```java
//定义泛型方法..
public <T> void show(T t) {
    System.out.println(t);
}
```

- 测试代码：

**用户传递进来的是什么类型，返回值就是什么类型了**

```java
public static void main(String[] args) {
    //创建对象
    ObjectTool tool = new ObjectTool();

    //调用方法,传入的参数是什么类型,返回值就是什么类型
    tool.show("hello");
    tool.show(12);
    tool.show(12.5);

}
```

#### 3.3 泛型类派生出的子类

前面我们已经定义了泛型类，**泛型类是拥有泛型这个特性的类，它本质上还是一个Java类，那么它就可以被继承**

那它是怎么被继承的呢？？这里分两种情况

1. **子类明确泛型类的类型参数变量**
2. **子类不明确泛型类的类型参数变量**

##### 3.3.1子类明确泛型类的类型参数变量

- 泛型接口

```java
/*
    把泛型定义在接口上
 */
public interface Inter<T> {
    public abstract void show(T t);

}
```

- 实现泛型接口的类.....

```java
/**
 * 子类明确泛型类的类型参数变量:
 */

public class InterImpl implements Inter<String> {
    @Override
    public void show(String s) {
        System.out.println(s);

    }
}
```

##### 3.3.2子类不明确泛型类的类型参数变量

- 当子类不明确泛型类的类型参数变量时，**外界使用子类的时候，也需要传递类型参数变量进来，在实现类上需要定义出类型参数变量**

```java
/**
 * 子类不明确泛型类的类型参数变量:
 *      实现类也要定义出<T>类型的
 *
 */
public class InterImpl<T> implements Inter<T> {

    @Override
    public void show(T t) {
        System.out.println(t);

    }
}
```

**测试代码:**

```java
public static void main(String[] args) {
//测试第一种情况
//Inter<String> i = new InterImpl();
//i.show("hello");

//第二种情况测试
Inter<String> ii = new InterImpl<>();
ii.show("100");

}
```

值得注意的是：

- **实现类的要是重写父类的方法，返回值的类型是要和父类一样的！**
- **类上声明的泛形只对非静态成员有效**

 