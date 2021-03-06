# 打印流

`java.io.PrintStream extends java.io.FileOutputStream`

## 特点：

1. 只负责数据的输出，不负责数据的输入
2. 永远不会抛出`IOException`，但会抛出`FileNotFoundException`
3. 有特有方法
4. **与父类的`write`方法不同，`print/println`方法能打印字符、字符串、浮点数、整数等**
5. 可以改变输出语句的目的地，用`System.setOut()`方法，参数传递为打印流

## 构造方法

- `public PrintStream(File file)`：输出的目的地是文件
- `public PrintStream(OutputStream out)`：输出的目的地是字节输出流
- `public PrintStream(String filename)`：输出的目的地是文件路径

## 注意

如果使用父类的`write`方法写数据，那么会查询编码表再写出数据  97->a

如果使用自己特有的方法`print/println`方法写数据，写的数据原样输出  97->97

## 实例

```java
// 打印不同类型的值
public class IOTest {
    public static void main(String[] args) {
        try (PrintStream ps = new PrintStream("src\\a.txt")) {
            ps.write(97);
            ps.println(97);
            ps.println(8.8);
            ps.println('a');
            ps.println("Hello Wrold");
            ps.println(true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}

// 改变输出语句的目的地
public class IOTest {
    public static void main(String[] args) {
        try(PrintStream ps =new PrintStream("src\\目的地是打印流.txt")){
            System.out.println("我是在控制台输出");  // 这句在控制台中输出
            System.setOut(ps);  // 参数传递为打印流
            System.out.println("我在打印流的目的地中输出");  // 这句在文件中输出
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}
```

