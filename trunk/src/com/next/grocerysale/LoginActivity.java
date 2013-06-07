package com.next.grocerysale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.next.grocery.client.UserWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;
import com.next.grocerysale.task.NextAsyncTask;

public class LoginActivity extends BaseActivity {
	
	DataServices dataServices = DataServiceFactory.getDataServices();
	int response;
	private ProgressBar progress;
	
	private EditText emailIdET;
	private EditText passwordET;
	private Button loginButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.act_login);
		progress=(ProgressBar)findViewById(R.id.loginProgress);
		progress.setVisibility(View.GONE);
		emailIdET = (EditText)findViewById(R.id.emailIdET);
		passwordET = (EditText)findViewById(R.id.passwordET);
		loginButton = (Button)findViewById(R.id.LoginBT);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				log("Login Button Clicked");
				switch (view.getId()) {

				case R.id.LoginBT:
					loginAsync(emailIdET.getText().toString(), passwordET.getText().toString());
					break;
				}
			}
		});
	}

	private void loginAsync(final String userName,final String password){
		new NextAsyncTask<String, String, UserWeb>() {
			protected void beforeExecution() {
				progress.setVisibility(View.VISIBLE);
				loginButton.setVisibility(View.GONE);
			}

			@Override
			protected UserWeb doInBackground(String... params) {
				try{
					UserWeb user = DataServiceFactory.getDataServices().login(userName, password);
					return user;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;
			}
			protected void afterExecution(UserWeb result) {
				progress.setVisibility(View.GONE);
				loginButton.setVisibility(View.VISIBLE);
				if(result != null){
					Intent intent = new Intent(LoginActivity.this,CustomerActivity.class);
					LoginActivity.this.startActivity(intent);
					LoginActivity.this.finish();
				}
			}
		}.execute();
	}
	
	
}
