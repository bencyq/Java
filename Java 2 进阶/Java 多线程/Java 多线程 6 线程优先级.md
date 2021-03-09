# 线程优先级

- Java 提供一个**线程调度器**来监视程序中启动后进入就绪状态的所有线程，线程调度器按照**优先级**决定应该调度哪个线程来执行
- 线程的优先级用数字来表示，范围从 1~10；最小的优先级为1，最大的优先级为10；默认优先级为 5
- 使用`getPriority()`和`setPriority(int xxx)`来获取和改变优先级
- **先设置优先级再启动**（优先级不会绝对地起作用，但是会很大提高概率，具体看 CPU 的调度）

```java
package src.com.bencyq.Thread;

public class ThreadPriority {
    public static void main(String[] args) {
        // 打印主线程默认优先级
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        
        MyPriority myPriority=new MyPriority();
        Thread t1=new Thread(myPriority);
        Thread t2=new Thread(myPriority);
        Thread t3=new Thread(myPriority);
        Thread t4=new Thread(myPriority);
        Thread t5=new Thread(myPriority);
        Thread t6=new Thread(myPriority);

        // 先设置优先级，再启动
        t1.start();
        
        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);  // MAX_PRIORITY = 10，为最大优先级
        t4.start();

        /*
        由于线程优先级越界，会报错，抛出异常
        t5.setPriority(-1);
        t5.start();

        t6.setPriority(11);
        t6.start();
        */
    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());

    }

}
```

