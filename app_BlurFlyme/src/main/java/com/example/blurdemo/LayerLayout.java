package com.example.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by zhouxiang on 16/10/8.
 */
public class LayerLayout extends FrameLayout {

    private Paint mLayerPaint = new Paint();
    private int mLayerColor = 0xCC000000;

    public LayerLayout(Context context) {
        super(context);
        init();
    }

    public LayerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mLayerPaint.setStyle(Paint.Style.FILL);
        mLayerPaint.setColor(mLayerColor);
    }

    @Override
    public void draw(Canvas canvas) {
        float left = getLeft();
        float top = getTop();
        float right = getRight();
        float bottom = getBottom();
        canvas.saveLayer(left, top, right, bottom, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawRect(left, (top + bottom) / 4, right, bottom, mLayerPaint);

        super.draw(canvas);

        canvas.restore();
    }
}
