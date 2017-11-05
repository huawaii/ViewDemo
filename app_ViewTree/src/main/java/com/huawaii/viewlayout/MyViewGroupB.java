package com.huawaii.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MyViewGroupB extends RelativeLayout implements View.OnClickListener{

    public MyViewGroupB(Context context) {
        super(context);
        init();
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroupB(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
        /*setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("click", "ViewGroupB onTouch" + event.getAction());
                return false;
            }
        });*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewGroupB dispatchTouchEvent: " + ev.getAction());
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewGroupB onInterceptTouchEvent: " + ev.getAction());
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewGroupB onTouchEvent: " + event.getAction());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (Debug.DEBUG_EVENT) {
            Log.d("Refresh", "ViewGroupB onClick->78: ");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupB onMeasure->60: ");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupB onLayout->66: ");
        }
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewGroupB onDraw->72: ");
        }
        super.onDraw(canvas);
    }
}
