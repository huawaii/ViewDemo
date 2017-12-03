package com.huawaii.viewDraw.Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.huawaii.viewDraw.R;

/**
 * Copyright (C) 2017 huawaii. All rights reserved.
 *
 * @author huawaii on 2017/11/6.
 */

/*
设置颜色的几种方法：
1、Canvas.drawColor/ARGB()   颜色参数
2、Canvas.drawBitmap()       Bitmap自带颜色参数
3、Canvas.draw***()
    Paint.setColor()、Paint.setShader()：用来给图形和文字设置颜色
    Paint.setColorFilter()：用来基于颜色进行过滤处理
    Paint.setXfermode()：用来处理源图像和 View 已有内容的关系(叠加)
*/
public class CanvasView extends View {

    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Bitmap mBitmap;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.panda1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //initPaintPrimary();
        initPaintAdvanced();
        testCanvas(canvas);
    }

    /**
     * Paint 设置图形的公有信息
     */
    private void initPaintPrimary() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为画线（FILL 填充）
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);          //设置画笔抗锯齿
    }

    /**
     * Shader   设置着色器 setShader()
     * PorterDuff.Mode  指定两个图像共同绘制时的颜色策略，一共17个，一类Alpha 合成、一类混合 (Blending)
     * ColorFilter  设置颜色过滤 setColorFilter()
     */
    private void initPaintAdvanced() {
        /** 渐变着色器   LinearGradient RadialGradient SweepGradient */
        //Shader shader1 = new LinearGradient(100, 100, 500, 500,
        //        Color.parseColor("#E91E63"), Color.parseColor("#2196F3"),
        //        Shader.TileMode.CLAMP);
        /** 图片着色器   BitmapShader */
        //Shader shader2 = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        /** 混合着色器   ComposeShader */
        //Shader shader = new ComposeShader(shader2, shader1, PorterDuff.Mode.LIGHTEN);
        //mPaint.setShader(shader);

        /** 颜色过滤器   LightingColorFilter PorterDuffColorFilter ColorMatrixColorFilter */
        ColorFilter colorFilter = new LightingColorFilter(0xff00ff, 0x000000);
        //ColorFilter colorFilter = new PorterDuffColorFilter(0xFF2222, PorterDuff.Mode.XOR);
        mPaint.setColorFilter(colorFilter);
    }

    /**
     * Canvas 设置图形的特有信息
     * @param canvas
     */
    private void testCanvas(Canvas canvas) {
        /**填充颜色*/
        canvas.drawColor(Color.parseColor("#22880000"));
        //canvas.drawARGB(88, 88, 00, 00);

        /**绘制点：在坐标(200,200)位置绘制一个点*/
        //canvas.drawPoint(200, 200, mPaint);

        /**绘制直线：在坐标(300,300)(500,600)之间绘制一条直线*/
        //canvas.drawLine(300,300,500,600,mPaint);

        /**绘制矩形：*/
        //canvas.drawRect(100,100,800,400,mPaint);  // 第一种
        //Rect rect = new Rect(100,100,800,400);  // 第二种
        //canvas.drawRect(rect,mPaint);
        //RectF rectF = new RectF(100,100,800,400);   // 第三种
        //canvas.drawRect(rectF,mPaint);

        /**绘制圆角矩形：*/
        //RectF rectF = new RectF(100,100,600,400); // 第一种
        //canvas.drawRoundRect(rectF,30,30,mPaint);
        //canvas.drawRoundRect(100,100,600,400,30,30,mPaint);   // 第二种

        /**绘制椭圆：*/
        //RectF rectF = new RectF(100,100,800,400);   // 第一种
        //canvas.drawOval(rectF,mPaint);
        //canvas.drawOval(100,100,800,400,mPaint);    // 第二种

        /**绘制圆：*/
        //canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆

        /**绘制圆弧：*/
        //RectF rectF = new RectF(100,100,800,400);
        //canvas.drawArc(rectF,0,90,false,mPaint);    // 不使用中心
        //canvas.drawArc(rectF,0,90,true,mPaint);    // 使用中心

        /**绘制路径：*/
        //initPath();
        //canvas.drawPath(mPath, mPaint);

        /** 绘制Bitmap*/
        canvas.drawBitmap(mBitmap, 200, 100, mPaint);

        /** 绘制文字*/
        //mPaint.setTextSize(100);
        //canvas.drawText("huawaii", 200, 100, mPaint);
    }


    /**
     * Path 描述图形路径
     * 第一组：add*** 添加子图形    圆、椭圆、矩形、path
     * 第二组：***To  画线
     *        lineTo\rLineTo：画直线  quadTo\rQuadTo：画贝二次塞尔曲线 cubicTo\rCubicTo：画贝三次塞尔曲线
     *        moveTo：移动到目标位置    close：封闭当前子图形
     *        arcTo\addArc：画弧线（不依赖当前位置，即不需要moveTo）
     *
     * Path 辅助的设置或计算
     * setFillType(Path.FillType ft) 设置填充方式，需理解 EVEN_ODD 和 WINDING 的原理
     */
    private void initPath() {
        /** 画心形*/
        //mPath.addArc(200, 200, 400, 400, -225, 225);
        //mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        //mPath.lineTo(400, 542);
        /** 画圆形*/
        mPath.addCircle(300, 300, 200, Path.Direction.CCW);
    }
}
