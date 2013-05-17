package com.next.grocerysale;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CreateOrderListAdapter extends ArrayAdapter<String> {

	ArrayList<String> list = new ArrayList<String>();
	Context context;

	public CreateOrderListAdapter(Context context, int textViewResourceId,
			ArrayList<String> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub

		this.context = context;
		list = objects;

	}

	@Override
	public View getView(int position, View convertView,
			android.view.ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.activity_createorder, parent,
				false);
		TextView effectNameTV = (TextView) convertView
				.findViewById(R.id.customerNameTV);

		Button createorder = (Button) convertView
				.findViewById(R.id.createOrderButton);
		createorder.setOnClickListener(new CreateOrderListener());
		effectNameTV.setText(list.get(position));

		return convertView;
	}// getView

	class CreateOrderListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, MakeOrderActivity.class);
			context.startActivity(intent);

		}
	}

}
