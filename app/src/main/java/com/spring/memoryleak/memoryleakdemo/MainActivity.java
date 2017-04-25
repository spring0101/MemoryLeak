package com.spring.memoryleak.memoryleakdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {

    int a = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommenUtil util = CommenUtil.getInstance(this);
        util.sayHello();
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int b = a;
                    Thread.sleep(1000);
                    //do something
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class MyHandle extends Handler{
     private    WeakReference<MainActivity> mainActivityWeakReference ;//设置软引用保存，当内存一发生GC的时候就会回收。
        public MyHandle(MainActivity activity){
            this.mainActivityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mainActivityWeakReference.get();
            if(activity == null || activity.isFinishing())
                return;
            switch (msg.what){
                case 0:
                    int b = activity.a;
                    //do something
                    break;
            }
        }
    };

    private void loadData2(){
        MyHandle handle = new MyHandle(this);
        handle.sendEmptyMessage(0);

    }

}
