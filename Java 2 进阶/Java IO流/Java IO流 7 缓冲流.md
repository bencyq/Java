# 缓冲流

## 概述

缓冲流也叫高效流，是对 4 个基本的`FileXXX`流的增强，所以也有 4 个流，按照数据类型分类：

- 字节缓冲流：`BufferedInputStream`，`BufferedOutputStream`
- 字符缓冲流：`BufferedReader`，`BufferedWriter`

缓冲流的基本原理，是在创建流对象时，会创建一个内置的默认大小的缓冲区数组，通过缓冲区读写，减少系统 IO 次数，从而**提高读写的效率**

## 字节缓冲流

### 使用步骤

1. 创建 FileOutputStream 对象
2. 创建 BufferedOutputStream 对象，构造方法中传递 FileOutputStream 对象
3. 使用方法 write，将数据写入到内部缓冲区中
4. 使用方法 flush，将内部缓存区中的数据刷新到硬盘中
5. 释放资源（**关闭缓冲流的时候会自动关闭字节流/字符流**）

### 字节缓冲输出流 BufferedOutputStream

`java.io.BufferedOutputStream extends OutputStream`

#### 构造方法

- `BufferedOutputStream(OutputStream out)`
- `BufferedOutputStream(OutputStream out, int size)`：第二个参数指定了缓冲区的大小

`BufferedOutputStream`会给 `OutputStream`添加一个缓冲区，提高写入效率

#### 实例

```java
public static void main(String[] args) {
    try (FileOutputStream fos = new FileOutputStream("a.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                bos.write("我把数据写入到内部缓冲区中".getBytes());
                bos.flush();
                bos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### 字节缓冲输入流 BufferedInputStream

`java.io.BufferedInputStream extends InputStream`

#### 构造方法

- `BufferedInputStream(InputStream out)`
- `BufferedInputStream(InputStream out, int size)`：第二个参数指定了缓冲区的大小

#### 实例

```java
public static void main(String[] args) {
    try (FileInputStream fis = new FileInputStream("src\\a.txt");
            BufferedInputStream bis = new BufferedInputStream(fis)) {
        int len = 0;
        while ((len = bis.read()) != -1) {
            System.out.print((char)len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### 字节缓冲流效率测试

1. 采用了一个字节读写的方法，直接用文件字节流读写
2. 采用了字节数组缓冲读取的方法，用文件字节流读写
3. 采用了一个字节读写的方法，用缓冲字节流读写
4. 采用了字节数组缓冲读取的方法，用缓冲字节流读写

最终结果为 ![image-20210303145722029](D:\github本地仓库\Java\Java 2 进阶\picture\image-20210303145722029.png)

```java
public class IOTest {
    public static void main(String[] args) {
        copyDirectly();
        copyWithArray();
        copyWithBuffer1();
        copyWithBuffer2();
    }

    public static void copyDirectly() { // 一次读写一个字节
        long s = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("D:\\青年大学习\\青年大学习.xlsx");
                FileOutputStream fos = new FileOutputStream("copyDirectly.xlsx")) {
            int len; // 记录了所读取的字节
            while ((len = fis.read()) != -1) {
                fos.write(len);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时" + (e - s) + "毫秒");
        // 耗时 1302 毫秒
    }

    public static void copyWithArray() {
        long s = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("D:\\青年大学习\\青年大学习.xlsx");
                FileOutputStream fos = new FileOutputStream("copyWithArray.xlsx")) {
            int len; // 记录了所读取的字节
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时" + (e - s) + "毫秒");
    }

    public static void copyWithBuffer1() {
        long s = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\青年大学习\\青年大学习.xlsx"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copyWithBuffer1.xlsx"))) {
            int len = 0;
            while ((len = bis.read()) != -1) {
                bos.write(len);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时" + (e - s) + "毫秒");
    }

    public static void copyWithBuffer2() {
        long s = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\青年大学习\\青年大学习.xlsx"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copyWithBuffer1.xlsx"))) {
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = bis.read(b)) != -1) {
                bos.write(b);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时" + (e - s) + "毫秒");
    }
}

```

## 字符缓冲流

### 字符缓冲输出流 BufferedWriter

#### 构造方法

`BufferedWriter(Writer out)`

`BufferedWriter(Writer out, int sz)`：第二个参数决定了缓冲区的大小

#### 特有的成员方法

`public void newLine()`：写入一个行分隔符；会根据不同的操作系统，获取不同的行分隔符

#### 实例

```java
public static void main(String[] args) {
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"))){
        for(int i=0;i<10;i++){
            bw.write("陈彦琦");
            bw.newLine();
        }
        bw.close();
    } catch(IOException e){
        System.out.println(e);
    }
}
```

### 字符缓冲输入流 BufferedReader

#### 构造方法

`BufferedReader(Reader in)`

`BufferedReader(Reader in, int sz)`：第二个参数决定了缓冲区的大小

#### 特有成员方法

`public String readLine()`：读一行文本；返回值为该行内容的字符串，但不包含任何终止符（比如`\r \n`），如果已经到达结尾，则返回 null

#### 实例

``` java
public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
    } catch (IOException e) {
        System.out.println(e);
    }
}
```

## 练习：文本排序



