# 属性集

## 概述

- `java.util.properties`继承于`Hashtable`，来表示一个持久的属性集。它使用键值结构存储数据
- 可保存在流中或者从流中加载，是一个唯一和 IO流相结合的集合
- 该类也被许多 Java 类使用，比如获取系统属性时，`System.getProperties`方法就是返回一个`Properties`对象
- `properties`集合是一个双列集合，其属性列表中键和值默认都是字符串

## 方法

- `public void store(OutputStream out, String comments)`：把集合中的临时数据，持久化写入到硬盘中存储，**不可以传中文**，comments 用来解释说明保存的文件是做什么用的（不能使用中文），一般使用空字符串

- `public void store(Writer writer, String comments)`：同上，可以传中文，但注释不能用中文

- `public void load(InputStream inStream)`：把硬盘中保存的文件（键值对），读取到集合中使用，不能读取中文键值对

- `public void load(Reader reader)`：同上，可以读取中文键值对

- `public Object setProperty(Stirng key, String value)`：本质是继承了 Hashtable 的方法 `put`

- `public String getProperty(String key)`：通过 key 找到 value

- `public Set<String> stringPropertyNames()`：返回此属性列表的键集（值为字符串）

## 注意

1. 存储键值对的文件中，键与值默认的连接符号可以使用 = ， 空格
2. 存储键值对的文件中，可以使用 # 进行注释，被注释的键值对不会被读取 
3. 存储键值对的文件中，键与值默认都是字符串，不用再加引号

## 实例

```java
private static void show01() {
    // 创建集合对象
    Properties propWrite = new Properties();
    propWrite.setProperty("陈彦琦", "177");
    propWrite.setProperty("高健", "176");
    ;
    propWrite.setProperty("吕志佺", "176");

    // 创建字节/字符输出流对象
    try (FileWriter fw = new FileWriter("prop.txt")) {
        // 使用 Properties 集合中的方法 store，把集合中的临时数据，写入到硬盘中储存
        propWrite.store(fw, "save data");
    } catch (IOException e) {
        e.printStackTrace();
    }

    Properties propRead = new Properties();
    // 创建字节/字符输入流对象
    try (FileReader fr = new FileReader("prop.txt")) {
        // 使用 Properties 集合中的方法 load 读取文件
        propRead.load(fr);
    } catch (IOException e) {
        e.printStackTrace();
    }

    // 使用 stringPropertyNames 把 properties 集合中的键取出，存储到一个 set 集合中
    Set<String> set = propRead.stringPropertyNames();

    // 遍历 set 集合，取出 properties 集合的每一个键
    for (String key : set) {
        // 使用 getProperty 方法，通过 key 获取 value
        String value = propRead.getProperty(key);
        System.out.println(key + "=" + value);
    }
}
```

