package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String username = "";
    private String password = "";
    private boolean remember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: 启动！\n");

        EditText v_username = (EditText)findViewById(R.id.username);
        EditText v_password = (EditText)findViewById(R.id.password);

        RadioButton v_remember = (RadioButton)findViewById(R.id.remember);
        v_remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remember = !remember;
                v_remember.setChecked(remember);
            }
        });

        Button v_sign = (Button)findViewById(R.id.sign);
        v_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = v_username.getText().toString();
                password = v_password.getText().toString();
                String msg = "\n注册成功！" +
                        "\n用户名：" + username +
                        "\n密码：" + password +
                        "\n是否记住密码：" + remember + "\n";
                Log.d(TAG, msg);
            }
        });

        Button v_exit = (Button)findViewById(R.id.exit);
        v_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "\n退出！\n");
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }
}
