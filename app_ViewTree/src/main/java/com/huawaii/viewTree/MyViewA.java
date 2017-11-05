package com.huawaii.viewTree;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.huawaii.viewTree.MainActivity.DEBUG_EVENT;
import static com.huawaii.viewTree.MainActivity.DEBUG_LAYOUT;

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
        Log.d(DEBUG_EVENT, "ViewA dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "ViewA onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Log.d(DEBUG_EVENT, "ViewA onClick->69: -------");
        Log.d(DEBUG_LAYOUT, "ViewA onClick->69: -------");
        //requestLayout();
        //invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(DEBUG_LAYOUT, "ViewA onMeasure->51: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(DEBUG_LAYOUT, "ViewA onLayout->57: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewA onDraw->63: ");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewA dispatchDraw.79-> ");
        super.dispatchDraw(canvas);
    }
}
