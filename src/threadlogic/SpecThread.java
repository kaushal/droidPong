package threadlogic;

import gamelogic.MultiplayerLogic;
import gamelogic.SinglePlayerLogic;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class SpecThread extends Thread implements Runnable{
	private SurfaceHolder sh;
	private Paint p;
	private MultiplayerLogic gs = null;
	private SinglePlayerLogic spl = null;
	public MultiplayerLogic getMPGameState(){
			return gs;
	}
	
	public SinglePlayerLogic getSPGameState(){
		return spl;
	}
	public SpecThread(SurfaceHolder sh, Context ctx, Handler hand){
		this.sh = sh; 
		this.p = new Paint();
		if(com.example.droidpong.MainActivity.level == 0)
			this.gs = new MultiplayerLogic();
		else
			this.spl = new SinglePlayerLogic();
	}
	
	public void run(){
		while(true){
			//Canvas c = sh.lockCanvas();
			if(spl == null) {
				Canvas c = sh.lockCanvas();
				
				gs.update();
				gs.draw(c, p);
				
				sh.unlockCanvasAndPost(c);
			}
			else {
				Canvas c = sh.lockCanvas();
				
				spl.update();
				spl.draw(c, p);
				
				sh.unlockCanvasAndPost(c);
			}
		}
	}
}