# AndroidCallback

Android回调练习


xyzCallback.java
```
package com.xuyazhong.androidcallback;

public interface xyzCallback {

    void invoke(String message);

}
```

Person.java

```
package com.xuyazhong.androidcallback;

import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class Person {
    String TAG = "#Person#";
    private xyzCallback callback;

    Person() {

    }

    public void work(String name, xyzCallback callback) {

        this.callback = callback;

        Log.e(TAG, "begin" + name + "开始工作");

        new xyzAsyncTask().execute(name);

    }

    class xyzAsyncTask extends AsyncTask<String, Void, String> {
        private final Object mLock = new Object();

        //onPreExecute用于异步处理前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //在doInBackground方法中进行异步任务的处理.
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            Log.e(TAG, name + "开始计算");
            try {
                synchronized (mLock) {
                    int result = 0;
                    for (int i = 0;i <= 100; i++) {
                        result += i;
                    }
                    Log.e(TAG, "算出来啦" + String.valueOf(result));
                    return String.valueOf(result);
                }
            } catch (Exception e) {
                Log.e(TAG, name + "没有算出来");
                return "没有算出来";
            }
        }

        //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
        @Override
        protected void onPostExecute(String result) {
            execResult(result);
        }
    }

    private void execResult(String result) {
        invokeSuccessWithResult(result);
    }

    /**
     * 识别成功时触发
     *
     */
    private void invokeSuccessWithResult(String result) {
        if (this.callback != null) {
            this.callback.invoke(result);
            this.callback = null;
        }
    }

}
```

调用

```
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
```


#打印结果 

#MainActivity#: 小明准备工作啦
#Person#: begin小明开始工作
#MainActivity#: 小明计算中
#Person#: 小明开始计算
#Person#: 算出来啦4950
#MainActivity#: 小明算出的结果4950

