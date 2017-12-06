package com.huawaii.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
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
    private float mLevel = 0.5f;

    public GLBlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = new GLBlurDrawable(true);
    }

    public void setLevel(float level) {
        mLevel = level;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawable.setBlurLevel(mLevel);
        mDrawable.setBounds(0, 0, getWidth(), getHeight());
        mDrawable.draw(canvas);
    }
}
