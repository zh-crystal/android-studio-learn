package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework2.model.Message;
import com.example.homework2.model.PullParser;
import com.example.homework2.util.GreenAdapter;

import java.util.List;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 *
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final String TAG = "recyclerView";
    private int msg_num;
    private GreenAdapter mAdapter;
    private RecyclerView mMsgListView;
    private Toast mToast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        // 获取RecyclerView空间并设置布局管理
        mMsgListView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMsgListView.setLayoutManager(layoutManager);

        //load data from assets/data.xml
        try {
            List<Message> messages = PullParser.pull2xml(getAssets().open("data.xml"));
            msg_num = messages.size();
            // 设置数据适配器
            mAdapter = new GreenAdapter(msg_num, messages, this);
            mMsgListView.setAdapter(mAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(int clickedPosition, String nickName) {
        Log.d(TAG, "onListItemClick: #" + clickedPosition + ": " + nickName);

        // 跳转到聊天窗口
        Bundle bundle = new Bundle();
        bundle.putString("clickedPosition", String.valueOf(clickedPosition));
        bundle.putString("nickName", nickName);
        Intent indent = new Intent(this, ChatroomActivity.class);
        indent.putExtra("nickName", bundle);
        startActivity(indent);
    }
}
