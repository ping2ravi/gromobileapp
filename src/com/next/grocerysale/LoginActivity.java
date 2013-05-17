package com.next.grocerysale;

import com.next.grocery.client.UserWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class LoginActivity extends BaseActivity {
	
	DataServices dataServices = DataServiceFactory.getDataServices();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.act_login);

	}

	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.LoginBT:
			Log.i("user", "Logged In");
			//UserWeb loginUser = dataServices.login("userName", "Password");
			//Log.i("user", " User Name "+loginUser.getName());
			Intent intent = new Intent(LoginActivity.this,CustomerActivity.class);
			startActivity(intent);
			finish();
			break;
		}

	}
}
