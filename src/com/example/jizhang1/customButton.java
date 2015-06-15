package com.example.jizhang1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.Button;

public class customButton extends Button {

	private Paint paint;
	private int color;
	private String contentString;
	public customButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customButtonattr);
		TypedArray b = context.obtainStyledAttributes(attrs, R.styleable.customButtonattr);
		color =  a.getColor(R.styleable.customButtonattr_button_color,0xffffffff);
		contentString = a.getString(R.styleable.customButtonattr_button_text);
		paint = new Paint();
		paint.setColor(color);
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
	}
	
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		Path path = new Path();
		path.moveTo(15,0);
		path.lineTo(this.getWidth()-15, 0);
		path.lineTo(this.getWidth(),10);
		path.lineTo(this.getWidth(), this.getHeight()-10);
		path.lineTo(this.getWidth()-15, this.getHeight());
		path.lineTo(15, this.getHeight());
		path.lineTo(0, this.getHeight()-10);
		path.lineTo(0, 10);
		path.lineTo(15, 0);
		canvas.drawPath(path, paint);
		Paint textPaint = new Paint();
		textPaint.setColor(0xffffffff);
		textPaint.setTextSize(this.getHeight()/2);
		textPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(contentString, this.getWidth()/2, this.getHeight()*2/3, textPaint);
	}

}
