# Java IO流 概述

## 基本概念

Java 使用 IO流用来读写数据，分为输入流和输出流。

Java 中所有的流都在 Java.io.* 下

Java 的 IO流有四大块：

	1. java.io.InputStream        字节输入流
 	2. java.io.OutputStream     字节输出流
 	3. java.io.Reader                字符输入流
 	4. java.io.Writer                  字符输出流

以 Stream 为结尾的都是字节流，以 Reader/Writer 为结尾的都是字符流