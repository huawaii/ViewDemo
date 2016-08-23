package com.huawaii.viewlayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Log.d("lzh", "dispatchTouchEvent->19: ---------------start");
        //Log.d("lzh", "MainActivity dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("lzh", "onTouchEvent->26: ---------------end");
        //Log.d("lzh", "MainActivity onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
