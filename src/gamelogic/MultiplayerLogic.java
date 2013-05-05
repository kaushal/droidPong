package gamelogic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MultiplayerLogic {

	//screen width and height
	final int screenWidth = 600;
	final int screenHeight = 840;

	
	//The ball
	final int ballSize = 10;
	int ballX = 500; 	int ballY = 500;
	int ballVelocityX = 6; 	int ballVelocityY = 6;

	//The bats	
	final int batLength = 75;	final int batHeight = 10;
	int topBatX = (screenWidth/2) - (batLength / 2);
	final int topBatY = 10;
	int bottomBatX = (screenWidth/2) - (batLength / 2);	
	final int bottomBatY = 830;


	
	
	//The update method
	public void update() {

	ballX += ballVelocityX;
	ballY += ballVelocityY;

	//DEATH!
	if(ballY > screenHeight || ballY < 0){
		ballX = 100; 	ballY = 100;
		}  	//Collisions with the sides

	if(ballX > screenWidth - 100 || ballX < 0)
	     	ballVelocityX *= -1; 	//Collisions with the bats     	

	if(ballX > topBatX && ballX < topBatX+batLength && ballY < topBatY)     	
			ballVelocityY *= -1;  //Collisions with the bats     	

	if(ballX > bottomBatX && ballX < bottomBatX + batLength && ballY > bottomBatY)
	        ballVelocityY *= -1;
	}

	public boolean motionDetected(MotionEvent event) {
		boolean a = false;
		for(int i = 0; i < event.getPointerCount(); i++){
			float x = event.getX(i);
			if(event.getY(i) <= screenHeight/2){
				topBatX = (int)(x - 37);
				a = true;
			}
			else{
				bottomBatX = (int) (x - 37);
				a = true;
			}
		}
		
		
		
		return true;
	}
	


	//the draw method
	public void draw(Canvas canvas, Paint paint) {

	//Clear the screen
	canvas.drawRGB(20, 20, 20);

	//set the colour
	paint.setARGB(200, 0, 200, 0);

	//draw the ball
	canvas.drawRect(new Rect(ballX,ballY,ballX + ballSize,ballY + ballSize),
	                             paint);

	//draw the bats
	canvas.drawRect(new Rect(topBatX, topBatY, topBatX + batLength, topBatY + batHeight), paint); //top bat
	canvas.drawRect(new Rect(bottomBatX, bottomBatY, bottomBatX + batLength, bottomBatY + batHeight), paint); //bottom bat

	}
	}