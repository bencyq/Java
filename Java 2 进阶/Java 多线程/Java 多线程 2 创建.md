# 创建

## 三种创建方式

1. 继承 **Thread** 类（重点）
2. 实现 **Runnable** 接口（重点）
3. 实现 **Callable** 接口（了解）

## Thread

### 使用步骤

1. 自定义线程类继承 Thread 类
2. 重写 run() 方法，编写线程执行体
3. 创建线程对象，**调用 Start() 方法**启动多线程（如果调用了 run() 方法，会先执行run() 方法）
### 实例

```java
public class ThreadTest {  // 继承 Thread 类
    public static void main(String[] args) {
        StartThread st = new StartThread();  // 创建线程对象
        st.start();  // 开启线程
        for (int i = 0; i < 10; i++) {
            System.out.println("我在学习Java" + i);
        }
    }
}

class StartThread extends Thread {

    @Override
    public void run() {  // 重写 run() 方法
        for (int i = 0; i < 10; i++) {
            System.out.println("我在学习多线程" + i);
        }
    }
}
```

​	结果为      ![image-20210306151001652](../picture/image-20210306151001652.png)     交替运行了

## 总结

线程开启不一定立即执行，由 CPU 调度执行

## 案例：下载图片

