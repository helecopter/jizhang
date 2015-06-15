package com.example.jizhang1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by zheng on 15-6-2.
 */
public class customEditText extends EditText {

    private Paint mPaint;
    private int color;

    public customEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.customEditTextattr);
        color = a.getColor(R.styleable.customEditTextattr_line_color,0XFFFFFFFF);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(2);
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, mPaint);
        canvas.drawLine(0,this.getHeight()-10,0,this.getHeight()-1,mPaint);
        canvas.drawLine(this.getWidth()-1,this.getHeight()-10,this.getWidth()-1,this.getHeight()-1,mPaint);
    }
}
