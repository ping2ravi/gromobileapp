package com.next.grocerysale;

import com.next.grocery.client.CustomerWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SearchCustomerActivity extends BaseActivity implements
		SearchCustomerLeftFragment.OnClickFragmentListener {

	private DataServices dataServices = DataServiceFactory.getDataServices();
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customersearch);

		CustomerWeb customer = (CustomerWeb)getIntent().getSerializableExtra("NewCustomer");
		//Now add this customer to right side table

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();

		finish();
	}

	@Override
	public void onClickFragement(String searchItem) {
		SearchCustomerRightFragment rightFragement = (SearchCustomerRightFragment) getFragmentManager()
				.findFragmentById(R.id.customersearchRightFragment);
		if (rightFragement != null && rightFragement.isInLayout()) {
			// TODO
			// update right fragment
		} else {
			Intent intent = new Intent(getApplicationContext(),
					CustomerListAct.class);
			// if want to pass data
			// intent.putExtra(DetailActivity.EXTRA_URL, link);
			startActivity(intent);
		}
	}
	
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.searchBtn :
			Toast.makeText(this, "Clicked on Search", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}
}
