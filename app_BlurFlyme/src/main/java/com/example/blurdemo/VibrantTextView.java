package com.example.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhouxiang on 16/10/9.
 */
public class VibrantTextView extends TextView {

    private static Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public VibrantTextView(Context context) {
        super(context);
    }

    public VibrantTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VibrantTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = getPaint();
        Xfermode xm = p.getXfermode();
        p.setXfermode(sXfermode);

        super.draw(canvas);

        p.setXfermode(xm);

        super.draw(canvas);
    }
}
