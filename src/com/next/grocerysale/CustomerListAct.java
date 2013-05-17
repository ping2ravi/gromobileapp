package com.next.grocerysale;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class CustomerListAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		setContentView(R.layout.act_customer_list);
		
	} //onCreate
}
