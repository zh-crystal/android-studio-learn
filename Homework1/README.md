### 修改的配置文件
+ build.gradle
    ```js
    // 1. 使用maven镜像源
    maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
    maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter' }
    maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
    maven { url 'http://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }
    // 2. 修改kotlin版本1.3.21->1.3.61，否则使用模拟器运行app时会报错
    ext.kotlin_version = '1.3.61'
    ```
+ gradle/wrapper/gradle-wrapper.properties
    ```js
    // 使用离线gradle，与File->Settings->Gradle中的本地路径选择一致
    distributionUrl=D:/Program/Gradle/gradle-5.1.1-all.zip
    ```

### 自定义的源文件
+ app/src/main/res/layout/activity_main.xml
> 本次实现在Design模式完成View的添加和设置。
+ app/src/main/java/com.example.homework1/MainActivity.java
> 主要工作是添加三个按钮的onClickListener方法，将交互信息通过logd打印出来。

### 打包的apk
+ app-debug.apk