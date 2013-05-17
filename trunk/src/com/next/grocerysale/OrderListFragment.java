package com.next.grocerysale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.next.grocery.client.ItemCategoryWeb;
import com.next.grocery.domain.ItemCategoryDomain;
import com.next.grocerysale.adapter.ItemCategoryListAdapter;
import com.next.grocerysale.adapter.OrderListAdapter;
import com.next.grocerysale.loaders.ItemCategoryLoader;

public class OrderListFragment extends Fragment implements
		LoaderCallbacks<List<ItemCategoryWeb>>, OnClickListener {

	Button goBackButton;

	Loader<List<ItemCategoryWeb>> itemCategoryLoader;

	ItemCategoryDomain itemCategoryDomain;

	Map<Long, List<ItemCategoryWeb>> itemCategoryTree = new HashMap<Long, List<ItemCategoryWeb>>();

	ItemCategoryListAdapter mAdapter;
	OrderListAdapter listadapter;
	

	private Callbacks mCallbacks = sDummyCallbacks;

	private Stack<List<ItemCategoryWeb>> categoryListStack = new Stack<List<ItemCategoryWeb>>();



	private ArrayList<ItemCategoryWeb> selectedItemCategoryList;
	ListView listview;

	public interface Callbacks {

		public void onItemCategorySelected(ItemCategoryWeb itemCategoryWeb);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemCategorySelected(ItemCategoryWeb itemCategoryWeb) {
		}
	};

	public OrderListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = null;
		getLoaderManager().initLoader(0, bundle, this);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_item_category_list,
				container, false);
		itemCategoryLoader = new ItemCategoryLoader(this.getActivity());

		selectedItemCategoryList = new ArrayList<ItemCategoryWeb>();
		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new ItemCategoryListAdapter(getActivity(),
				selectedItemCategoryList);

		listview = (ListView) view.findViewById(R.id.list);

		listview.setAdapter(mAdapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Log.i("ItemCategoryLoader", "Clicked on  "
						+ selectedItemCategoryList.get(position).getId()
						+ " , "
						+ selectedItemCategoryList.get(position).getName());

				OrderAdapterItems items[] = new OrderAdapterItems[selectedItemCategoryList
						.size()];
				int i = 0;
				for (ItemCategoryWeb web : selectedItemCategoryList) {
					items[i] = new OrderAdapterItems(selectedItemCategoryList
							.get(i).getName(), 0);
					i++;
				}

				if (!goIntoChildItemCategoryList(selectedItemCategoryList.get(
						position).getId())) {

					mCallbacks.onItemCategorySelected(selectedItemCategoryList
							.get(position));
					listview.clearDisappearingChildren();

					listadapter = new OrderListAdapter(getActivity(),
							R.layout.makeorderlistitems, items);
					listview.setAdapter(listadapter);
				}
				goIntoChildItemCategoryList(selectedItemCategoryList.get(
						position).getId());
			}
		});

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		listview.setChoiceMode(activateOnItemClick ? AbsListView.CHOICE_MODE_SINGLE
				: AbsListView.CHOICE_MODE_NONE);
	}

	@Override
	public Loader<List<ItemCategoryWeb>> onCreateLoader(int id, Bundle args) {

		return itemCategoryLoader;
	}

	@Override
	public void onLoadFinished(Loader<List<ItemCategoryWeb>> arg0,
			List<ItemCategoryWeb> itemCategoryList) {
		if (itemCategoryList == null) {
			return;
		}
		itemCategoryTree.clear();
		Long parentId;
		List<ItemCategoryWeb> oneList;
		for (ItemCategoryWeb oneItemCategory : itemCategoryList) {
			if (oneItemCategory.getParentCategoryId() == null
					|| oneItemCategory.getParentCategoryId() <= 0) {
				parentId = 0L;
			} else {
				parentId = oneItemCategory.getParentCategoryId();
			}

			oneList = itemCategoryTree.get(parentId);
			if (oneList == null) {
				oneList = new ArrayList<ItemCategoryWeb>();
				itemCategoryTree.put(parentId, oneList);
			}
			oneList.add(oneItemCategory);
		}
		categoryListStack.clear();
		showItemCategoryList(itemCategoryTree.get(0L));
	}

	private void showItemCategoryList(List<ItemCategoryWeb> oneList) {
		categoryListStack.add(oneList);
		selectedItemCategoryList.clear();
		selectedItemCategoryList.addAll(oneList);
		mAdapter.notifyDataSetChanged();
	}

	/*
	 * private void returnToParentItemCategoryList() { // Remove the top list
	 * categoryListStack.pop(); List<ItemCategoryWeb> oneList =
	 * categoryListStack.peek(); selectedItemCategoryList.clear();
	 * selectedItemCategoryList.addAll(oneList);
	 * mAdapter.notifyDataSetChanged(); if (categoryListStack.size() <= 1) {
	 * goBackButton.setEnabled(false); } }
	 */

	private boolean goIntoChildItemCategoryList(Long parentCategoryId) {
		Log.i("ItemCategoryLoader", "Opening Child Catgory of "
				+ parentCategoryId);
		List<ItemCategoryWeb> oneList = itemCategoryTree.get(parentCategoryId);
		if (oneList == null) {
			return false;
		}
		selectedItemCategoryList.clear();
		selectedItemCategoryList.addAll(oneList);
		mAdapter.notifyDataSetChanged();
		categoryListStack.add(oneList);
		
		return true;
	}

	@Override
	public void onLoaderReset(Loader<List<ItemCategoryWeb>> arg0) {
		itemCategoryTree.clear();
		mAdapter.notifyDataSetChanged();

	}

	@Override
	public void onClick(View v) {

	}

	public void resetList() {
		listview.clearDisappearingChildren();
		listview.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();

	}

}