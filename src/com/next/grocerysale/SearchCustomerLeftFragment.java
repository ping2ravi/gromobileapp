package com.next.grocerysale;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;

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

@SuppressLint("NewApi")
public class SearchCustomerLeftFragment extends Fragment implements
		OnClickListener

{
	private static final int STATE = 0;
	private static final int DISTRICT = 1;
	private static final int CITY = 2;
	private static final int COLONEY = 3;

	ProgressBar stateProgressbar;
	ProgressBar districtProgressbar;
	ProgressBar cityProgressbar;
	ProgressBar localareaProgressbar;
	
	RadioButton locationRBtn, CustomerIDRBtn, mobileRBtn;
	Spinner stateSpinner, citySpinner, districtSpinner, colonySpinner;

	OnClickFragmentListener listener;
	private Button searchBtn;

	ColonyDataListAdapter colonyDataListAdapter;
	StatedataListAdapter statedataListAdapter;
	CityDataListAdapter cityDataListAdapter;
	DistrictDataListAdapter districtDataListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_search_customer_left,
				null);
		stateProgressbar = (ProgressBar) view.findViewById(R.id.stateProgressbar);
		districtProgressbar = (ProgressBar) view.findViewById(R.id.districtProgressbar);
		cityProgressbar = (ProgressBar) view.findViewById(R.id.cityProgressbar);
		localareaProgressbar = (ProgressBar) view.findViewById(R.id.localareaProgressbar);
		
		locationRBtn = (RadioButton) view.findViewById(R.id.bylocationRB);
		CustomerIDRBtn = (RadioButton) view.findViewById(R.id.byIDRBtn);
		mobileRBtn = (RadioButton) view.findViewById(R.id.bymobileRbtn);
		stateSpinner = (Spinner) view.findViewById(R.id.statesearchSP);
		districtSpinner = (Spinner) view.findViewById(R.id.districtsearchSP);
		citySpinner = (Spinner) view.findViewById(R.id.citysearchSP);
		colonySpinner = (Spinner) view.findViewById(R.id.colonoysearchSP);
		locationRBtn.setOnClickListener(this);
		CustomerIDRBtn.setOnClickListener(this);
		mobileRBtn.setOnClickListener(this);

		Bundle bundle = null;
		getLoaderManager().initLoader(1, bundle,
				new LoaderCallbacks<List<StateWeb>>() {

					@Override
					public Loader<List<StateWeb>> onCreateLoader(int id,
							Bundle args) {
						// TODO Auto-generated method stub
						stateProgressbar.setVisibility(View.VISIBLE);
						stateSpinner.setVisibility(View.GONE);
						return new StateWebLoader(getActivity());
					}

					@Override
					public void onLoadFinished(Loader<List<StateWeb>> arg0,
							List<StateWeb> arg1) {
						stateProgressbar.setVisibility(View.GONE);
						stateSpinner.setVisibility(View.VISIBLE);
						setStateAdapter(arg1);
					}

					@Override
					public void onLoaderReset(Loader<List<StateWeb>> arg0) {
						// TODO Auto-generated method stub

					}

				}).forceLoad();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public interface OnClickFragmentListener {
		public void onClickFragement(String searchItem);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnClickFragmentListener) {
			listener = (OnClickFragmentListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bylocationRB:
			locationRBtn.setChecked(true);
			mobileRBtn.setChecked(false);
			CustomerIDRBtn.setChecked(false);
			break;
		case R.id.byIDRBtn:
			CustomerIDRBtn.setChecked(true);
			locationRBtn.setChecked(false);
			mobileRBtn.setChecked(false);
			break;
		case R.id.bymobileRbtn:
			CustomerIDRBtn.setChecked(false);
			mobileRBtn.setChecked(true);
			locationRBtn.setChecked(false);
			break;

		}
	}

	public void setStateAdapter(List<StateWeb> statelist) {
		List<StateWeb> fullList = new ArrayList<StateWeb>(statelist);
		StateWeb selectState = new StateWeb();
		selectState.setId(-1L);
		selectState.setName("Select State");
		fullList.add(0, selectState);
		statedataListAdapter = new StatedataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, fullList);
		statedataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stateSpinner.setAdapter(statedataListAdapter);

		stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if(position <= 0){
					districtSpinner.setEnabled(false);
					citySpinner.setEnabled(false);
					colonySpinner.setEnabled(false);
					return;
				}
				StateWeb item = statedataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().restartLoader(2, bundle,
						new LoaderCallbacks<List<DistrictWeb>>() {

							@Override
							public Loader<List<DistrictWeb>> onCreateLoader(
									int id, Bundle args) {
								// TODO Auto-generated method stub
								districtProgressbar.setVisibility(View.VISIBLE);
								districtSpinner.setVisibility(View.GONE);
								return new DistrictWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<DistrictWeb>> arg0,
									List<DistrictWeb> arg1) {
								districtProgressbar.setVisibility(View.GONE);
								districtSpinner.setVisibility(View.VISIBLE);
								districtSpinner.setEnabled(true);
								setDistrictAdapter(arg1);
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
				// TODO Auto-generated method stub

			}
		});

	}

	public void setDistrictAdapter(List<DistrictWeb> districtlist) {
		List<DistrictWeb> fullList = new ArrayList<DistrictWeb>(districtlist);
		DistrictWeb selectDistrict = new DistrictWeb();
		selectDistrict.setId(-1L);
		selectDistrict.setName("Select District");
		fullList.add(0, selectDistrict);
		districtDataListAdapter = new DistrictDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, fullList);
		districtDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSpinner.setAdapter(districtDataListAdapter);
		districtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					citySpinner.setEnabled(false);
					colonySpinner.setEnabled(false);
					return;
				}
				DistrictWeb item = districtDataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().initLoader(3, bundle,
						new LoaderCallbacks<List<CityVillageWeb>>() {

							@Override
							public Loader<List<CityVillageWeb>> onCreateLoader(
									int id, Bundle args) {
								cityProgressbar.setVisibility(View.VISIBLE);
								citySpinner.setVisibility(View.GONE);
								return new CityVillageWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<CityVillageWeb>> arg0,
									List<CityVillageWeb> citylist) {
								cityProgressbar.setVisibility(View.GONE);
								citySpinner.setVisibility(View.VISIBLE);
								citySpinner.setEnabled(true);
								setCityAdapter(citylist);
							}

							@Override
							public void onLoaderReset(
									Loader<List<CityVillageWeb>> arg0) {
								// TODO Auto-generated method stub

							}
						});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public void setCityAdapter(List<CityVillageWeb> citylist) {
		List<CityVillageWeb> fullList = new ArrayList<CityVillageWeb>(citylist);
		CityVillageWeb selectCity = new CityVillageWeb();
		selectCity.setId(-1l);
		selectCity.setName("Select City/Village/Town");
		fullList.add(0,selectCity);
		
		cityDataListAdapter = new CityDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, fullList);
		cityDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(cityDataListAdapter);

		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position <= 0){
					colonySpinner.setEnabled(false);
					return;
				}
				CityVillageWeb item = cityDataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().initLoader(4, bundle,
						new LoaderCallbacks<List<LocalAreaWeb>>() {

							@Override
							public Loader<List<LocalAreaWeb>> onCreateLoader(
									int id, Bundle args) {
								// TODO Auto-generated method stub
								localareaProgressbar.setVisibility(View.VISIBLE);
								colonySpinner.setVisibility(View.GONE);
								return new LocalAreaWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<LocalAreaWeb>> arg0,
									List<LocalAreaWeb> arg1) {
								localareaProgressbar.setVisibility(View.GONE);
								colonySpinner.setVisibility(View.VISIBLE);
								colonySpinner.setEnabled(true);
								setColoneyAdapter(arg1);
							}

							@Override
							public void onLoaderReset(
									Loader<List<LocalAreaWeb>> arg0) {
								// TODO Auto-generated method stub

							}

						});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	public void setColoneyAdapter(List<LocalAreaWeb> coloneyList) {
		List<LocalAreaWeb> fullList = new ArrayList<LocalAreaWeb>(coloneyList);
		LocalAreaWeb selectLocalArea = new LocalAreaWeb();
		selectLocalArea.setId(-1L);
		selectLocalArea.setName("Select Colony/LocalArea");
		fullList.add(0,selectLocalArea);
		
		colonyDataListAdapter = new ColonyDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, fullList);
		colonyDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		colonySpinner.setAdapter(colonyDataListAdapter);
		colonySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	/*
	 * public void useLoader() {
	 * 
	 * Log.w("loader", "useLoader"); Bundle args = new Bundle();
	 * 
	 * getLoaderManager().initLoader(1, args, this).forceLoad();
	 * 
	 * }
	 * 
	 * public void restartNewLoader() {
	 * 
	 * Log.w("loader", "useLoader"); Bundle args = new Bundle();
	 * 
	 * Log.w("loader", "useLoader");
	 * 
	 * getLoaderManager().initLoader(1, args, this).forceLoad();
	 * 
	 * }
	 */
}
