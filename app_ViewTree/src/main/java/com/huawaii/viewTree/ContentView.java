package com.huawaii.viewTree;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import static com.huawaii.viewTree.MainActivity.DEBUG_ACTIVITY;

/**
 * Copyright (C) 2018 Meizu Telecom Equipment Co., Ltd. All rights reserved.
 *
 * @author lizongheng@meizu.com (李宗恒)
 */
public class ContentView extends RelativeLayout {

    public ContentView(Context context) {
        super(context);
    }

    public ContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(DEBUG_ACTIVITY, "ContentView onFinishInflate.30->");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(DEBUG_ACTIVITY, "ContentView onAttachedToWindow.39->");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(DEBUG_ACTIVITY, "ContentView onMeasure.45->");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(DEBUG_ACTIVITY, "ContentView onSizeChanged.51->");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(DEBUG_ACTIVITY, "ContentView onLayout.57->");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(DEBUG_ACTIVITY, "ContentView onDraw.63->");
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(DEBUG_ACTIVITY, "ContentView onDetachedFromWindow.70->");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d(DEBUG_ACTIVITY, "ContentView onWindowFocusChanged.76->");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.d(DEBUG_ACTIVITY, "ContentView onWindowVisibilityChanged.82->");
    }
}
