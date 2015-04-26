package com.example.administrator.s02e05_thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hong on 2015/4/25.
 */

//S02E08 Handler 三（上）
// ThreadLocal
// Looper -> Thread（Message) -> Handler
//一个Handler对象对应一个Looper对象，一个Looper对象对应一个MessageQueue对象
//使用Handler对象生成Message，所生成的Message对象的target属性就是Handler对象。

// Looper: new MessageQueue(); ThreadLocal().set(new Looper())

public class HandlerActivity8Sub extends Activity{
    private Handler handler = new Handler();
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler8sub);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new testThread();
                t.start();
            }
        });
    }

    class testThread extends Thread {
        @Override
        public void run() {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    //这里可以写上更新UI的代码，因为这个是跑在主线程里的。
                    textView.setText("sync by main thread");
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println("thread name:                 " + currentThreadName);
                }
            };
            handler.post(r);
        }
    }
}
