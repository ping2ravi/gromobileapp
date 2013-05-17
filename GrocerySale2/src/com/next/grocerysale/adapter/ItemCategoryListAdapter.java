package com.next.grocerysale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.next.grocery.client.ItemCategoryWeb;
import com.next.grocerysale.R;

public class ItemCategoryListAdapter extends ArrayAdapter<ItemCategoryWeb> {

	public ItemCategoryListAdapter(Context context, List<ItemCategoryWeb> values) {
		super(context, R.layout.rowlayout_item_category, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout_item_category,
				parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.categoryName);
		textView.setText(getItem(position).getName());
		textView.setTextSize(20);
		// Change the icon for Windows and iPhone
		return rowView;
	}
}
