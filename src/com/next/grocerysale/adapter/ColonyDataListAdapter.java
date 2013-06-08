package com.next.grocerysale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.next.grocery.client.LocalAreaWeb;
import com.next.grocerysale.R;

public class ColonyDataListAdapter extends ArrayAdapter<LocalAreaWeb> {
Context context;
	public ColonyDataListAdapter(Context context, int resource,
			List<LocalAreaWeb> colony) {
		super(context, resource, colony);
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		TextView textView = new TextView(context);
		textView.setText(getItem(position).getName());

		return textView;

	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {

		
		TextView textView = new TextView(context);
		textView.setText(getItem(position).getName());

		return textView;

	}
	
	
}
