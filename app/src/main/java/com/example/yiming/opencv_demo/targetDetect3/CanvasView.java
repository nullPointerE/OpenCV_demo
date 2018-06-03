package com.example.yiming.opencv_demo.targetDetect3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yiming.opencv_demo.R;


public class CanvasView extends View {


    private int width;
    private int height;

    private int mBitmapWidth;
    private int mBitmapHeight;

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap cat2;
    private Matrix matrix;

    private float nndrRatio = 0.7f;//这里设置既定值为0.7，该值可自行调整
    private int matchesPointCount = 0;


    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat1);
        cat2=BitmapFactory.decodeResource(getResources(),R.drawable.cat2);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //平移图像，参数：水平相移量，垂直相移量
        canvas.drawBitmap(mBitmap, matrix, mPaint);
        matrix.postTranslate(mBitmapWidth, mBitmapHeight);
        canvas.drawBitmap(cat2,matrix,mPaint);

//        //重置matrix，以前的状态会消失
//        matrix.reset();
//        //将图像放大，参数：水平放大倍数，垂直放大倍数；如果小于1，则是缩小
//        matrix.postScale(2, 2);
//        canvas.drawBitmap(mBitmap, matrix, mPaint);
//        matrix.reset();
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//
//
//        //将图形选择180度，旋转后的图形位置会发生变化
//        matrix.postRotate(180);
//        matrix.postTranslate(mBitmapWidth*2,mBitmapHeight*2);
//        canvas.drawBitmap(mBitmap, matrix, mPaint);
//
//        //x轴对称
//        matrix.reset();
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        float values[] = {1f,0f,0f,0f,-1f, 0f, 0f, 0f,1f};
//        //此方法作出的处理是：让图片的矩阵与传入的矩阵相乘
//        matrix.setValues(values);
//        matrix.postTranslate(0, mBitmapHeight * 2);
//        canvas.drawBitmap(mBitmap,matrix,mPaint);
//
//        //y轴对称
//        matrix.reset();
//        canvas.drawBitmap(mBitmap,0,0,mPaint);
//        float values2[] = {-1f,0f,1f,0f,1f, 0f, 0f, 0f,1f};
//        matrix.setValues(values2);
//        matrix.postTranslate(mBitmapWidth*2,0);
//        canvas.drawBitmap(mBitmap,matrix,mPaint);
    }


}
