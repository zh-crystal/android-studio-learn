package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_exercises1).setOnClickListener(this);
        findViewById(R.id.btn_exercises2).setOnClickListener(this);
        findViewById(R.id.btn_exercises3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exercises1:
                startActivity(new Intent(this, Exercises1.class));
                break;
            case R.id.btn_exercises2:
                startActivity(new Intent(this, Exercises2.class));
                break;
            case R.id.btn_exercises3:
                startActivity(new Intent(this, Exercises3.class));
                break;
        }
    }
}
