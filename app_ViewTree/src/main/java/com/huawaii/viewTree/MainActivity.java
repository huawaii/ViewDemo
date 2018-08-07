package com.huawaii.viewTree;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;


public class MainActivity extends Activity {

    public final static String DEBUG_EVENT = "Click";
    public final static String DEBUG_LAYOUT = "Refresh";
    public final static String DEBUG_ACTIVITY = "Activity";

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "---------------onGenericMotionEvent: " + event.getAction());
        return super.onGenericMotionEvent(event);
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onCreate.41-> ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onStart.48->");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onRestart.54->");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onResume.60->");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onPostResume.66->");
        super.onPostResume();
    }


    @Override
    public void onAttachedToWindow() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onAttachedToWindow.126->");
        super.onAttachedToWindow();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onWindowFocusChanged.120->");
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onPause.78->");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onStop.84->");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onDestroy.90->");
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onDetachedFromWindow.132->");
        super.onDetachedFromWindow();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onConfigurationChanged.96->");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onApplyThemeResource.138->");
        super.onApplyThemeResource(theme, resid, first);
    }

    @Override
    public void onContentChanged() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onContentChanged.114->");
        super.onContentChanged();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onNewIntent.72->");
        super.onNewIntent(intent);
    }


    @Override
    public void onLowMemory() {
        Log.d(DEBUG_ACTIVITY, "MainActivity onLowMemory.102->");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(DEBUG_ACTIVITY, "MainActivity onTrimMemory.108-> level:" + level);
        super.onTrimMemory(level);
    }
}
