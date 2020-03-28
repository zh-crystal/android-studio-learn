package com.example.homework2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        View root_count = findViewById(R.id.root_count);
        TextView view_count = findViewById(R.id.view_count);
        view_count.setText(String.valueOf(getAllChildViewCount(root_count)));
    }

    public int getAllChildViewCount(View view) {
        int viewCount = 0;

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LinkedList<ViewGroup> queue = new LinkedList<>();
            queue.add(viewGroup);
            while (!queue.isEmpty()) {
                ViewGroup current = queue.removeFirst();
                viewCount++;
                for (int i = 0; i < current.getChildCount(); i++) {
                    if (current.getChildAt(i) instanceof ViewGroup) {
                        queue.addLast((ViewGroup) current.getChildAt(i));
                    } else {
                        viewCount++;
                    }
                }
            }
        } else {
            viewCount++;
        }

        return viewCount;
    }
}
