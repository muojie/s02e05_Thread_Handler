package com.example.administrator.s02e05_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class HandlerActivity7 extends ActionBarActivity {

    private TextView textview;
    private Button button;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler7);

        textview = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);

        handler = new MyHandler();
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Thread t = new NetworkThread();
            t.start();
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("HandleMessage:         " + Thread.currentThread().getName());
            String s = (String)msg.obj;
            textview.setText(s);
        }
    }

    class NetworkThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //变量S的值，模拟从网络中获得的。
            String s = "从网络中获得的数据";
            Message msg = handler.obtainMessage();
            msg.obj = s;
            handler.sendMessage(msg);
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
