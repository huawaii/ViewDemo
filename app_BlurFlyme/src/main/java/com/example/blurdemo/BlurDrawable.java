package com.example.blurdemo;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.view.ViewDebug;

import java.lang.reflect.Method;

public class BlurDrawable extends Drawable {
    public static final float DEFAULT_BLUR_LEVEL = 0.9f;
    public static final int DEFAULT_BLUR_COLOR = 0xD9FFFFFF;
    public static final PorterDuff.Mode DEFAULT_BLUR_COLOR_MODE = PorterDuff.Mode.SRC_OVER;
    public static final Method sDrawBlurRectMethod = getDrawBlurRectMethod();
    @ViewDebug.ExportedProperty(deepExport = true, prefix = "state_")
    private BlurState mState;
    private boolean mMutated;

    /**
     * Creates a new BlurDrawable with default level.
     */
    public BlurDrawable() {
        this(null);
    }

    /**
     * Creates a new BlurDrawable with the specified level.
     *
     * @param level The level to blur.
     */
    public BlurDrawable(float level) {
        this(null);
        setBlurLevel(level);
    }

    private BlurDrawable(BlurState state) {
        mState = new BlurState(state);
        if (state == null) {
            setColorFilter(DEFAULT_BLUR_COLOR, DEFAULT_BLUR_COLOR_MODE);
        }
    }

    public static void drawBlurRect(Canvas canvas, Rect rect, float level, Paint paint) {
        if (sDrawBlurRectMethod != null) {
            try {
                sDrawBlurRectMethod.invoke(canvas, rect, level, paint);
            } catch (Exception e) {
                canvas.drawRect(rect, paint);
            }
        } else {
            canvas.drawRect(rect, paint);
        }
    }

    private static Method getDrawBlurRectMethod() {
        try {
            return Canvas.class.getMethod("drawBlurRect", Rect.class, float.class, Paint.class);
        } catch (Exception e) {
            return null;
        }
    }

    private static int getUseColor(int color, int alpha, float level) {
        float a = (color & 0xFF000000) >>> 24;
        a = a + level * (255 - a);
        a = a * alpha / 255.0f;
        return (((int) a & 0xFF) << 24) | (color & 0xFFFFFF);
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | mState.mChangingConfigurations;
    }

    /**
     * Make this drawable mutable.
     *
     * @return This drawable.
     */
    @Override
    public Drawable mutate() {
        if (!mMutated && super.mutate() == this) {
            mState = new BlurState(mState);
            mMutated = true;
        }
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        if (sDrawBlurRectMethod != null) {
            try {
                sDrawBlurRectMethod.invoke(canvas, getBounds(), mState.mLevel, mState.mPaint);
            } catch (Exception e) {
                canvas.drawRect(getBounds(), mState.mPaint);
            }
        } else {
            canvas.drawRect(getBounds(), mState.mPaint);
        }
    }

    /**
     * Returns the alpha value of this drawable's color.
     *
     * @return A value between 0 and 255.
     */
    /*@Override
    public int getAlpha() {
        return mState.mPaint.getAlpha();
    }*/

    /**
     * Gets the drawable's blur level.
     *
     * @return float The level to blur.
     */
    public float getBlurLevel() {
        return mState.mLevel;
    }

    /**
     * Sets the drawable's blur level. The value of level is 0.0f ~ 1.0f
     *
     * @param level The level to blur.
     */
    public void setBlurLevel(float level) {
        if (mState.mLevel != level) {
            mState.mLevel = level;
            if (updateUseColor()) {
                invalidateSelf();
            }
        }
    }

    /**
     * Sets the color's alpha value.
     *
     * @param alpha The alpha value to set, between 0 and 255.
     */
    public void setAlpha(int alpha) {
        if (mState.mAlpha != alpha) {
            mState.mAlpha = alpha;
            if (updateUseColor()) {
                if (sDrawBlurRectMethod != null) {
                    mState.mPaint.setAlpha(alpha);
                }
                invalidateSelf();
            }
        }
    }

    /**
     * Setting a color filter on a BlurDrawable.
     *
     * @param colorFilter .
     */
    public void setColorFilter(ColorFilter colorFilter) {
        mState.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(int color, PorterDuff.Mode mode) {
        if (sDrawBlurRectMethod == null) {
            if (mState.mBaseColor != color) {
                mState.mBaseColor = color;
                if (updateUseColor()) {
                    invalidateSelf();
                }
            }
        } else {
            super.setColorFilter(color, mode);
        }
    }

    /**
     * It's no sure this can work properbly now
     */
    public void setXfermode(Xfermode xfermode) {
        mState.mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    public int getOpacity() {
        if (sDrawBlurRectMethod == null) {
            switch (mState.mPaint.getAlpha()) {
                case 255:
                    return PixelFormat.OPAQUE;
                case 0:
                    return PixelFormat.TRANSPARENT;
            }
        }
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public ConstantState getConstantState() {
        mState.mChangingConfigurations = getChangingConfigurations();
        return mState;
    }

    private boolean updateUseColor() {
        if (sDrawBlurRectMethod == null) {
            int useColor = getUseColor(mState.mBaseColor, mState.mAlpha, mState.mLevel);
            if (mState.mUseColor != useColor) {
                mState.mUseColor = useColor;
                mState.mPaint.setColor(useColor);
                return true;
            }
            return false;
        }
        return true;
    }

    final static class BlurState extends ConstantState {
        float mLevel = DEFAULT_BLUR_LEVEL;
        Paint mPaint = new Paint();
        int mAlpha = 255;
        int mBaseColor = DEFAULT_BLUR_COLOR;
        int mUseColor = getUseColor(DEFAULT_BLUR_COLOR, 255, DEFAULT_BLUR_LEVEL);
        @ViewDebug.ExportedProperty
        int mChangingConfigurations;

        BlurState(BlurState state) {
            if (state != null) {
                mLevel = state.mLevel;
                mPaint = new Paint(state.mPaint);
                mChangingConfigurations = state.mChangingConfigurations;
            } else {
                if (sDrawBlurRectMethod == null) {
                    mPaint.setColor(mUseColor);
                }
            }
        }

        @Override
        public Drawable newDrawable() {
            return new BlurDrawable(this);
        }

        @Override
        public Drawable newDrawable(Resources res) {
            return new BlurDrawable(this);
        }

        @Override
        public int getChangingConfigurations() {
            return mChangingConfigurations;
        }
    }
}
