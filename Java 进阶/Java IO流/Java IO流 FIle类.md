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

