// 线程停止
public class ThreadStop implements Runnable {

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("进行到第" + i + "轮了");
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();
        new Thread(threadStop).start();
        for (int i = 0; i < 1000; i++) {
            if (i == 900) {
                // 调用 stop 方法切换标志位，让线程停止（这是自己写的 stop 方法，不是 JDK 提供的）
                threadStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
