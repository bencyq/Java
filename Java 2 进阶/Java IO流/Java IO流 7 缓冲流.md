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
5. 释放资源

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

