package com.hagan.interviewpoint;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Handler mHandler;
    private static final int MSG_SUB_TO_MAIN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn_update).setOnClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                updateUI(msg);
            }
        };
    }

    private void updateUI(Message msg) {
        String content = (String) msg.obj;
        tv.setText(content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = MSG_SUB_TO_MAIN;
                        msg.obj = "注意一下子啊，更新啦要";
                        mHandler.sendMessage(msg);
                    }
                }).start();
                break;
        }
    }
}