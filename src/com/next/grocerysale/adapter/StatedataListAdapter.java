package com.next.grocerysale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.next.grocery.client.StateWeb;
import com.next.grocerysale.R;

public class StatedataListAdapter  extends ArrayAdapter<StateWeb> {
Context context;
	public StatedataListAdapter(Context context, int resource,
			List<StateWeb> colony) {
		super(context, resource, colony);
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout_sale_item, parent,false);
		TextView textView = new TextView(context);
		textView.setText(getItem(position).getName());

		return textView;

	}}
