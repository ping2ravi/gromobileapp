package com.next.grocerysale;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {

	private Thread thread;
	private boolean stop = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		thread = new Thread(runable);
		thread.start();
	}

	public Runnable runable = new Runnable() {

		public void run() {

			try {

				Thread.sleep(2000);

			} catch (InterruptedException e) {
				e.printStackTrace();
				stop = false;
			}

			if (!stop) {
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
			}

			finish();
		}
	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		stop = true;
		finish();
	}
}
