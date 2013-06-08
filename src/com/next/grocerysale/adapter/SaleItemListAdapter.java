package com.next.grocerysale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.next.grocery.client.ext.ItemWithItemPackExtWeb;
import com.next.grocerysale.R;

public class SaleItemListAdapter extends ArrayAdapter<ItemWithItemPackExtWeb> {

	public SaleItemListAdapter(Context context,
			List<ItemWithItemPackExtWeb> values) {
		super(context, R.layout.rowlayout_sale_item, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout_sale_item, parent,
				false);
		TextView textView = (TextView) rowView.findViewById(R.id.itemName);
		textView.setText(getItem(position).getName());textView.setText("data");
		// Change the icon for Windows and iPhone
		return rowView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout_sale_item, parent,
				false);
		TextView textView = (TextView) rowView.findViewById(R.id.itemName);
		textView.setText(getItem(position).getName());textView.setText("data");
		// Change the icon for Windows and iPhone
		return rowView;
	}
	
	
}
