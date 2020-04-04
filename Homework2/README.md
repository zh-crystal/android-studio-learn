### 源文件
+ Java文件（app/src/main/java/com/example/homework2）
    + `model`包内含有demo工程中的`Message`类和`PullParser`类，用于xml数据解析
    + `util`包内含有参考demo工程中的自定义Adapter类`GreenAdapter`和图标选择类`IconSelector`用于显示消息前头像
    + `widget`包内含有demo工程中的`CircleImageView`类，用于显示圆形图标
    > 下列5个类需要在`AndroidManifest.xml`中注册
    + `MainActivity.java`处理入口界面逻辑
    + `Exercises1.java`处理任务1逻辑
    + `Exercises2.java`处理任务2逻辑
    + `Exercises3.java`处理任务3消息列表界面逻辑
    + `ChatroomActivity.java`处理任务3消息跳转界面逻辑
+ Layout文件（app/src/main/res/layout）
    + `activity_main.xml`，入口界面
    + `activity_lifecycle.xml`，任务1界面
    + `activity_count.xml`，任务2界面
    + `activity_tips.xml`，任务3消息列表界面
        > 使用了RecyclerView
        + `im_list_item.xml`，消息列表项界面
    + `activity_chatroom.xml`，任务3消息跳转界面
### 打包的apk
+ `app-debug.apk`