package com.BlackBerryJuice;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

public class ActivitySplash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		if(Build.VERSION.SDK_INT >= 21) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(this.getResources().getColor(R.color.tameshk_dark));
		}

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);
        
        new CountDownTimer(5000,1000) {
        	
			@Override
			public void onFinish() {
				Intent intent = new Intent(getBaseContext(), ActivityMainMenu.class);
				
				startActivity(intent);

				finish();
				
			}

			@Override
			public void onTick(long millisUntilFinished) {
								
			}
		}.start();
        
    }
}