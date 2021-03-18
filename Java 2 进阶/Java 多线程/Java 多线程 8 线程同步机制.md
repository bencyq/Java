# 线程同步机制

## 概念

- 处理线程问题时，多个线程访问同一个对象，并且某些线程还想修改这个对象，这个时候我们就需要**线程同步**
- 线程同步其实是一种**等待**机制，多个需要同时访问此对象的线程进入这个**对象的等待池**形成队列，等待前面的线程使用完毕，下一个线程再使用
- 并发：同一个对象被多个线程同时操作
## 锁
- 由于同一进程共享一块储存空间，为了保证数据在方法中被访问时的正确性，在访问时加入**锁机制 synchronized**，当一个线程获得对象的排它锁，会独占资源，其他线程必须等待，使用后释放锁即可

### 锁机制带来的问题

1. 一个线程持有锁会导致其他所有需要此锁的线程挂起
2. 在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题
3. 如果一个优先级高的线程等待一个优先级低的线程，会导致优先级倒置，引起性能问题

## 线程不安全的案例

### 案例一

```java
package src.com.bencyq.Thread;

public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "路人 1").start();
        new Thread(station, "路人 2").start();
        new Thread(station, "黄牛").start();
    }
}

class BuyTicket implements Runnable {

    private int ticketNums = 10;
    boolean flag = true;

    @Override
    public void run() {
        // 买票
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buy() throws InterruptedException {
        // 判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        // 模拟延时
        Thread.sleep(100);

        // 买票
        System.out.println(Thread.currentThread().getName() + "买到票了" + ticketNums--);
    }

}
```

一张票被多个人获取

![image-20210315111443229](../picture/image-20210315111443229.png)	

### 案例二

```java
package src.com.bencyq.Thread;

public class UnsafeBank {
    public static void main(String[] args) {
        // 创建账户
        Account account = new Account(100, "基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "你");
        you.start();
        girlFriend.start();
    }
}

// 账户
class Account {
    int money; // 余额
    String name; // 卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行：模拟取款
class Drawing extends Thread {
    Account account; // 账户

    int drawingMoney; // 取了多少钱

    int nowMoney;// 现在手里有多少钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {
        // 判断有没有钱
        if (account.money < drawingMoney) {
            System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
            return;
        }

        // 放大问题的发生性
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 卡内余额
        account.money = account.money - drawingMoney;

        // 你手里的钱
        nowMoney = nowMoney + drawingMoney;

        System.out.println(account.name + "余额为 " + account.money);
        System.out.println(this.getName() + "手里的钱 " + nowMoney);
    }
}

```

钱被多拿了

![image-20210315111609473](../picture/image-20210315111609473.png)	

### 案例三：List 集合的线程不安全

```java
package src.com.bencyq.Thread;

import java.util.*;

public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
```

输出结果为 9987（原本应该是输出10000）

因为有不同 的线程同时对一个资源进行了操作

## 同步方法

### 基础概念

- `public synchronized void method(int args){}`
- 该方法控制对"对象"的访问，每个对象对应一把锁，每个`synchronized`必须获取调用该方法的锁才能执行，否则线程会阻塞
- 方法一旦执行，就独占该锁，直到方法返回才释放锁，后面被阻塞的线程才能获得这个锁，继续执行
- `synchronized`默认锁的是 this
- 缺陷：若将一个大的方法声明为`synchronized`将会影响效率
### 实例

在这个例子里，`synchronized`方法锁的是 this

```java
package src.com.bencyq.Thread;

public class SafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "路人 1").start();
        new Thread(station, "路人 2").start();
        new Thread(station, "黄牛").start();
    }
}

class BuyTicket implements Runnable {

    private int ticketNums = 10;
    boolean flag = true;

    @Override
    public void run() {
        // 买票
        while (flag) {
            buy();

            // 模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized 同步方法，锁的是 this
    private synchronized void buy() {
        // 判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        // 买票
        System.out.println(Thread.currentThread().getName() + "买到票了" + ticketNums--);
    }

}
```

得到了一个正常的顺序

![image-20210315164434314](../picture/image-20210315164434314.png)	

## 同步块

### 基础概念

