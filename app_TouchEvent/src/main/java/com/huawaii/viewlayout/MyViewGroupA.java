package com.huawaii.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
        Log.d("click", "----------------start---------------");
        Log.d("click", "ViewGroupA dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("click", "ViewGroupA onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("click", "ViewGroupA onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("Refresh", "onMeasure->61: ViewGroupA");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("Refresh", "onLayout->67: ViewGroupA");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Refresh", "onDraw->73: ViewGroupA");
        super.onDraw(canvas);
    }

    @Override
    public void onClick(View v) {
        Log.d("Refresh", "onClick->79: ViewGroupA");
        //requestLayout();
        //invalidate();
    }
}
