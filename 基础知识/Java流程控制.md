# Java流程控制

## 一、Scanner对象

### 基础语法

1. 新建一个Scanner的对象，打开IO流

```java
Scanner sc = new Scanner(System.in);  
```
2. 使用hasNext方式接受（碰到空格停止）
```java
String str = sc.next();
```
3. 使用hasNextLine方式接受（碰到回车符停止）
```   java
String str = sc.nextNextLine();
```

4. 用完后关闭


```java
sc.close()  //凡是属于IO流的类如果不关闭会一直占用资源
```

p.s.

```java
sc.hasNextLine()  //判断是否有输入
```

### 进阶语法

