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
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "----------------start---------------");
            Log.d("click", "ViewGroupA dispatchTouchEvent: " + ev.getAction());
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewGroupA onInterceptTouchEvent: " + ev.getAction());
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewGroupA onTouchEvent: " + event.getAction());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (Debug.DEBUG_EVENT) {
            Log.d("Refresh", "ViewGroupA onClick->79: ");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupA onMeasure->61: ");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupA onLayout->67: ");
        }
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupA onDraw->73: ");
        }
        super.onDraw(canvas);
    }
}
