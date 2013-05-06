package gamelogic;

import com.example.droidpong.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Looper;
import android.view.MotionEvent;

public class SinglePlayerLogic {
	// top and bottom of both paddles
	// screen height and width
	// size of the ball for shenanigans
	// ball velocity in x and y

	int ballSize = (4 - com.example.droidpong.MainActivity.level) * 10;

	int heightOfScreen = 500;
	int widthOfScreen = 840;
	int topScore = 0;
	int bottomScore = 0;
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
	
	Context ctx;
	
	public SinglePlayerLogic(Context ctx){
		this.ctx = ctx;
	}
	
	


	public void update() {
		ballx = ballx + ballXVelocity;
		bally = bally + ballYVelocity;

		if(ballx > topPaddleX && topPaddleX <= heightOfScreen)
			topPaddleX += (com.example.droidpong.MainActivity.level);
		else if(ballx < topPaddleX && topPaddleX >= 0)
			topPaddleX -= (com.example.droidpong.MainActivity.level);

		if((bally + ballSize/2) < 95){ //Loosing
			bottomScore += 1;
			ballx = 250;
			bally = 420;
			ballYVelocity *= -1;
		}
		else if((bally - ballSize/2) > 745){
			topScore += 1;
			ballx = 250;
			bally = 420;
			ballYVelocity *= -1;
		}

		else if((ballx + ballSize/2) >= heightOfScreen|| (ballx - ballSize/2) <= 0){
			ballXVelocity *= -1;
		}

		else if((bally - ballSize/2) <= 105 && (ballx + ballSize/2) <= (topPaddleX + paddleLength/2) && (ballx - ballSize/2) >= (topPaddleX - paddleLength/2)){
			ballYVelocity *= -1;
		}
		else if((bally + ballSize/2) >= 735 && (ballx + ballSize/2) <= (bottomPaddleX + paddleLength/2) && (ballx - ballSize/2) >= (bottomPaddleX - paddleLength/2)){
			ballYVelocity *= -1;
			System.out.println(bally);
		}

	}

	public boolean motionDetected(MotionEvent event) {
		bottomPaddleX = (int) event.getX() - paddleLength/2;
		return true;
	}
	
	@SuppressWarnings("deprecation")

	public void draw(Canvas canvas, Paint paint) {
		canvas.drawRGB(20, 20, 20);
		paint.setARGB(200, 255, 255,255);

		canvas.drawRect(new Rect(ballx - ballSize/2,bally + ballSize/2,ballx + ballSize/2,bally - ballSize/2),paint);

		canvas.drawRect(new Rect(topPaddleX - paddleLength/2, topPaddleY + paddleWidth/2, topPaddleX + paddleLength/2, topPaddleY - paddleWidth/2), paint); //top Paddle
		canvas.drawRect(new Rect(bottomPaddleX - paddleLength/2, bottomPaddleY + paddleWidth/2, bottomPaddleX + paddleLength/2, bottomPaddleY - paddleWidth/2), paint); //bot
		paint.setStrokeWidth(4);
		Paint newPaint = new Paint();
		newPaint.setARGB(200, 255, 255,255);


		newPaint.setTextSize(50);
		canvas.drawText(topScore + "", 450, 410, newPaint);
		canvas.drawText(bottomScore + "", 450, 465, newPaint);
		canvas.drawLine(0, 420, 500, 420, paint);
		canvas.drawLine(0, 0, 0, 840, paint);
		canvas.drawLine(0, 0, 500, 0, paint);
		canvas.drawLine(0, 840, 500, 840, paint);
		canvas.drawLine(500, 0, 500, 840, paint);
		
		Paint winText = new Paint();
		winText.setARGB(200, 255, 255,255);
		
		
		winText.setTextSize(70);
		if(topScore >= 5){
			winText.setARGB(200, 255, 0, 0);
			canvas.drawText("Computer Wins!", 5, 200, winText);
			ballXVelocity = 0;
			ballYVelocity = 0;
		}else if(bottomScore >= 5){
			winText.setARGB(200, 0, 255, 0);
			canvas.drawText("  Human Wins!", 5, 200, winText);
			ballXVelocity = 0;
			ballYVelocity = 0;
		}
	}

}