- `synchronized(obj){}`
- `obj`称为**同步监视器**，可以为任何对象，但是推荐使用共享资源作为同步监视器
- 锁的对象（obj）就是变化的量，需要增删改的对象
- 同步方法中无需指定同步监视器，因为同步方法的同步监视器就是 this，即这个对象本身，或者是 class

### 使用方法

直接锁有变动的对象，具体操作放在代码块里

```java
public void run(){
    synchronized(obj){
        ...
    }
}
```



### 同步监视器的执行过程

1. 第一个线程访问，锁定同步监视器，执行其中的代码
2. 第二个线程访问，发现同步监视器被锁定，无法访问
3. 第一个线程访问完毕，解锁同步监视器
4. 第二个线程访问，发现同步监视器没有锁，然后锁定并访问

### 实例一

在这个例子里，`synchronized`锁的是 account，是另外一个 class，不是继承了 Thread 类的、重写了 run 方法的 this 

```java
package src.com.bencyq.Thread;

public class SafeBank {
    public static void main(String[] args) {
        // 创建账户
        Account account = new Account(100, "账户");
        Drawing you = new Drawing(account, 50, "卡主 1");
        Drawing girlFriend = new Drawing(account, 100, "卡主 2");
        you.start();
        girlFriend.start();
    }
}

// 账户
class Account {
    int money; // 余额
    String name; // 卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行：模拟取款
class Drawing extends Thread {
    Account account; // 账户

    int drawingMoney; // 取了多少钱

    int nowMoney;// 现在手里有多少钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {

        synchronized (account) {
            // 判断有没有钱
            if (account.money < drawingMoney) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }

            // 放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 卡内余额
            account.money = account.money - drawingMoney;

            // 你手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "余额为 " + account.money);
            System.out.println(this.getName() + "手里的钱 " + nowMoney);
        }
    }
}

```

### 实例二：集合 LIst

```java
package src.com.bencyq.Thread;

import java.util.*;

public class SafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                // 在 Lambda 表达式里锁
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
```

## JUC 安全类型的集合

位于`java.util.concurrent.*`包下，其集合都是安全类型的，不用再调用同步机制

### 实例`CopyOnWriteArrayList`集合

```java
package src.com.bencyq.Thread;

import java.util.concurrent.CopyOnWriteArrayList;

public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list =new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
    }
}
```

## 死锁

### 基本概念

- 两个或多个线程占有同一资源，互相等待对方释放资源，都停止执行的情形
- 某一同步块同时拥有**两个以上对象的锁**时，就可能发生死锁问题

### 避免方法

#### 产生死锁的四个必要条件

1. 互斥条件：一个资源每次只能被一个进程使用
2. 请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放
3. 不剥夺条件：线程已获得的资源，在未使用完之前，不能强行剥夺
4. 循环等待条件：若干线程之间形成一种头尾衔接的循环等待资源关系

### 实例（==未完成==）

```java
package src.com.bencyq.Thread;

public class DeadLock {
    public static void main(String[] args) {
        Test test1 = new Test(0, "疯子 1");
        Test test2 = new Test(0, "疯子 2");
        test1.start();
        test2.start();
    }
}

class Thread1 {

}

class Thread2 {

}

class Test extends Thread {
    static Thread1 thread1 = new Thread1();
    static Thread2 thread2 = new Thread2();

    int choice;
    String threadName; // 使用 CPU 的线程

    Test(int choice, String threadName) {
        this.choice = choice;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 互相持有对方的锁
    private void test() throws InterruptedException {
        if (choice == 0) {
            synchronized (thread1) { // 获得 线程1 的锁
                System.out.println(this.threadName + "获得 线程1 的锁");
                Thread.sleep(1000);
                synchronized (thread2) { // 1 秒钟后，获得 线程2 的锁
                    System.out.println(this.threadName + "获得 线程2 的锁");
                }
            }
        } else {
            synchronized (thread2) { // 获得 线程2 的锁
                System.out.println(this.threadName + "拿到 线程1 的锁");
                Thread.sleep(3000);
                synchronized (thread1) { // 2 秒钟后，获得 线程1 的锁
                    System.out.println(this.threadName + "拿到 线程2 的锁");
                }
            }
        }
    }
}
```

