package nl.henriarends.tgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View{

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// bitmap
		Bitmap backgroundbmp = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		canvas.drawBitmap(backgroundbmp, 0, 0, null);
		Bitmap bordbmp = BitmapFactory.decodeResource(getResources(), R.drawable.bord);
		canvas.drawBitmap(bordbmp, 45, 45, null);
		Bitmap player1bmp = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
		canvas.drawBitmap(player1bmp, 330, 990, null);
		Bitmap player2bmp = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
		canvas.drawBitmap(player2bmp, 330, 810, null);
		Bitmap player3bmp = BitmapFactory.decodeResource(getResources(), R.drawable.player3);
		canvas.drawBitmap(player3bmp, 550, 990, null);
		Bitmap player4bmp = BitmapFactory.decodeResource(getResources(), R.drawable.player4);
		canvas.drawBitmap(player4bmp, 550, 810, null);
		invalidate();
	}
	
}
