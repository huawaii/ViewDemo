package com.huawaii.viewTree;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class MainActivity extends Activity {

    public final static String DEBUG_EVENT = "Click";
    public final static String DEBUG_LAYOUT = "Refresh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(DEBUG_EVENT, "---------------start MainActivity dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "---------------end MainActivity onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}
