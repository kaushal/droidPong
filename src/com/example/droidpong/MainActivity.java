package com.example.droidpong;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	public static int level = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intro_screen);
		
		final Button easyButton = (Button) findViewById(R.id.button1);
		easyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                level = 1;
                setContentView(R.layout.pong);
            }
        });
		
		final Button medButton = (Button) findViewById(R.id.button2);
		medButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				level = 2;
				setContentView(R.layout.pong);
			}
		});
		
		
		final Button hardButton = (Button) findViewById(R.id.button3);
		hardButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				level = 3;
				setContentView(R.layout.pong);
			}
		});
		
		final Button multiButton = (Button) findViewById(R.id.button4);
			multiButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setContentView(R.layout.pong);
			}
		});
		
		
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
