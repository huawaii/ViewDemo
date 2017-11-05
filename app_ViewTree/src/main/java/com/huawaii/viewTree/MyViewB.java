package com.huawaii.viewTree;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.huawaii.viewTree.MainActivity.DEBUG_EVENT;
import static com.huawaii.viewTree.MainActivity.DEBUG_LAYOUT;

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
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "ViewB dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DEBUG_EVENT, "ViewB onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Log.d(DEBUG_EVENT, "ViewB onClick->69: -------");
        Log.d(DEBUG_LAYOUT, "ViewB onClick->69: -------");
        //requestLayout();
        //invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(DEBUG_LAYOUT, "ViewB onMeasure->51: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(DEBUG_LAYOUT, "ViewB onLayout->57: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewB onDraw->63: ");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewB dispatchDraw.79-> ");
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.d(DEBUG_LAYOUT, "ViewB draw.88-> ");
        super.draw(canvas);
    }
}
