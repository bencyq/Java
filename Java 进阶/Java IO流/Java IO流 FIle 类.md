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

- `public String getAbsolutepath()`：返回此 File 的**绝对路径**

- `public string getPath()`：将此 File 对象转换为路径名**字符串**，绝对路径则返回绝对路径，相对路径则返回相对路径

- `public String getName()`：返回由此 File 对象表示的文件或者目录的名字，获取的是**路径的结尾**，即文件或者文件夹

- `public long length()`：返回由此 File 对象表示的文件的**大小**，以字节为单位的，返回值为 **Long**；如果路径不存在或者路径是文件夹，那么返回**0**

- ```java
  File f1 = File("D:\\a.txt");
  System.out.println(f1.toString());  // toString() 方法调用的是 File 类的 getPath() 方法 
  ```


### 判断功能的方法

- `public boolean exists()`：判断此 File 对象表示的文件或者目录是否存在
- `public boolean isDirectoy()`：判断是否为目录
- `public boolean isFile()`：判断是否为文件

### 创建和删除功能的方法

- `public boolean createNewFile()`：当且仅当具有该名称的文件不存在的时候，创建一个新的空文件（会抛出异常 IOException，得捕获或者继续抛出）
- `public boolean delete()`：删除此文件或者目录
- `public boolean mkdir()`：创建该目录（只能创建**单级文件夹**，不能创建多级文件夹）
- `public boolean mkdirs()`：创建该目录，包括任何必需但是不存在的父目录（可以创建**多级文件夹**）

## 目录的遍历

- `public String[] list()`：返回一个 String 数组，表示该 File 目录中的所有子文件或者目录
- `public File[] listFiles()`：返回一个 File 数组，表示该 File 目录中的所有子文件或者目录

如果该目录不存在或者不是一个目录，会抛出空指针异常

能获取隐藏的文件和目录

```java
File f1 = new File("C:\\document\\github本地仓库");
String[] arr = f1.list();
for (String string : arr) {
	System.out.println(string);  // 打印的是目录和文件的名称
}

File[] files = f1.listFiles();
for (File file : files) {
    System.out.println(file);  // 打印的是目录和文件的整个绝对路径
}
```

### 递归打印多级目录

```java
public class IOTest {
    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库");
        getAllFile(file);
    }

    public static void getAllFile(File dir) {
        System.out.println(dir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                getAllFile(file);  // 递归
            }
            else{
                System.out.println(file);
            }
        }
    }
}
```

## 综合案例

### 文件搜索

查找以 **.md** 结尾的文件

```java
public class IOTest {
    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库");
        getAllFile(file);
    }

    public static void getAllFile(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFile(file); // 递归
            } else {
                String str = file.getAbsolutePath(); // 获得字符串形式的路径
                str = str.toLowerCase(); // 将路径全部小i写
                boolean b = str.endsWith(".md"); // 判断字符串末尾是否为 .md
                if (b)
                    System.out.println(file);
                // 或者直接
                // if (file.getName().toLowerCase().endsWith(".md"))
                //		System.out.println(file);
            }
        }
    }
}
```

### 文件过滤器（FileFilter）

#### `java.io.FileFilter`接口

- `java.io.FileFilter`是一个接口，用来过滤**文件名**
- [listFiles](# 目录的遍历) 方法参数传递的就是过滤器对象（new FileFilter)

##### 作用

用来过滤文件（File 对象）

##### 抽象方法

`boolean accept(File pathname)`：用来过滤文件的方法；

​		参数： File pathname 就是使用 [listFiles](# 目录的遍历) 方法遍历目录，得到的每一个文件对象

#### `java.io.FilenameFilter`接口

##### 作用

用于过滤文件名称，和 FileFilter 接口的区别是，FilenameFilter 可以过滤**文件名和文件夹名**

##### 抽象方法

`boolean accept(File dir, String name)`：用来过滤文件名称的方法

​		参数：File dir 是构造方法中传递的被遍历的目录；

​				   String name 是使用 ListFiles 方法遍历目录，获得的文件/文件夹的名称

#### 注意

两个过滤器是没有实现类的，必须我们自己**重写实现类**，重写过滤方法 accept

#### 过滤器的原理

```java
File[] files = dir.listFiles(new FileFilterImpl());
```

`FileFilter`中的方法 accept 是由 `listFiles`调用的

`listFiles`一共做了三件事

1. 查找该路径下的每一个目录和文件 --> 封装为 File 对象
2. 调用参数传递的过滤器中的方法 accept
3. 把封装后的 File 对象，传递到 accept 方法中

```java
public class FileFilterImpl implements FileFilter {
    public boolean accept(File pathname) {
        /*
        过滤的规则
        */
        return true;
    }
}
```

#### 实例

##### FileFilter

- 正常写法

```java
// 过滤器的实现类
public class FileFilterImpl implements FileFilter {
    public boolean accept(File pathname) {
        // 如果 pathname 是目录，则继续遍历，且返回 true
        if (pathname.isDirectory())
            return true;
        return pathname.getAbsolutePath().toLowerCase().endsWith(".md");
    }
}

// 执行文件
public class IOTest {
    static int count = 0;

    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库");
        getAllFile(file);
        System.out.println("一共有 " + count + " 个笔记");
    }

    public static void getAllFile(File dir) {
        File[] files = dir.listFiles(new FileFilterImpl()); // 传递过滤器对象
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFile(file); // 递归
            } else {
                System.out.println(file);
                count++;
            }
        }
    }
}
```

- **匿名内部类写法**

```java
public class IOTest {
    static int count = 0;

    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库");
        getAllFile(file);
        System.out.println("一共有 " + count + " 个笔记");
    }

    public static void getAllFile(File dir) {
        // 采用匿名内部来，在 new 的时候直接重写方法
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".md");
            }
        });
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFile(file); // 递归
            } else {
                System.out.println(file);
                count++;
            }
        }
    }
}
```

##### FilenameFilter

- 匿名内部类写法

```java
public class IOTest {
    static int count = 0;

    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库");
        getAllFile(file);
        System.out.println("一共有 " + count + " 个笔记");
    }

    public static void getAllFile(File dir) {
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir,String name) {  // 匿名重写 accept 方法
                return new File(dir,name).isDirectory()||name.endsWith(".md");
            }
        });
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFile(file); // 递归
            } else {
                System.out.println(file);
                count++;
            }
        }
    }
}
```

