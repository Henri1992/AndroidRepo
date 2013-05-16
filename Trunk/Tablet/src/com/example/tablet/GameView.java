package com.example.tablet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GameView extends View{
	
	public GameView(Context context){
		super(context);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
		super.onDraw(canvas);
		Paint green = new Paint();
		green.setColor(Color.GREEN);
		green.setStyle(Paint.Style.FILL);
		Rect ourRect = new Rect();
		ourRect.set(0, 0, canvas.getWidth()/2, 180);
		canvas.drawRect(ourRect, green);
	}

}
