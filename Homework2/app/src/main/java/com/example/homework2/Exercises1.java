package com.example.homework2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private static final String TAG = "lifecycle";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    private TextView mLifecycleDisplay;

    private static String logMsg=""; // 静态变量保存ON_STOP、ON_DESTROY信息

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        mLifecycleDisplay = findViewById(R.id.tv_loglifecycle);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                mLifecycleDisplay.setText((String)savedInstanceState.get(LIFECYCLE_CALLBACKS_TEXT_KEY));
                mLifecycleDisplay.append(logMsg); // 添加UI不可见时保存的信息
            }
        }
        logAndAppend(ON_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
        // 清空历史消息，保证只记录重建activity的消息#ON_STOP#ON_DESTROY
        logMsg = "";
    }

    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);
        logMsg += ON_STOP + "\n";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY);
        logMsg += ON_DESTROY + "\n";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, mLifecycleDisplay.getText().toString());
    }

    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("lifecycle callbacks:");
    }
}
