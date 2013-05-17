package com.next.grocerysale.loaders;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;

import com.next.grocery.client.LocalAreaWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

@SuppressLint("NewApi")
public class LocalAreaWebLoader extends AsyncTaskLoader<List<LocalAreaWeb>> {
	Context context;
	private DataServices dataServices;
	private Long itemid=0L;

	public LocalAreaWebLoader(Context context, Long id) {
		super(context);
		this.context = context;
		dataServices = DataServiceFactory.getDataServices();
		itemid = id;

	}

	@Override
	public List<LocalAreaWeb> loadInBackground() {
		return dataServices.getAllLocalAreaOfCityVillage(itemid);
	}

	@Override
	public void deliverResult(List<LocalAreaWeb> data) {
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

	public void onCanceled(List<LocalAreaWeb> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled((List<LocalAreaWeb>) data);

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
