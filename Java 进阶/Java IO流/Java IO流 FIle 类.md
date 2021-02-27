# File 类

## 概述

文件和目录路径名的抽象表示形式

Java 把电脑中的文件和文件夹（目录）封装成了一个 File 类，我们可以通过 File 类对文件和文件夹进行操作

File 类是一个与系统无关的类，任何的操作系统都可以使用这个类中的方法

## 关键字

file：文件		directory：文件夹/目录		path：路径

## 静态成员变量

- static String **pathSeparator** 与系统有关的路径分隔符，为了方便，表示为字符串，在 windows 系统下为分号**;**，在linux 系统下为冒号**:**
- 
- static String **separator** 与系统有关的文件名称分隔符，window 下为反斜杠\，linux 下为正斜杠/

## 相对路径与绝对路径

绝对路径：是一个完整的路径，以盘符开始的路径，如	D:\\steam\\a.txt

相对路径：相对于当前项目根目录的路径，如	a.txt	../picture/a.txt

路径中的分隔符 windows 使用反斜杠，反斜杠是转义字符，两个反斜杠代表一个普通的反斜杠

## 构造方法

### File(String pathname)

通过给定的路径名字字符串转换为抽象路径名来创建一个新的 file 实例

参数：

- String pathname：字符串的路径名称
- 路径可以是文件名结尾，也可以是文件夹结尾
- 路径可以是相对路径，也可以是绝对路径
- 路径可以存在，也可以不存在
- 创建 File 对象，只是将字符串路径封装为 File 对象，不考虑路径的真假情况

```java
File f1 = new File("D:\\test");
```

### File(String parent, String child)

根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例

参数：

- 把路径分成了父路径和子路径两部分，都可以单独书写，使用起来非常灵活

```java
File f1 = new File("D:\\","a.txt");
```

### File(File parent, String child)

根据 parent 抽象路径名和 child 路径名字符串创建一个新的 File 实例

参数:

- 把路径分成了父路径和子路径两部分，都可以单独书写，使用起来非常灵活
- 父路径是 File 类型，可以使用 File 类的方法对路径进行操作

```java
File parent =new File("D:\\");
File f1 = new File(parent, "a.txt");
```

## 常用方法

### 获取功能的方法

- `public String getAbsolutepath()`：返回此 File 的绝对路径