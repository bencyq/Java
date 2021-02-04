# Java 基础

## 一、 输出字符串和数字时

```java
int a = 10;
int b = 20;
System.out.println("" + a + b);  //输出1020
System.out.println(a + b + "");  //输出30
```

因为直接将字符串和数字一起运算时，数字被当成了字符串；而输出的时候，计算是从左到右的，所以先运行了a + b。

## 二、快捷输入

输入psvm来生成

```java
public static void main(String[] args) {}
```

输入sout来生成

```java
System.out.println()
```

## 三、常见的DOS命令

### 盘符切换

输入 D: 或者 E: 等英文符号来切换

### 查看当前目录下的所有文件

输入 dir

### 切换目录

- 输入cd document_name 来进入下一级目录 (cd在英文中是change directory的意思)
- 输入cd..来返回上一级目录
- 输入cd /d D:/document_name 来进入别的盘的文件

### 清除屏幕

输入cls

### 推出终端

输入exit

### 查看电脑IP

输入ipconfig

### 查看网站IP

输入ping www.baidu.com

### 文件操作

```bash
md test  # 创建文件夹
rd test  # 删除文件夹
cd> a.txt  # 新建文件
del a.txt  # 删除文件
```

## 四、数据类型

### 不同的进制

```java
int a = 10;    //二进制
int a = 010;   //八进制（保持数据为三位的，不够的话添0
int a = 0x10； //十六进制（在数字前加0x
```

### 浮点数拓展

#### 问题：银行业务怎么表示

例子：

```java
float f = 0.1f;
double d = 1.0/10;
System.out.println(f==d);  //输出为false

float d1 = 2323231111232323f;
float d2 = d1 + 1;
System.out.println(d1==d2)  //出为true
```

原因是浮点数是离散的，它在处理的时候会舍入误差，会产生大约而不等于的效果。

所以，最好完全==避免使用浮点数作比较==。

所以，银行业务的表示可以采用Java的BigDecimal（数字工具类）来进行