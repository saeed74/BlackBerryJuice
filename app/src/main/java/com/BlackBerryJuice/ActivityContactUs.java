package com.BlackBerryJuice;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityContactUs extends Activity {

	EditText body;
	Button send;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
		bar.setTitle("Contact Us");
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setHomeButtonEnabled(true);

		body = (EditText) findViewById(R.id.body);
		send = (Button) findViewById(R.id.send);

	}

	public void SendBtn(View v) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { getResources().getString(R.string.email_address) });

		intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));

		String body_email = body.getText().toString() + "\n\n Sent from Ecommerce Android App";
		intent.putExtra(Intent.EXTRA_TEXT, body_email);
		startActivityForResult(Intent.createChooser(intent, "Send email..."), 100);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(ActivityContactUs.this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}

}
