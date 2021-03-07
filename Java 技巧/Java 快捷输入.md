# Java快捷输入

## 在VSCode里新建一个快捷输入方式

点击File=>Preferences=>User Snippets或者使用快捷键`Ctrl+Shift+P`打开命令输入`snippet` 新建一个snippet文件。

属性字段的描述如下：

- `Print to console`:自定义代码片段的名称。
- `scope`:代码片段适用的语言，多个语言用逗号分隔，如果为空或者不使用该参数将适用所有的语言。
- `prefix`:代码片段标识前缀，定义如何从IntelliSense和选项卡完成中选择此代码段。
- `body`:代码片段的主体内容，其中每个字符串表示一行。
- `description`:代码片段说明，会在 IntelliSense 候选栏中出现，也就是描述。

代码片段可能出现的变量如:$1,$2,使用Tab键可以让编辑器的指针在代码片段内的$1,$2变量所在的位置进行跳转，这些数字指定了光标跳转的顺序。$0表示最终光标位置。

下面是我添加的代码片段，在VS Code中开发Java程序时跟IDEA编辑器一样输入psvm后快速生成main方法。

```java
{
	// Place your global snippets here. Each snippet is defined under a snippet name and has a scope, prefix, body and 
	// description. Add comma separated ids of the languages where the snippet is applicable in the scope field. If scope 
	// is left empty or omitted, the snippet gets applied to all languages. The prefix is what is 
	// used to trigger the snippet and the body will be expanded and inserted. Possible variables are: 
	// $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. 
	// Placeholders with the same ids are connected.
	// Example:
	// "Print to console": {
	// 	"scope": "javascript,typescript",
	// 	"prefix": "log",
	// 	"body": [
	// 		"console.log('$1');",
	// 		"$2"
	// 	],
	// 	"description": "Log output to console"
	// }

	"Java PSVM": {
		"scope": "java",
		"prefix": "psvm",
		"body": [
			"public static void main(String[] args) {",
			   "$1",
		   "}",
		   "$2"
		],
		"description": "像IDEA一样快速生成PSVM"
	}
}
```



## 常见的快捷输入

- 输入psvm来生成

```java
public static void main(String[] args) {}
```

- 输入sout来生成

```java
System.out.println()
```

- 输入fori来生成

```java
for (int i = 0; i < args.length; i++) {}
```

