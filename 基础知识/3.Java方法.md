# Java方法

## 方法的基本概念

- 方法是语句的集合
- 一个方法只完成一个功能，有利于后期的扩展
- 命名规则为驼峰命名
- Java的方法采用的是值传递，因为没有对指针的操作

## 方法的基本组成部分

修饰符  返回值类型  方法名（参数类型  参数名）

……

方法体

……

return  返回值;

### 例子

```java
public class test {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = add(a, b);
    }

    public static int add(int a, int b) {  
        //在静态的成员中，不能直接访问非静态成员(包括方法和变量)。因为，非静态的变量是依赖于对象存在的，对象必须实例化之后，它的变量才会在内存中存在。
        //所以该方法的定义中加入了 static
        return a + b;
    }
}
```

## 方法重载

### 概念

重载就是在一个类中，有相同的函数名称，但形参不同的函数

### 重载的规则

- 方法名必须相同

- 参数列表必须不同（个数不同、类型不同、参数排列顺序不同）

- 方法的返回类型可以不同也可以相同

- ==仅仅返回类型的不同不足以构成方法重载==

## 可变参数

### 概念

- 从JDK1.5开始，Java支持传递同类型的可变参数给一个方法。
- 在方法声明中，在指定参数类型后加一个省略号。
- 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数。任何普通的参数必须在它之前声明。
### 例子

```java
public class test {
    public static void main(String[] args) {
        print(34, 3, 3, 2, 56.5);
    }

    public static void print(double... numbers) {
        //传入的为可变参数
        if (numbers.length == 0) {
            System.out.println("No argument passed");
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
        return;
    }
}
```

## 递归

### 概念

- 自己调用自己
- 利用递归可以简化所需要的重复计算，减少代码量
### 结构

- 递归头：什么时候不调用自己。

- 递归体：怎样调用自己。

## 例子

```java
public static int f(int n) {
        if (n == 1)
            return 1;
        else {
            return n * f(n - 1);
        }
    }
```

