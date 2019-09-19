# KcMvp
mvp库。包含mvp模式使用的一些基类，旨在帮助新项目使用mvp模式进行快速开发。依赖后可以通过源码查看库中每个类的具体功能。

Gradle依赖方法：
1、在你的根目录的build.gradle文件中添加以下代码
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
2、在你的模块build.gradle文件中添加以下代码
dependencies {
	        implementation 'com.github.foreverGoUp:KcMvp:1.0.0'
}
