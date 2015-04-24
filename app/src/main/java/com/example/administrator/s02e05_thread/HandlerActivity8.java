package com.example.administrator.s02e05_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HandlerActivity8 extends ActionBarActivity {

    private TextView textview;
    private Button button;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler8);

        textview = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);

        WorkerThread st = new WorkerThread();
        st.start();
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            System.out.println("onClick:      " + Thread.currentThread().getName());
            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);
        }
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            //准备looper对象
            Looper.prepare();
            //在WorkerThread里生成一个Handler对象
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("handleMessage:  got message " + Thread.currentThread().getName());
                }
            };
            Looper.loop();
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
