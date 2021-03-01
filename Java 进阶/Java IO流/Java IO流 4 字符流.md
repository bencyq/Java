# 字符流

## 字符输入流 Reader

`java.io.Reader`：是字符输入流的最顶层的父类，是一个抽象类

### 共性成员方法

`public int read()`：读取单个字符并返回

`public int read(char[] cbuf)`：一次读取多个字符，将字符放入数组中；返回读取的有效字符的个数

`public void close()`：关闭流

### 文件字符输入流 FileReader

`java.io.FileReader extends InputStreamReader extends Reader`

将硬盘文件中的数据以字符的方式读取到内存中

#### 构造方法

`FileReader(String fileName)`

`FileReader(File file)`

#### 实例

```java
public static void main(String[] args)throws IOException {
    FileOutputStream fos = new FileOutputStream("a.txt");
    fos.write("abc123".getBytes());
    FileReader fr =new FileReader("a.txt");
    int len =0;
    while((len=fr.read())!=-1){
        System.out.print((char)len);
    }
    fr.close();
}
```

