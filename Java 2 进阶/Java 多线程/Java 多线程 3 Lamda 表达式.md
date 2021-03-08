# Lambda 表达式

## 基础概念

其实质属于**函数式编程**的概念

## 作用

- 用来简化程序，避免匿名内部类定义过多
- 让代码看起来比较简洁
- 去掉了一堆没有意义的代码，只留下核心的逻辑

理解函数式接口（Functional Interface）是学习 Java8 Lambda 表达式的关键

## 函数式接口

- 任何接口，如果只包含唯一一个抽象方法，那么它就是一个函数式接口
- 对于函数式接口，我们可以通过 Lambda 表达式来**创建该接口的对象**

## 推导 Lambda 表达式

- Lambda 的核心是简化了匿名内部类（这个类是函数式接口重写实现的）
- 由于函数式接口只有一个抽象方法，因而在使用匿名内部类的时候只用重写一个方法
- 而 Lambda 表达式则是**省略了实现接口、重写方法名**这一段过程，直接通过**`test = () ->{...};`**来代替（test 为对象的引用）
- `()`里放重写的方法所要传递的参数
- 如果重写的方法里只有一行语句，`{}`也可以省略

```java
package src.com.bencyq.Thread;

public class TestLambda {

    // 3. 静态内部类
    static class Like2 implements Ilike {

        @Override
        public void Lambda() {
            System.out.println("This is Lambda 静态内部类");
        }

    }

    public static void main(String[] args) {
        Ilike like = new Like();
        like.Lambda();
        like = new Like2();
        like.Lambda();

        // 4.局部内部类
        class Like3 implements Ilike {

            @Override
            public void Lambda() {
                System.out.println("This is Lambda 局部内部类");
            }

        }

        like = new Like3();
        like.Lambda();

        // 5.匿名内部类
        like = new Ilike() {
            @Override
            public void Lambda() {
                System.out.println("This is Lambda 匿名内部类");
            }
        };
        like.Lambda();

        // 6.用 Lambda 简化
        like = () -> {
            System.out.println("This is Lambda 用 Lambda 简化");
        };
        like.Lambda();
    }
}

// 1.定义一个函数式接口
interface Ilike {
    void Lambda();
}

// 2. 实现类
class Like implements Ilike {

    @Override
    public void Lambda() {
        System.out.println("This is Lambda");
    }

}
```

## **表达式与简化**

```java
public class TestLambda {
    public static void main(String[] args) {
        // 标准 Lambda 表达式
        Lambda lambda = (int a) -> {
            System.out.print("This is Lambda");
            System.out.println(" 表达式");
        };
        lambda.test(0);

        // 简化1：参数类型省略
        lambda = (a) -> {
            System.out.print("This is Lambda");
            System.out.println(" 表达式 简化1");
        };
        lambda.test(0);

        // 简化2：简化括号(前提是只有一个参数)
        lambda = a -> {
            System.out.print("This is Lambda");
            System.out.println(" 表达式 简化2");
        };
        lambda.test(0);

        // 简化3：去掉大括号(前提是重写的方法体只有一行代码)
        lambda=a->System.out.println("This is Lambda 表达式 简化3");
        lambda.test(0);
    }
}

interface Lambda {
    public void test(int a);
}

class LambdaImpl implements Lambda {

    @Override
    public void test(int a) {
        System.out.println("This is Lambda");
    }

}
```

