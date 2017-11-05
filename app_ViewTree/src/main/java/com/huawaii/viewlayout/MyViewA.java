package com.huawaii.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyViewA extends View implements View.OnClickListener{

    public MyViewA(Context context) {
        super(context);
        init();
    }

    public MyViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewA(Context context, AttributeSet attrs,
                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
        /*setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("click", "ViewA onTouch" + event.getAction());
                return false;
            }
        });*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewA dispatchTouchEvent: " + event.getAction());
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Debug.DEBUG_EVENT) {
            Log.d("click", "ViewA onTouchEvent: " + event.getAction());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (Debug.DEBUG_EVENT) {
            Log.d("Refresh", "ViewA onClick->69: ");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewA onMeasure->51: ");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewA onLayout->57: ");
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (Debug.DEBUG_LAYOUT) {
            Log.d("Refresh", "ViewA onDraw->63: ");
        }
        super.onDraw(canvas);
    }
}
