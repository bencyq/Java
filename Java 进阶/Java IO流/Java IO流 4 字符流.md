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

- 单字符读取

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

- 字符数组缓冲读取

```java
public static void main(String[] args) throws IOException {
    FileReader fr = new FileReader("a.txt");
    char[] cs = new char[1024];
    int len = 0;
    while ((len = fr.read(cs)) != -1) {
        System.out.println(new String(cs));
    }
    fr.close();
}
```

这里采用了 String 类的构造方法，`public String(char[] value)`和`public String(char[] value, int offset, int count)`，将**字节数组转化为字符串**

## 字符输出流 Writer

`java.io.Writer`：是字符输入流的最顶层的父类，是一个抽象类

### 共性成员方法

`public void write(int c)`：写入单个字符

`public void write(char[] cbuf) `：写入字符数组

`public abstract void write(char[] cbuf, int off, int len)`：写入字符数组的一部分

`public void write(String str)`写入字符串

`public void write(String str, int off, int len)`写入字符串的一部分

`public void flush()`刷新该流的缓冲

`public void close()`关闭流，但会先刷新流的缓冲t

### 文件字节输出流 FileWriter

`java.io.FileWriter extends OutputStreamWriter extends Writer`

#### 构造方法

`FileWriter(String fileName)`

`FileWriter(File file)`

#### 字符输出流输出的步骤

1. 创建 FileWriter 对象，构造方法中绑定数据的目的地
2. 使用方法 write，将数据写入到**内存缓冲区中（字符转换为字节的过程）**
3. 使用方法 flush，将内存缓冲区中的数据，刷新到文件中
4. 释放资源（会先吧内存缓冲区中的数据刷新到文件中）

#### 基本写出数据

- 写出单个字符

```java
public static void main(String[] args) throws IOException {
    FileWriter fw = new FileWriter("a.txt");
    fw.write("阿巴阿巴");
    fw.close();
}
```

#### 续写与换行

##### 构造方法

`public FileWriter(String fileNmae, boolean append)`

`public FileWriter(File file, boolean append)`

 ##### 实例

```java
public static void main(String[] args) throws IOException {
    FileWriter fw = new FileWriter("a.txt", true);
    fw.write("1111");
    fw.close();
}
```

##### 换行

​	符号：

​		`windows:\r\n`			`linux:/n`			`mac:/r`

