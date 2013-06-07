package com.next.grocerysale.loaders;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;

@SuppressLint("NewApi")
public class LoginLoader extends AsyncTaskLoader<Integer> {
	Context context;
	int response = 0;
	int data=0;


	public LoginLoader(Context context) {
		super(context);
		this.context = context;
		
	}

	@Override
	public Integer loadInBackground() {
		// TODO Auto-generated method stub
		return postData();

	}

	@Override
	public void deliverResult(Integer data) {
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

	public void onCanceled(Integer data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled(data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		onReleaseResources(data);
	}

	@Override
	public void forceLoad() {
		// TODO Auto-generated method stub
		super.forceLoad();
	}
	public Integer postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.toxsl.com");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	        nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	       HttpResponse response = httpclient.execute(httppost);
	        data = 200;
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	    } 
	    
	  
		return data;
	}
}
