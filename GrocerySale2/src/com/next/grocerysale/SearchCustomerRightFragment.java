package com.next.grocerysale;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("NewApi")
public class SearchCustomerRightFragment extends Fragment {
	private ListView listView;
	CreateOrderListAdapter createOrderListAdapter;

	ArrayList<String> list = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_rightsearchfragment,
				null);
		list.add("Preety");
		listView = (ListView) view.findViewById(R.id.customerOrderLV);
		createOrderListAdapter = new CreateOrderListAdapter(getActivity(),
				R.layout.activity_createorder, list);
		listView.setAdapter(createOrderListAdapter);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

	}

	public void setlist(String value) {
		Log.i("list data", value);
		if (value != null) {
			list.add(value);

			createOrderListAdapter.notifyDataSetChanged();
		}
	}
}
