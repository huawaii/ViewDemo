package com.huawaii.viewTree;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import static com.huawaii.viewTree.MainActivity.DEBUG_EVENT;
import static com.huawaii.viewTree.MainActivity.DEBUG_LAYOUT;

public class MyViewGroupA extends RelativeLayout implements View.OnClickListener{

    public MyViewGroupA(Context context) {
        super(context);
        init();
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroupA(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
        /*setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("click", "ViewGroupA onTouch" + event.getAction());
                return false;
            }
        });*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(DEBUG_EVENT, "ViewGroupA dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(DEBUG_EVENT, "ViewGroupA onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "ViewGroupA onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Log.d(DEBUG_EVENT, "ViewGroupA onClick->79: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(DEBUG_LAYOUT, "ViewGroupA onMeasure->61: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(DEBUG_LAYOUT, "ViewGroupA onLayout->67: ");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewGroupA onDraw->73: ");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewGroupA dispatchDraw: ");
        super.dispatchDraw(canvas);
    }
}
