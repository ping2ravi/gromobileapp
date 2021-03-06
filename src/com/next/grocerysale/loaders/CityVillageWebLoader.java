package com.next.grocerysale.loaders;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;

import com.next.core.exception.AppException;
import com.next.grocery.client.CityVillageWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

@SuppressLint("NewApi")
public class CityVillageWebLoader extends AsyncTaskLoader<List<CityVillageWeb>> {
	Context context;
	private DataServices dataServices;
	private Long itemid;

	public CityVillageWebLoader(Context context,Long id) {
		super(context);
		this.context = context;
		dataServices = DataServiceFactory.getDataServices();
		
		itemid=id;
	}

	@Override
	public List<CityVillageWeb> loadInBackground() {
		List<CityVillageWeb> returnList = null;
		for(int i=0;i<3;i++){
			try {
				returnList =  dataServices.getAllCityVillageOfDistrict(itemid);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return returnList;
	}


	@Override
	public void deliverResult(List<CityVillageWeb> data) {
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

	public void onCanceled(List<CityVillageWeb> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled((List<CityVillageWeb>) data);

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
