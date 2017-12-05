package com.huawaii.viewDraw.Xfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Copyright (C) 2017 Meizu Telecom Equipment Co., Ltd. All rights reserved.
 *
 * @author lizongheng@meizu.com (李宗恒)
 */

public class ColorfulTextView extends TextView {

    private static Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private int mCurTextColor;

    public ColorfulTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCurTextColor = getCurrentTextColor();
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = getPaint();
        Xfermode xm = p.getXfermode();
        p.setXfermode(sXfermode);
        setCurrentTextColor(0xFF000000);
        super.draw(canvas);

        p.setXfermode(xm);
        setCurrentTextColor(mCurTextColor);
        super.draw(canvas);
    }

    private void setCurrentTextColor(int color) {
        try {
            Field textColor = TextView.class.getDeclaredField("mCurTextColor");
            textColor.setAccessible(true);
            textColor.setInt(this, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
