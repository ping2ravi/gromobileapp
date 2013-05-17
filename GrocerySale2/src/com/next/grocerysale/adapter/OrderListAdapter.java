package com.next.grocerysale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.next.grocerysale.OrderAdapterItems;
import com.next.grocerysale.R;

public class OrderListAdapter extends ArrayAdapter<OrderAdapterItems> {

	public OrderListAdapter(Context context, int textViewResourceId,
			OrderAdapterItems[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
	}

	private final Context context;
	ViewHolder viewHolder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		viewHolder = new ViewHolder();
		// if (convertView == null) {
		OrderAdapterItems item = getItem(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.makeorderlistitems, null);
		viewHolder.itemQuantityET = (EditText) view
				.findViewById(R.id.itemquantityET);

		viewHolder.itemQuantityET.setText(Integer.toString(item.value));

		viewHolder.itemdetailET = (EditText) view
				.findViewById(R.id.itemdetailET);

		viewHolder.itemdetailET.setText(item.name);

		viewHolder.decreaseIV = (ImageView) view.findViewById(R.id.decreaseIV);
		viewHolder.increaseIV = (ImageView) view.findViewById(R.id.increaseIV);
		view.setTag(viewHolder);
		viewHolder.decreaseIV
				.setOnClickListener(new DecreaseImageListener(item));
		viewHolder.increaseIV
				.setOnClickListener(new IncreaseImageListener(item));
		// }
		/*
		 * else { viewHolder = (ViewHolder) view.getTag(); }
		 */

		return view;
	}// getView

	class ViewHolder {

		EditText itemdetailET, itemQuantityET;
		ImageView decreaseIV;
		ImageView increaseIV;
	}

	class DecreaseImageListener implements OnClickListener {
		OrderAdapterItems item;

		public DecreaseImageListener(OrderAdapterItems data) {
			item = data;

		}

		@Override
		public void onClick(View v) {
			item.value = item.value - 1;
			notifyDataSetChanged();

		}

	}

	class IncreaseImageListener implements OnClickListener {
		OrderAdapterItems item;

		public IncreaseImageListener(OrderAdapterItems data) {
			item = data;

		}

		@Override
		public void onClick(View v) {

			item.value = item.value + 1;
			notifyDataSetChanged();
		}

	}
}