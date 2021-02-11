# Java对象副本

## 方法重写(override)

重写都是方法的重写，和属性成员无关

### 基础概念

- 重写的方法的关键字不能是 private，可以是 public 和 protected
- 方法名必须相同
- 参数列表必须相同（重写不是重载）
- 修饰符范围可以扩大但是不能缩小：public > protected > default > private
- 抛出的异常范围可以被缩小但是不能扩大
- 子类的方法和父类必须要一致，但是方法体不同
- 子类不能用静态方法重写父类的非静态方法，同理，子类不能用非静态方法重写父类的静态方法


### P.S.
- 父类的引用可以指向子类
- 方法的调用只与定义的数据类型（引用）有关

### 静态方法

==静态方法无法被重写，只能被子类重新声明==

```java
package src.com.bencyq.oop1;  // 父类
public class A {
   public static void test(){
       System.out.println("a->test");
   }
}

package src.com.bencyq.oop1;  // 子类
public class B extends A {
    public static void test(){
        System.out.println("b->test");
    }
}

package src.com.bencyq.oop1;  // 执行类
public class application {
    public static void main(String[] args) {
        B b =new B();
        A a =new B();  //父类的引用可以指向子类
        b.test();  // 输出 b->test 被重新声明了，调用的是子类的静态方法
        a.test();   // 输出 a->test 方法的调用只与定义的数据类型（引用）有关
    }
}
```

### 非静态方法

```java
package src.com.bencyq.oop1;  // 父类
public class A {
   public void test(){
       System.out.println("a->test");
   }
}

package src.com.bencyq.oop1;  // 子类
public class B extends A {
    public void test(){
        System.out.println("b->test");
    }
}

package src.com.bencyq.oop1;  // 执行类
public class application {
    public static void main(String[] args) {
        B b =new B();
        A a =new B();  //父类的引用可以指向子类
        b.test();   // 输出 b->test 
        a.test();   // 输出 b->test  A 类的非静态方法被 B 类重写了
    }
}
```

### 重点

- 静态的方法是类的方法，而非静态的方法是对象的方法
- 有 static 的时候，b 调用了 B 类的方法，因为 b 是用 B 类声明的
- 没有 static 的时候，b 调用的是对象的方法，而 b 是用 A 类 new 的，即 b 是 A new 出来的对象，因此调用了A的方法

## 多态

### 基本概念

- 动态编译 类型：可扩展性
- 即同一方法可以根据发送对象的不同而采用多种不同的行为方式
- 一个对象的实际类型是确定的，但可以指向对象的引用类型有很多
- 多态是方法的多态，属性没有多态性
### 存在的条件

- 有继承方法
- 子类重写父类的方法
- 父类引用指向子类对象

### 对象能执行的方法

- 对象能执行的方法，主要看其左边声明的引用类型，和用什么类 new 出来的对象关系不大
- ==对象能调用的方法都是自己的，或者继承父类的==
- 父类的引用可以指向子类，但是不能调用子类独有的方法

#### 例子

```java
package src.com.bencyq.oop1;  // 父类
public class Person {
    protected void test() {
        System.out.println("person");
    }
}

package src.com.bencyq.oop1;  // 子类
public class Student extends Person {
    protected void test(){
        System.out.println("student");
    }
    public void eat(){
        System.out.println("eat");
    }    
}

package src.com.bencyq.oop1;  // 执行类
public class application {
    public static void main(String[] args) {
        Student s1 = new Student();
        Person s2 = new Student();
        Object s3 = new Student();
        s1.test();  // 输出 student
        s2.test();  // 输出 student
        s1.eat();   // 输出 eat
        s2.eat();   // 报错
    }
}

```

### 例外

- static 方法，属于类，不属于实例
- final 常量
- private 方法，无法被其他对象调用

## instanceof 关键字用法

　instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例，用法为：

```java
boolean result = obj instanceof Class
```

　　其中 obj 为一个对象，Class 表示一个类或者一个接口，当 obj 为 Class 的==对象==，或者是其==直接或间接子类==，或者是其==接口的实现类==，结果result 都返回 true，否则返回false。

　　注意：编译器会检查 obj 是否能转换成右边的class类型，如果不能转换则直接报错，如果不能确定类型，则通过编译，具体看运行时定。

### 实例

#### 1.obj 必须为引用类型，不能是基本类型

```java
int i = 0;
System.out.println(i instanceof Integer);  // 编译不通过
System.out.println(i instanceof Object);   // 编译不通过
```

instanceof 运算符只能用作对象的判断。

基本类型： string, number, boolean, null, undefined, symbol 之类的

引用类型：Function, Array, Object

#### 2.obj 为 null

```java
System.out.println(null instanceof Object); // false
```

在 [JavaSE规范](https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.20.2) 中对 instanceof 运算符的规定就是：如果 obj 为 null，那么将返回 false。

#### 3.obj 为 class 类的实例

```java
Integer integer = new Integer();
System.out.println(integer instanceof Interger);  // true
```

#### 4.obj 为 class 接口的实现类

　我们可以用 instanceof 运算符判断 某个对象是否是该接口的实现类，如果是返回 true，否则返回 false

#### 5.obj 为类的直接或间接子类

```java
public class Person{}  // 父类
public class Man extends Person{} // 子类
// 测试
Person p1 = new Person();
Person p2 = new Man();
Man m1 = new Man();
System.out.println(p1 instanceof Man);  // false
System.out.println(p2 instanceof Man);  // true
System.out.println(m1 instanceof Man);  // true
```

注意第一种情况， p1 instanceof Man ，Man 是 Person 的子类，Person 不是 Man 的子类，所以返回结果为 false。