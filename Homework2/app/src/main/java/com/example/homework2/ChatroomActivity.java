package com.example.homework2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChatroomActivity extends AppCompatActivity {

    private final static String TAG = "Chatroom";

    private TextView tvWithName;
    private TextView tvContentInfo;
    private EditText edSay;
    private ImageView btnSendInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ChatroomActivity: #onCreate");
        setContentView(R.layout.activity_chatroom);

        tvWithName = findViewById(R.id.tv_with_name);
        edSay = findViewById(R.id.ed_say);
        tvContentInfo = findViewById(R.id.tv_content_info);
        btnSendInfo = findViewById(R.id.btn_send_info);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("nickName");
            if (bundle != null) {
                tvWithName.setText("我和" + bundle.getString("nickName") + "的对话" + " #" + bundle.getString("clickedPosition"));
            }
        }

        edSay.setText("");
        btnSendInfo.setEnabled(false);

        edSay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSendInfo.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnSendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edSay.getText().toString();
                edSay.setText("");

                // 仿照示例进行简单的自动回复
                tvContentInfo.append(message + "\n");
                message = message.replace("你", "我");
                message = message.replace("吗", "");
                message = message.replace("？", "");
                message = message.replace("?", "");
                tvContentInfo.append(message + "\n");

                btnSendInfo.setEnabled(false);
            }
        });


    }
}
