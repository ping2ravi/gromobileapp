package com.next.grocerysale;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.next.grocery.client.CityVillageWeb;
import com.next.grocery.client.DistrictWeb;
import com.next.grocery.client.LocalAreaWeb;
import com.next.grocery.client.StateWeb;
import com.next.grocerysale.adapter.CityDataListAdapter;
import com.next.grocerysale.adapter.ColonyDataListAdapter;
import com.next.grocerysale.adapter.DistrictDataListAdapter;
import com.next.grocerysale.adapter.StatedataListAdapter;
import com.next.grocerysale.loaders.CityVillageWebLoader;
import com.next.grocerysale.loaders.DistrictWebLoader;
import com.next.grocerysale.loaders.LocalAreaWebLoader;
import com.next.grocerysale.loaders.StateWebLoader;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class CreateCustomerActivity extends Activity implements
		OnMapClickListener, OnMapLongClickListener, OnMarkerDragListener

{
	private static final int STATE = 0;
	private static final int DISTRICT = 1;
	private static final int CITY = 2;
	private static final int COLONEY = 3;
	
	private DataServices dataServices = DataServiceFactory.getDataServices();
	

	Spinner stateSP, citySP, colonySP, districtSP;

	final int RQS_GooglePlayServices = 1;
	private GoogleMap myMap;
	boolean markerClicked;
	TextView locationTV;
	EditText nameET;

	ColonyDataListAdapter colonyDataListAdapter;
	StatedataListAdapter statedataListAdapter;
	CityDataListAdapter cityDataListAdapter;
	DistrictDataListAdapter districtDataListAdapter;
	private ProgressBar progress;
	MapFragment map;
	Marker marker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createcustomer);

		progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setVisibility(View.GONE);
		stateSP = (Spinner) findViewById(R.id.StateSP);
		colonySP = (Spinner) findViewById(R.id.colonySP);
		districtSP = (Spinner) findViewById(R.id.districtSP);
		citySP = (Spinner) findViewById(R.id.citySP);
		map = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
		
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.draggable(true);
		markerOptions.position(new LatLng(2.3, 5.8));
		
		
		marker = map.getMap().addMarker(markerOptions);
		
		
		
		Bundle bundle1 = null;
		getLoaderManager().initLoader(1, bundle1,
				new LoaderCallbacks<List<StateWeb>>() {

					@Override
					public Loader<List<StateWeb>> onCreateLoader(int id,
							Bundle args) {

						progress.setVisibility(View.VISIBLE);
						return new StateWebLoader(CreateCustomerActivity.this);
					}

					@Override
					public void onLoadFinished(Loader<List<StateWeb>> arg0,List<StateWeb> arg1) {
						List<StateWeb> states = new ArrayList<StateWeb>(arg1);
						StateWeb selectState = new StateWeb();
						selectState.setName("Select State");
						selectState.setId(0L);
						states.add(0,selectState);
						setStateAdapter(states);
						progress.setVisibility(View.GONE);
					}

					@Override
					public void onLoaderReset(Loader<List<StateWeb>> arg0) {

					}

				}).forceLoad();

		// locationTV = (TextView) findViewById(R.id.locationTV);
		nameET = (EditText) findViewById(R.id.nameET);
		FragmentManager myFragmentManager = getFragmentManager();
		MapFragment myMapFragment = (MapFragment) myFragmentManager
				.findFragmentById(R.id.map);
		myMap = myMapFragment.getMap();

		if (myMap != null) {
			myMap.setMyLocationEnabled(true);
			// myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

			myMap.setOnMapClickListener(this);
			myMap.setOnMapLongClickListener(this);
			myMap.setOnMarkerDragListener(this);
		}

		markerClicked = false;

	}

	@Override
	protected void onResume() {
		super.onResume();

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());

		if (resultCode == ConnectionResult.SUCCESS) {
			Toast.makeText(getApplicationContext(),
					"isGooglePlayServicesAvailable SUCCESS", Toast.LENGTH_LONG)
					.show();
		} else {
			GooglePlayServicesUtil.getErrorDialog(resultCode, this,
					RQS_GooglePlayServices);
		}

	}

	@Override
	public void onMapClick(LatLng point) {
		// locationTV.setText(point.toString());
		myMap.animateCamera(CameraUpdateFactory.newLatLng(point));

		markerClicked = false;

	}

	@Override
	public void onMapLongClick(LatLng point) {
		// locationTV.setText("New Marker added at:: " + point.toString());
		myMap.addMarker(new MarkerOptions().position(point).draggable(true));
		markerClicked = false;
	}

	@Override
	public void onMarkerDrag(Marker marker) {
		// locationTV.setText("Marker " + marker.getId() + "Drag@ "
		// + marker.getPosition());
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		// locationTV.setText("Marker " + marker.getId() + "Drag@ "
		// + marker.getPosition() + " Drag End");
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// locationTV.setText("Marker " + marker.getId() + "Drag@ "
		// + marker.getPosition() + " Drag Start");
	}

	public void onClick(View view)

	{
		Intent intent;

		switch (view.getId()) {

		case R.id.savecustomerBtn:
			String namedata = nameET.getText().toString();
			Log.i("namedata", namedata);
			intent = new Intent(CreateCustomerActivity.this,
					SearchCustomerActivity.class);
			intent.putExtra("namedata", namedata);
			startActivity(intent);
			break;

		case R.id.cancelBtn:
			intent = new Intent(CreateCustomerActivity.this,
					CustomerActivity.class);
			startActivity(intent);
			break;
		}
	}
	public void updateMapLocation(double lat,double lng,int depth){
		LatLng newMarkerPosition = new LatLng(lat, lng);
		marker.setPosition(newMarkerPosition);
		
		CameraPosition.Builder builder = CameraPosition.builder();
		builder.target(newMarkerPosition);
		builder.zoom(depth);
		
		CameraPosition cameraPosition = builder.build();
		
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		map.getMap().animateCamera(cameraUpdate);
		
	}
	public void setStateAdapter(List<StateWeb> statelist) {

		statedataListAdapter = new StatedataListAdapter(
				CreateCustomerActivity.this,
				android.R.layout.simple_list_item_1, statelist);
		statedataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stateSP.setAdapter(statedataListAdapter);

		stateSP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					//Do not fire any event to load Districts
					return;
				}
				StateWeb item = statedataListAdapter.getItem(position);
				updateMapLocation(item.getLatitude() , item.getLongitude(), item.getDepth());
				
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().restartLoader(2, bundle,
						new LoaderCallbacks<List<DistrictWeb>>() {

							@Override
							public Loader<List<DistrictWeb>> onCreateLoader(
									int id, Bundle args) {
								progress.setVisibility(View.VISIBLE);
								return new DistrictWebLoader(
										CreateCustomerActivity.this, itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<DistrictWeb>> arg0,
									List<DistrictWeb> arg1) {
								List<DistrictWeb> districts = new ArrayList<DistrictWeb>(arg1);
								DistrictWeb selectDistrict = new DistrictWeb();
								selectDistrict.setName("Select District");
								selectDistrict.setId(-1L);
								districts.add(0, selectDistrict);
								setDistrictAdapter(districts);
								progress.setVisibility(View.GONE);
							}

							@Override
							public void onLoaderReset(
									Loader<List<DistrictWeb>> arg0) {

							}

						});
				/* restartNewLoader(); */

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	public void setDistrictAdapter(List<DistrictWeb> districtlist) {
		districtDataListAdapter = new DistrictDataListAdapter(
				CreateCustomerActivity.this,
				android.R.layout.simple_list_item_1, districtlist);
		districtDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSP.setAdapter(districtDataListAdapter);
		districtSP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					return;
				}
				DistrictWeb item = districtDataListAdapter.getItem(position);
				
				updateMapLocation(item.getLatitude() , item.getLongitude(), item.getDepth());
				
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().restartLoader(3, bundle,
						new LoaderCallbacks<List<CityVillageWeb>>() {

							@Override
							public Loader<List<CityVillageWeb>> onCreateLoader(
									int id, Bundle args) {
								progress.setVisibility(View.VISIBLE);
								return new CityVillageWebLoader(
										CreateCustomerActivity.this, itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<CityVillageWeb>> arg0,
									List<CityVillageWeb> citylist) {
								List<CityVillageWeb> cityVillageWebs = new ArrayList<CityVillageWeb>(citylist);
								CityVillageWeb selectCityVillageWeb = new CityVillageWeb();
								selectCityVillageWeb.setName("Select CIty/Village");
								selectCityVillageWeb.setId(-1L);
								cityVillageWebs.add(0,selectCityVillageWeb);
								setCityAdapter(cityVillageWebs);
								progress.setVisibility(View.GONE);
							}

							@Override
							public void onLoaderReset(
									Loader<List<CityVillageWeb>> arg0) {

							}
						});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public void setCityAdapter(List<CityVillageWeb> citylist) {
		cityDataListAdapter = new CityDataListAdapter(
				CreateCustomerActivity.this,
				android.R.layout.simple_list_item_1, citylist);
		cityDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySP.setAdapter(cityDataListAdapter);

		citySP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					return;
				}
				CityVillageWeb item = cityDataListAdapter.getItem(position);
				updateMapLocation(item.getLatitude() , item.getLongitude(), item.getDepth());
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().restartLoader(4, bundle,
						new LoaderCallbacks<List<LocalAreaWeb>>() {

							@Override
							public Loader<List<LocalAreaWeb>> onCreateLoader(
									int id, Bundle args) {
								progress.setVisibility(View.VISIBLE);
								return new LocalAreaWebLoader(
										CreateCustomerActivity.this, itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<LocalAreaWeb>> arg0,
									List<LocalAreaWeb> arg1) {
								List<LocalAreaWeb> localAreas = new ArrayList<LocalAreaWeb>(arg1);
								LocalAreaWeb localAreaWeb = new LocalAreaWeb();
								localAreaWeb.setName("Select Colony/Local Area");
								localAreaWeb.setId(-1L);
								localAreas.add(0,localAreaWeb);
								setColoneyAdapter(localAreas);
								progress.setVisibility(View.GONE);
							}

							@Override
							public void onLoaderReset(
									Loader<List<LocalAreaWeb>> arg0) {

							}

						});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	public void setColoneyAdapter(List<LocalAreaWeb> coloneyList) {

		colonyDataListAdapter = new ColonyDataListAdapter(
				CreateCustomerActivity.this,
				android.R.layout.simple_list_item_1, coloneyList);
		colonyDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		colonySP.setAdapter(colonyDataListAdapter);
		colonySP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					return;
				}
				LocalAreaWeb selectedLocalArea = colonyDataListAdapter.getItem(position);
				updateMapLocation(selectedLocalArea.getLatitude() , selectedLocalArea.getLongitude(), selectedLocalArea.getDepth());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}
}