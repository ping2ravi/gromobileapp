package com.next.grocerysale;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//CheckDate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}

	public static void log(String string) {
		Log.e("pos", string);
	}

	public void toast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	public static void loge(String string) {
		Log.e("pos", string);
	}

	public void CheckDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MAY, 15);
		Calendar currentcal = Calendar.getInstance();

		if (currentcal.after(cal)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Please contact : shiv@toxsl.com");
			builder.setTitle("Demo Expired");
			builder.setCancelable(false);
			builder.setNegativeButton("Close", new OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}

	}
}
