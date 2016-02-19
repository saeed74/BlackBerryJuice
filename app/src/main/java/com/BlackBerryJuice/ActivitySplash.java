package com.BlackBerryJuice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

public class ActivitySplash extends Activity {

	public static Typeface F1,F2,F3,F4,F5,F6;

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

		F1=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
		F2=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Medium.ttf");
		F3=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Bold.ttf");
		F4=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_UltraLight.ttf");
		F5=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile.ttf");
		F6=Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light_Persian_Digits.ttf");
        
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