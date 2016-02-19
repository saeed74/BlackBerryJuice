package com.BlackBerryJuice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

public class ActivityMainMenu extends Activity {
	static DBHelper dbhelper;
	AdapterMainMenu mma;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		if(Build.VERSION.SDK_INT >= 21) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(this.getResources().getColor(R.color.tameshk_dark));
		}
		setContentView(R.layout.main_layout);

		RelativeLayout order = (RelativeLayout) findViewById(R.id.Order_Cat_Button);
		order.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				startActivity(new Intent(ActivityMainMenu.this, ActivityCategoryList.class));
				overridePendingTransition (R.anim.open_next, R.anim.close_next);
			}
		});



		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		// checking internet connection
		if (!Constant.isNetworkAvailable(ActivityMainMenu.this)) {
			Toast.makeText(ActivityMainMenu.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
		}

		mma = new AdapterMainMenu(this);
		dbhelper = new DBHelper(this);

		// create database
		try {
			dbhelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		// then, the database will be open to use
		try {
			dbhelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		// if user has already ordered food previously then show confirm dialog
		if (dbhelper.isPreviousDataExist()) {
			showAlertDialog();
		}

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}


	// show confirm dialog to ask user to delete previous order or not
	void showAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.confirm);
		builder.setMessage(getString(R.string.db_exist_alert));
		builder.setCancelable(false);
		builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// delete order data when yes button clicked
				dbhelper.deleteAllData();
				dbhelper.close();

			}
		});

		builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// close dialog when no button clicked
				dbhelper.close();
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		dbhelper.deleteAllData();
		dbhelper.close();
		finish();
		overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = new ActivityHome();
				break;

			case 1:
				dbhelper.deleteAllData();
				dbhelper.close();
				ActivityMainMenu.this.finish();
				overridePendingTransition(R.anim.open_next, R.anim.close_next);
				break;

			default:
				break;
		}

		if (fragment != null) {
			//android.app.FragmentManager fragmentManager = getFragmentManager();
			//fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

}