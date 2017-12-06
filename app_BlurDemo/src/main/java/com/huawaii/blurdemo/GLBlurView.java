package com.huawaii.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.meizu.common.renderer.drawable.GLBlurDrawable;

/**
 * Copyright (C) 2017 Meizu Telecom Equipment Co., Ltd. All rights reserved.
 *
 * @author lizongheng@meizu.com (李宗恒)
 */

public class GLBlurView extends View {

    private GLBlurDrawable mDrawable;
    private float mLevel = 1.0F;
    private int mRadius = 4;
    private float mScale = 0.06F;   //0.001F, 1.01F
    private final Path mPath;

    public GLBlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = new GLBlurDrawable(true);
        mPath = new Path();
        mDrawable.setBlurLevel(0.5f);
        //mDrawable.setRadius(mRadius);
        //mDrawable.setScale(mScale);
    }

    public void setLevel(float level) {
        mLevel = level;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.addCircle(getWidth()/2, getHeight()/2, getWidth()/2, Path.Direction.CW);
        canvas.clipPath(mPath);
        mDrawable.setBounds(0, 0, getWidth(), getHeight());
        mDrawable.draw(canvas);
    }
}
