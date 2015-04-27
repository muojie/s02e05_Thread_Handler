package com.example.administrator.s02e05_thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HandlerActivity extends Activity {

    private Button button;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        button = (Button)findViewById(R.id.button);
        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);

        handler = new FirstHandler();
    }

    //在一个应用程序里，主线程（Main Thread）通常用于接收用户的输入，以及将运算的结果反馈给用户。
    //所以说对于一些可能会产生阻塞的操作，必须放到Worker Thread当中。

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Message msg = handler.obtainMessage();
            msg.what = 2;
            handler.sendMessage(msg);
        }
    }

    class FirstHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            System.out.println("what: " + what);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
