# 异常处理

之前的练习中，我一直在 main 函数中将 IOException 抛出，实际开发中不能这么操作，建议使用 `try catch`代码块处理异常

```java
public static void main(String[] args) {
    FileReader fr = null;
    try {
        fr = new FileReader("a.txt");
        int len = 0;
        while ((len = fr.read()) != -1) {
            System.out.println(len);
        }

    } catch (FileNotFoundException e) {
        System.out.println("路径是错误的");
    } catch (IOException e) {
        System.out.println(e);
    } finally {
        // 创建 fr 对象失败了，fr 的默认值是 null，是不能调用方法的，会抛出空指针异常，需要增加一个判断，不是 null 的话再把资源释放掉
        if (fr != null) {
            try {
                // close() 方法声明抛出了 IOException 对象，所以要处理这个对象
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
```

