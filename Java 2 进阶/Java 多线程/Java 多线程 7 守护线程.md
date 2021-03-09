# 守护线程

## 基本概念

- 用户分为用户线程和守护线程
- 虚拟机**不用**等待**守护线程**执行完毕
- 虚拟机必须确保用户线程执行完毕，执行完毕用户线程后就会结束

## 用法

后台记录操作日志、监控内存、垃圾回收等待

## 实例

```java
package src.com.bencyq.Thread;

public class ThreadDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true); // 默认为 false 表示用户线程，设置为 true 表示为守护线程

        thread.start(); // 守护线程启动

        new Thread(you).start();
    }
}

class God implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            System.out.println("God protect you");
        }
    }

}

class You implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 3650; i++) {
            System.out.println("alive  day "+i);
        }
        System.out.println("------dead");
    }

}
```