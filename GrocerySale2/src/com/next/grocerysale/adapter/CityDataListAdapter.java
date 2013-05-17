package com.next.grocerysale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.next.grocery.client.CityVillageWeb;
import com.next.grocerysale.R;

public class CityDataListAdapter extends ArrayAdapter<CityVillageWeb> {
	Context context;

	public CityDataListAdapter(Context context, int resource,
			List<CityVillageWeb> colony) {
		super(context, resource, colony);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CityVillageWeb item=getItem(position);
		TextView textView = new TextView(context);
		textView.setText(item.getName());

		return textView;

	}
}
