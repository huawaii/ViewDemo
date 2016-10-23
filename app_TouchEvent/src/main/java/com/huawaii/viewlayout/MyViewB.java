package com.huawaii.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyViewB extends View implements View.OnClickListener{

    public MyViewB(Context context) {
        super(context);
        init();
    }

    public MyViewB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewB(Context context, AttributeSet attrs,
                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
        /*setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("click", "ViewB onTouch" + event.getAction());
                return false;
            }
        });*/
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("click", "ViewB onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("click", "ViewB dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("Refresh", "onMeasure->51: ViewB");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("Refresh", "onLayout->57: ViewB");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Refresh", "onDraw->63: ViewB");
        super.onDraw(canvas);
    }

    @Override
    public void onClick(View v) {
        Log.d("Refresh", "onClick->69: ViewB");
        //requestLayout();
        //invalidate();
    }
}
