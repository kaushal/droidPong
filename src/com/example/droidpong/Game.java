package com.example.droidpong;

import threadlogic.SpecThread;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;




public class Game extends SurfaceView implements SurfaceHolder.Callback2{
	
	SpecThread thread;
	
	public Game(Context ctx, AttributeSet att) {
		
		super(ctx, att); 
		SurfaceHolder sh = getHolder(); // Listening for touch events
		sh.addCallback(this);
		
		// Start the previously defined thread
		thread = new SpecThread(sh, ctx, new Handler());
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		return;
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		thread.start();
	}
	

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		thread.stop();
		
	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if(com.example.droidpong.MainActivity.level == 0)
			return thread.getMPGameState().motionDetected(event);
		else
			return thread.getSPGameState().motionDetected(event);
	}
	
	

	
}
