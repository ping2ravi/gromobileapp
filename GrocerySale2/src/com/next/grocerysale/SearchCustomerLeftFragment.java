package com.next.grocerysale;

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
	

	Spinner stateSP, citySP, colonySP, districtSP;

	RadioButton locationRBtn, CustomerIDRBtn, mobileRBtn;
	Spinner stateSpinner, citySpinner, districtSpinner, colonyspinner;

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

		locationRBtn = (RadioButton) view.findViewById(R.id.bylocationRB);
		CustomerIDRBtn = (RadioButton) view.findViewById(R.id.byIDRBtn);
		mobileRBtn = (RadioButton) view.findViewById(R.id.bymobileRbtn);
		stateSpinner = (Spinner) view.findViewById(R.id.statesearchSP);
		districtSpinner = (Spinner) view.findViewById(R.id.districtsearchSP);
		citySpinner = (Spinner) view.findViewById(R.id.citysearchSP);
		colonyspinner = (Spinner) view.findViewById(R.id.colonoysearchSP);
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

						return new StateWebLoader(getActivity());
					}

					@Override
					public void onLoadFinished(Loader<List<StateWeb>> arg0,
							List<StateWeb> arg1) {
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

		statedataListAdapter = new StatedataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, statelist);
		statedataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stateSpinner.setAdapter(statedataListAdapter);

		stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				StateWeb item = statedataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().initLoader(2, bundle,
						new LoaderCallbacks<List<DistrictWeb>>() {

							@Override
							public Loader<List<DistrictWeb>> onCreateLoader(
									int id, Bundle args) {
								// TODO Auto-generated method stub
								return new DistrictWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<DistrictWeb>> arg0,
									List<DistrictWeb> arg1) {
								setDistrictAdapter(arg1);

							}

							@Override
							public void onLoaderReset(
									Loader<List<DistrictWeb>> arg0) {
								// TODO Auto-generated method stub

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
		districtDataListAdapter = new DistrictDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, districtlist);
		districtDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSpinner.setAdapter(districtDataListAdapter);
		districtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				DistrictWeb item = districtDataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().initLoader(3, bundle,
						new LoaderCallbacks<List<CityVillageWeb>>() {

							@Override
							public Loader<List<CityVillageWeb>> onCreateLoader(
									int id, Bundle args) {

								return new CityVillageWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<CityVillageWeb>> arg0,
									List<CityVillageWeb> citylist) {
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
		cityDataListAdapter = new CityDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, citylist);
		cityDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(cityDataListAdapter);

		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				CityVillageWeb item = cityDataListAdapter.getItem(position);
				final Long itemid = item.getId();
				Bundle bundle = null;
				getLoaderManager().initLoader(4, bundle,
						new LoaderCallbacks<List<LocalAreaWeb>>() {

							@Override
							public Loader<List<LocalAreaWeb>> onCreateLoader(
									int id, Bundle args) {
								// TODO Auto-generated method stub
								return new LocalAreaWebLoader(getActivity(),
										itemid);
							}

							@Override
							public void onLoadFinished(
									Loader<List<LocalAreaWeb>> arg0,
									List<LocalAreaWeb> arg1) {
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

		colonyDataListAdapter = new ColonyDataListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, coloneyList);
		colonyDataListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		colonyspinner.setAdapter(colonyDataListAdapter);
		colonyspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

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
