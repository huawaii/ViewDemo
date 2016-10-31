package com.example.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhouxiang on 16/10/9.
 */
public class VibrantImageView extends ImageView {

    private static Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public VibrantImageView(Context context) {
        super(context);
    }

    public VibrantImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VibrantImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void draw(Canvas canvas) {
        Drawable d = getDrawable();
        if (d instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) d;
            Paint p = bd.getPaint();
            Xfermode xm = p.getXfermode();
            p.setXfermode(sXfermode);

            super.draw(canvas);

            p.setXfermode(xm);
        }

        super.draw(canvas);
    }
}
