package com.next.grocerysale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerActivity extends BaseActivity {
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_customer);
	}

	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.searchoptionBtn:

			intent = new Intent(CustomerActivity.this,
					SearchCustomerActivity.class);
			startActivity(intent);
			//finish();
			break;
		case R.id.createoptionBtn:
			intent = new Intent(CustomerActivity.this,
					CreateCustomerActivity.class);
			startActivity(intent);
			//finish();
			break;

		}

	}

}
