package com.next.grocerysale.task;

import android.os.AsyncTask;

public abstract class NextAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>{

	@Override
	final protected void onPreExecute() {
		beforeExecution();
	}
	protected void beforeExecution() {
	}

	@Override
	final protected void onPostExecute(Result result) {
		afterExecution(result);
	}
	protected void afterExecution(Result result) {
	}

}
