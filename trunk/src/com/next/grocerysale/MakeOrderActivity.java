package com.next.grocerysale;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.next.grocery.client.ItemCategoryWeb;

public class MakeOrderActivity extends BaseActivity implements
		OrderListFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makeorder);

		mTwoPane = true;

		/*
		 * ((ItemCategoryListFragment) getFragmentManager().findFragmentById(
		 * R.id.item_list)).setActivateOnItemClick(true);
		 */
	}

	@Override
	public void onItemCategorySelected(ItemCategoryWeb itemCategoryWeb) {
		Log.i("onItemSelected", itemCategoryWeb.toString());
		if (mTwoPane) {
			Log.i("onItemSelected", "Two Pan");
			/*
			 * ItemDetailFragment itemDetailFragment = (ItemDetailFragment)
			 * getFragmentManager()
			 * .findFragmentById(R.id.item_detail_fragment);
			 * itemDetailFragment.setSelectedCategory(itemCategoryWeb);
			 */
		} else {
			Log.i("onItemSelected", "Single Pan");

		}
	}

	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.saveorderBT:
			break;
		case R.id.cancelorderBT:
			
			OrderListFragment listfragment=(OrderListFragment) getFragmentManager().findFragmentById(R.id.orderfragments);
			listfragment.resetList();
			break;

		}
	}
}
