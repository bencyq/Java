# 静态代理模式

## 基础概念

- 真实对象和代理对象都要实现同一个接口

- 代理对象要代理真实对象

实际上当我们使用 Runnable 接口开启多线程的时候，采用的就是这种静态代理模式，Threa() 作为代理对象，实现了 Runnable 接口的对象则为真实对象

`new Thread( () -> System.out.println("代理")).start()`，new 出的 Thread() 对象调用了真实对象的 start() 方法

## 用处

- 代理对象可以做真实对象做不了的事情
- 而真实对象只用专注于自己的事情

## 做法




## 实例

```java
public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany=new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

// 真实对象
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("你要结婚了");
    }

}

// 代理对象
class WeddingCompany implements Marry {

    private Marry target;  // 调用的真实对象

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    private void before() {
        System.out.println("结婚之前，布置现场");
    }

    private void after() {
        System.out.println("结婚之后，收拾现场");
    }
}
```

