package com.mycolor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ColorView extends ImageView {

    private Bitmap mBitmap = null;
    private Paint mPaint = new Paint(); //新建画笔对象
    private ColorMatrix myColorMatrix = new ColorMatrix();  //新建颜色矩阵对象
    private float[] colorArray = {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0};

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a2);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //描画（原始图片）
        //canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        //设置颜色矩阵的值
        myColorMatrix.set(colorArray);
        //设置画笔颜色过滤器
        mPaint.setColorFilter(new ColorMatrixColorFilter(myColorMatrix));
        //描画（处理后的图片）
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    //设置颜色数值
    public void setColorArray(float[] colorArray) {
        this.colorArray = colorArray;
        invalidate();
    }

    //设置图片
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }
}
