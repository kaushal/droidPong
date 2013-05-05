package gamelogic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class SinglePlayerLogic {
	// top and bottom of both paddles
	// screen height and width 
	// size of the ball for shenanigans
	// ball velocity in x and y
	
	int ballSize = (4 - com.example.droidpong.MainActivity.level) * 10;
	
	int heightOfScreen = 500;
	int widthOfScreen = 840;
	
	int ballx = 250; 
	int bally = 250;
	
	
	int ballXVelocity = 3;//(int)Math.sin((Math.random() * 6.2));
	int ballYVelocity = 3;//(int)Math.sin((Math.random() * 6.2));
	
	int paddleLength = 75;
	int paddleWidth = 10;
	
	int topPaddleX = (widthOfScreen / 2) - (paddleLength /2); 
	int bottomPaddleX = topPaddleX;
	
	int topPaddleY = 100;
	int bottomPaddleY = 740;
	
	int bottomOfTopPaddle = 105;
	int topOfBottomPaddle = 735;
	
	
	public void update() {
		if(ballx > topPaddleX && topPaddleX <= heightOfScreen)
			topPaddleX += (com.example.droidpong.MainActivity.level);
		else if(ballx < topPaddleX && topPaddleX >= 0)
			topPaddleX -= (com.example.droidpong.MainActivity.level);
		
		ballx = ballx + ballXVelocity;
		bally = bally + ballYVelocity;
		
		if((bally + ballSize/2) < 95 || (bally - ballSize/2) > 745){ //Loosing
			ballx = 250;
			bally = 250;
			ballYVelocity *= -1;
		}
		
		if(ballx >= heightOfScreen|| ballx <= 0){
			ballXVelocity *= -1;
		}
		
		if((bally + ballSize/2) <= 105 && (ballx + ballSize/2) <= (topPaddleX + paddleLength/2) && (ballx - ballSize/2) >= (topPaddleX - paddleLength/2)){
			ballYVelocity *= -1;
		}
		if((bally - ballSize/2) >= 735 && (ballx + ballSize/2) <= (bottomPaddleX + paddleLength/2) && (ballx - ballSize/2) >= (bottomPaddleX - paddleLength/2)){
			ballYVelocity *= -1;
		}
		
		
		//top of the ball connecting wiht the bottom of the top paddle
		
		
		
	}
	
	public boolean motionDetected(MotionEvent event) {
		bottomPaddleX = (int) event.getX() - paddleLength/2;
		return true;
	}
	
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawRGB(20, 20, 20);
		paint.setARGB(200, 0, 200, 0);
		
		canvas.drawRect(new Rect(ballx - ballSize/2,bally + ballSize/2,ballx + ballSize/2,bally - ballSize/2),paint);
		
		canvas.drawRect(new Rect(topPaddleX - paddleLength/2, topPaddleY + paddleWidth/2, topPaddleX + paddleLength/2, topPaddleY - paddleWidth/2), paint); //top Paddle
		canvas.drawRect(new Rect(bottomPaddleX - paddleLength/2, bottomPaddleY + paddleWidth/2, bottomPaddleX + paddleLength/2, bottomPaddleY - paddleWidth/2), paint); //bot
	}
	

}
