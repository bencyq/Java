# 查找文件夹内 md 文件所含字符数

- 代码主要用了 File 类的文件搜索和 FileReader 类的字符读取功能

```java
package src.com.bencyq.IO;

import java.io.*;

public class ChechCharacter {
    static long length = 0; // 记录文件中的字符总数

    public static void main(String[] args) {
        File file = new File("C:\\document\\github本地仓库\\java");
        getAllFile(file);
        System.out.println("该目录中的笔记共有 " + length + "个字符");
    }

    public static void checkCharacter(File file) {
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            int len = 0;
            char[] cs = new char[1024];
            while ((len = fr.read(cs)) != -1) {
                length += (long) len;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getAllFile(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                getAllFile(file);
            else {
                if (file.getName().endsWith("md"))
                    checkCharacter(file);
            }
        }
    }
}

```

