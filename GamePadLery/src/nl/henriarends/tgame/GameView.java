package nl.henriarends.tgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View{
	
	int x;
	int y;
	int aantalOgen = 0;
	boolean player1 = false;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		x = 0;
		y = 0;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		player1 = true;
		aantalOgen = 6;
		
		if (player1 = true)
		{
			switch(aantalOgen)
			{
				case 1: // 1 opzij of 1 omhoog of 1 omlaag
						// welke pion
						// locatie oude pion (x,y)
						// pion verplaatsen (x,y)
					break;
				case 2: // 2 opzij of 2 omhoog of 2 omlaag of 1 opzij en 1 omhoog of 1 opzij en 1 omlaag
					break;
				case 3: // 3 opzij of 3 omhoog of 3 omlaag of 1 opzij en 2 omhoog of 1 opzij en 2 omlaag 
					// 2 opzij en 1 omhoog of 2 opzij en 1 omlaag
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					if (y < 200)
					{
						x = 50;
						y += 10;
					}
					else
					{
						x = 50;
						y = 200;
					}
					Bitmap pion1 = BitmapFactory.decodeResource(getResources(), R.drawable.playerblackpion1);
					canvas.drawBitmap(pion1, x, y, null);
					invalidate();
					break;
			}
		}
	}
	
}
