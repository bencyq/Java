# 异常处理

## 一般的处理方法

之前的练习中，我一直在 main 函数中将 IOException 抛出，实际开发中不能这么操作，建议使用 `try catch`代码块处理异常

```java
public static void main(String[] args) {
    FileReader fr = null;  // 定义 IO流要在 try catch 语句外部定义，否则在 finally 语句块中就没办法使用 close() 方法
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

## JDK7 的新特性

- 在 try 的后边可以增加一个( )，在括号中可以定义流对象，而这个流对象的作用域就只在 try 中
- try 中的代码执行完毕，会**自动把流对象释放**，不用在 finally 中释放

### 格式

```java
try(定义流对象;定义流对象...){
    可能会产生异常的代码
} catch (异常变量名 变量名){
    异常处理
}
```

### 实例

```java
public static void main(String[] args) {
    try (FileReader fr = new FileReader("a.txt"); FileWriter fw = new FileWriter("a.txt")) {
        int len = 0;
        while ((len = fr.read()) != -1) {
            fw.write(len);
        }
    } catch (FileNotFoundException e) {
        System.out.println("找不到该文件");
    } catch (IOException e) {
        System.out.println(e);
    }
}
```

## JDK9 的新特性

- 在 try 的后边 ( ) 中可以直接引入流对象的名称（变量名）
- 在 try 代码执行完毕后，流对象也可以释放掉，不用写 finally
- 相较于 JDK7 的新特性，该特性更麻烦，还要 throws 异常

### 格式

```
A a = new A();
B b = new B();
try(a;b){
    可能会产生异常的代码
} catch (异常变量名 变量名){
    异常处理
}
```

### 实例

```java
public static void main(String[] args) throws IOException {
    FileReader fr = new FileReader("a.txt");
    FileWriter fw = new FileWriter("a.txt");
    try (fr; fw) {
        int len = 0;
        while ((len = fr.read()) != -1) {
            fw.write(len);
        }
    } catch (IOException e) {
        System.out.println(e);
    }
}
```

