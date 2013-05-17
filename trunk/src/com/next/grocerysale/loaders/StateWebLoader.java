package com.next.grocerysale.loaders;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.next.grocery.client.StateWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

@SuppressLint("NewApi")
public class StateWebLoader extends AsyncTaskLoader<List<StateWeb>> {
	Context context;
	private DataServices dataServices;

	public StateWebLoader(Context context) {
		super(context);
		this.context = context;
		dataServices = DataServiceFactory.getDataServices();
	}

	@Override
	public List<StateWeb> loadInBackground() {
		Log.i("StateWebLoader", "Loading States");
		List<StateWeb> states =  dataServices.getAllStates();
		Log.i("StateWebLoader", "States Loaded");
		Log.i("StateWebLoader", "List = "+states);
		return states;

	}

	@Override
	public void deliverResult(List<StateWeb> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}
	
	private void onReleaseResources(Object cachedSaleItems2) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

	@Override
	protected void onReset() {
		super.onReset();
		onStopLoading();
	}

	public void onCanceled(List<StateWeb> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled((List<StateWeb>) data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		onReleaseResources(data);
	}

	@Override
	public void forceLoad() {
		// TODO Auto-generated method stub
		super.forceLoad();
	}
	
}
