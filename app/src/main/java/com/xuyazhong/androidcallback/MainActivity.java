package com.xuyazhong.androidcallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG = "#MainActivity#";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person = new Person();

        final String name = "小明";

        // 回调
        xyzCallback callback = new xyzCallback() {
            @Override
            public void invoke(String message) {
                Log.e(TAG, name + "算出的结果" + message);
            }
        };

        Log.e(TAG, name + "准备工作啦");

        person.work(name, callback);

        Log.e(TAG, name + "计算中");


    }
}
