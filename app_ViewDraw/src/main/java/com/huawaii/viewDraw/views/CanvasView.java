package com.huawaii.viewDraw.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Copyright (C) 2017 huawaii. All rights reserved.
 *
 * @author huawaii on 2017/11/6.
 */

public class CanvasView extends View {

    private Paint mPaint = new Paint();

    public CanvasView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        testCanvas(canvas);

    }

    private void testCanvas(Canvas canvas) {
        // 绘制点：在坐标(200,200)位置绘制一个点
//        canvas.drawPoint(200, 200, mPaint);

        // 绘制直线：在坐标(300,300)(500,600)之间绘制一条直线
//        canvas.drawLine(300,300,500,600,mPaint);

        // 绘制矩形：
//        canvas.drawRect(100,100,800,400,mPaint);  // 第一种
//        Rect rect = new Rect(100,100,800,400);  // 第二种
//        canvas.drawRect(rect,mPaint);
//        RectF rectF = new RectF(100,100,800,400);   // 第三种
//        canvas.drawRect(rectF,mPaint);

        // 绘制圆角矩形：
//        RectF rectF = new RectF(100,100,800,400); // 第一种
//        canvas.drawRoundRect(rectF,30,30,mPaint);
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);   // 第二种

        // 绘制椭圆：
//        RectF rectF = new RectF(100,100,800,400);   // 第一种
//        canvas.drawOval(rectF,mPaint);
//        canvas.drawOval(100,100,800,400,mPaint);    // 第二种

        // 绘制圆：
//        canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆

        // 绘制圆弧：
//        canvas.drawArc(rectF,0,90,false,mPaint);    // 不使用中心
//        canvas.drawArc(rectF2,0,90,true,mPaint);    // 使用中心
    }
}
