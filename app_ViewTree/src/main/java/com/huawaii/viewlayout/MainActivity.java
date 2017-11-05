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
        if (Debug.DEBUG_EVENT) {
            //Log.d("click", "---------------start MainActivity dispatchTouchEvent: " + ev.getAction());
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Debug.DEBUG_EVENT) {
            //Log.d("click", "---------------end MainActivity onTouchEvent: " + event.getAction());
        }
        return super.onTouchEvent(event);
    }
}
