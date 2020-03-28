# android-studio-learn
移动互联网技术及应用课程实操

## 一、homework1
> 2020-03-16
### 任务要求
+ 创建一个Android app工程，包含一个activity（环境可以参考 demo）
+ 使用5种以上的View并实现一些简单交互
    + ImageView, Button, TextView, RadioButton, CheckBox，EditText, ProgressBar, SeekBar, Switch 等等不限
+ 将一些交互结果输出log
+ 打包生成apk
+ 把练习代码上传到github仓库里
### 任务实现
+ 参照demo新建Android app工程
+ 实验环境
    + Windows10
    + Android Studio 3.6.1
    + JDK8
    + Gradle-5.1.1-all
    + 模拟器: Pixel2 API26
+ 实现效果
    + 包含5种view
        + 图片logo -> ImageView
        + 用户名和密码提示框 -> TextView
        + 用户名和密码输入框 -> EditText
        + 记住密码单选按钮 -> RadioButton
        + 注册和退出按钮 -> Button
    + UI截图
    ![UI截图](./screenshot/chapter1/ui.png)
    + 交互
        + 启动日志记录启动信息
        + 可输入用户名和密码
        + 可勾选记住密码
        + 点击注册日志记录注册信息
        + 点击退出日志记录退出信息并退出程序
    + 日志记录截图
    ![日志截图](./screenshot/chapter1/log.png)

## 二、homework2
> 2020-03-23
### 任务要求
+ Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来，但UI界面我们看不到，在今天课程基础上想办法补全它，让其跟logcat的展示一样
+ 一个抖音笔试题：统计页面所有view的个数     
    + ViewGroup中的API ： getChildCount()   getChildAt()
+ 实现一个类似抖音消息页面，并且点击每个recycleview的item,能够跳转到一个新的界面，并且在新的页面显示出他是第几个item
### 任务实现
+ MainActivity界面UI截图
![activity_main.xml](./screenshot/chapter2/main.png)
+ 任务1
    + 初次进入UI截图
    ![exe1_01.png](./screenshot/chapter2/exe1_01.png)
    + 测试屏幕选转
    ![exe1_02.png](./screenshot/chapter2/exe1_02.png)
    + 重置日志
    ![exe1_03.png](./screenshot/chapter2/exe1_03.png)
+ 任务2
> 页面共5个button，5个textview（其中一个用来显示个数），1个viewgroup（constraint-layout），共计11个view。

![exe2.png](./screenshot/chapter2/exe2.png)
+ 任务3
    + 消息页面
    ![tips.xml](./screenshot/chapter2/exe3_01.png)
    + item跳转页面
    ![it.xml](./screenshot/chapter2/exe3_02.png)
    + 仿照`homework-debug.apk`实现简单的自动回复
    ![auto.png](./screenshot/chapter2/exe3_03.png)
