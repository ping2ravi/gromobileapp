package com.next.grocerysale;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.next.core.exception.AppException;
import com.next.grocery.client.AddressWeb;
import com.next.grocery.client.CityVillageWeb;
import com.next.grocery.client.CustomerWeb;
import com.next.grocery.client.DistrictWeb;
import com.next.grocery.client.LocalAreaWeb;
import com.next.grocery.client.StateWeb;
import com.next.grocery.client.ext.requests.SaveCustomerRequest;
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
import com.next.grocerysale.task.NextAsyncTask;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class CreateCustomerActivity extends BaseActivity implements
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

	ColonyDataListAdapter colonyDataListAdapter;
	StatedataListAdapter statedataListAdapter;
	CityDataListAdapter cityDataListAdapter;
	DistrictDataListAdapter districtDataListAdapter;
	private ProgressBar stateProgressBar;
	private ProgressBar districtProgressBar;
	private ProgressBar cityProgressBar;
	private ProgressBar colonyProgressBar;
	MapFragment map;
	Marker marker;

	private EditText nameEditText;
	private EditText customerMobile1EditText;
	private EditText customerMobile2EditText;
	private EditText emailEditText;
	
	private EditText address1EditText;
	private EditText address2EditText;
	private EditText address3EditText;
	private EditText pinEditText;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createcustomer);
		
		nameEditText = (EditText) findViewById(R.id.nameET);
		customerMobile1EditText = (EditText) findViewById(R.id.customermobile1ET);
		customerMobile2EditText = (EditText) findViewById(R.id.customermobile2ET);
		emailEditText = (EditText) findViewById(R.id.emailET);

		address1EditText = (EditText) findViewById(R.id.address1ET);
		address2EditText = (EditText) findViewById(R.id.address2ET);
		address3EditText = (EditText) findViewById(R.id.address3ET);
		pinEditText = (EditText) findViewById(R.id.pinET);

		stateProgressBar = (ProgressBar) findViewById(R.id.stateProgressBar);
		stateProgressBar.setVisibility(View.GONE);
		districtProgressBar = (ProgressBar) findViewById(R.id.districtProgressBar);
		districtProgressBar.setVisibility(View.GONE);
		cityProgressBar = (ProgressBar) findViewById(R.id.cityProgressBar);
		cityProgressBar.setVisibility(View.GONE);
		colonyProgressBar = (ProgressBar) findViewById(R.id.colonyProgressBar);
		colonyProgressBar.setVisibility(View.GONE);
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

						stateProgressBar.setVisibility(View.VISIBLE);
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
						stateProgressBar.setVisibility(View.GONE);
					}

					@Override
					public void onLoaderReset(Loader<List<StateWeb>> arg0) {

					}

				}).forceLoad();

		// locationTV = (TextView) findViewById(R.id.locationTV);
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

	}

	@Override
	public void onMapLongClick(LatLng point) {
	}

	@Override
	public void onMarkerDrag(Marker marker) {
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
	}

	public void onClick(View view)

	{
		Intent intent;

		switch (view.getId()) {

		case R.id.savecustomerBtn:
			SaveCustomerRequest saveCustomerRequest = new SaveCustomerRequest();
			CustomerWeb customer = new CustomerWeb();
			customer.setName(nameEditText.getText().toString());
			customer.setMobile1(customerMobile1EditText.getText().toString());
			customer.setMobile2(customerMobile2EditText.getText().toString());
			customer.setEmail(emailEditText.getText().toString());
			saveCustomerRequest.setCustomer(customer);
			AddressWeb address = new AddressWeb();
			address.setAddressLine1(address1EditText.getText().toString());
			address.setAddressLine2(address2EditText.getText().toString());
			address.setAddressLine3(address3EditText.getText().toString());
			address.setPin(Integer.parseInt(pinEditText.getText().toString()));
			address.setStateId(((StateWeb)stateSP.getSelectedItem()).getId());
			address.setDistrictId(((DistrictWeb)districtSP.getSelectedItem()).getId());
			address.setCityVillageId(((CityVillageWeb)citySP.getSelectedItem()).getId());
			address.setLocalAreaId(((LocalAreaWeb)colonySP.getSelectedItem()).getId());
			address.setAddressType("Home");
			saveCustomerRequest.setAddress(address);
			
			saveCustomerAsync(saveCustomerRequest);
			
			intent = new Intent(CreateCustomerActivity.this,SearchCustomerActivity.class);
			startActivity(intent);
			break;

		case R.id.cancelBtn:
			intent = new Intent(CreateCustomerActivity.this,
					CustomerActivity.class);
			startActivity(intent);
			break;
		}
	}
	private void saveCustomerAsync(final SaveCustomerRequest saveCustomerRequest){
		new NextAsyncTask<String, String, CustomerWeb>() {

			@Override
			protected CustomerWeb doInBackground(String... params) {
				try {
					CustomerWeb customerWeb = DataServiceFactory.getDataServices().saveCustomer(saveCustomerRequest);
					log("CustomerId = "+customerWeb.getId());
					return customerWeb;
				} catch (AppException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		}.execute();
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
								districtProgressBar.setVisibility(View.VISIBLE);
								districtSP.setVisibility(View.GONE);
								return new DistrictWebLoader(
										CreateCustomerActivity.this, itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<DistrictWeb>> arg0,
									List<DistrictWeb> arg1) {
								districtProgressBar.setVisibility(View.GONE);
								districtSP.setVisibility(View.VISIBLE);
								List<DistrictWeb> districts = new ArrayList<DistrictWeb>(arg1);
								DistrictWeb selectDistrict = new DistrictWeb();
								selectDistrict.setName("Select District");
								selectDistrict.setId(-1L);
								districts.add(0, selectDistrict);
								setDistrictAdapter(districts);
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
								cityProgressBar.setVisibility(View.VISIBLE);
								citySP.setVisibility(View.GONE);
								return new CityVillageWebLoader(
										CreateCustomerActivity.this, itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<CityVillageWeb>> arg0,
									List<CityVillageWeb> citylist) {
								cityProgressBar.setVisibility(View.GONE);
								citySP.setVisibility(View.VISIBLE);
								List<CityVillageWeb> cityVillageWebs = new ArrayList<CityVillageWeb>(citylist);
								CityVillageWeb selectCityVillageWeb = new CityVillageWeb();
								selectCityVillageWeb.setName("Select CIty/Village");
								selectCityVillageWeb.setId(-1L);
								cityVillageWebs.add(0,selectCityVillageWeb);
								setCityAdapter(cityVillageWebs);
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
								colonyProgressBar.setVisibility(View.VISIBLE);
								colonySP.setVisibility(View.GONE);
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
								colonyProgressBar.setVisibility(View.GONE);
								colonySP.setVisibility(View.VISIBLE);
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