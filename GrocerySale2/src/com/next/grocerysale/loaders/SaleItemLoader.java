package com.next.grocerysale.loaders;

import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.next.grocery.client.ext.ItemWithItemPackExtWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

public class SaleItemLoader extends
		AsyncTaskLoader<List<ItemWithItemPackExtWeb>> {

	private List<ItemWithItemPackExtWeb> cachedSaleItems;
	private DataServices dataServices;
	private Long itemCategoryId;

	public SaleItemLoader(Context context, long itemCategoryId) {
		super(context);
		this.itemCategoryId = itemCategoryId;
		dataServices = DataServiceFactory.getDataServices();
	}

	@Override
	public List<ItemWithItemPackExtWeb> loadInBackground() {

		Log.i("SaleItemLoader", "loadInBackground");

		return dataServices.getItemsOfCategory(itemCategoryId,5,5);
		// return cachedSaleItems;

	}

	@Override
	public void deliverResult(List<ItemWithItemPackExtWeb> data) {
		Log.i("SaleItemLoader", "deliverResult " + data);
		if (isReset()) {
			// The Loader has been reset; ignore the result and invalidate the
			// data.
			Log.i("SaleItemLoader", "isReset() true ");
			onReleaseResources(data);
			return;
		}

		// Hold a reference to the old data so it doesn't get garbage collected.
		// The old data may still be in use (i.e. bound to an adapter, etc.), so
		// we must protect it until the new data has been delivered.
		List<ItemWithItemPackExtWeb> oldData = cachedSaleItems;
		cachedSaleItems = data;

		if (isStarted()) {
			// If the Loader is in a started state, deliver the results to the
			// client. The superclass method does this for us.
			super.deliverResult(data);
			Log.i("SaleItemLoader", "isStarted() true ");
		}

		// Invalidate the old data as we don't need it any more.
		if (oldData != null && oldData != data) {
			Log.i("SaleItemLoader", "onReleaseResources ");
			onReleaseResources(oldData);
		}
	}

	/*********************************************************/
	/** (3) Implement the Loader’s state-dependent behavior **/
	/*********************************************************/

	@Override
	protected void onStartLoading() {
		if (cachedSaleItems != null) {
			// Deliver any previously loaded data immediately.
			deliverResult(cachedSaleItems);
		}

		/*
		 * // Begin monitoring the underlying data source. if (mObserver ==
		 * null) { mObserver = new SampleObserver(); // TODO: register the
		 * observer }
		 */

		if (takeContentChanged() || cachedSaleItems == null) {
			// When the observer detects a change, it should call
			// onContentChanged()
			// on the Loader, which will cause the next call to
			// takeContentChanged()
			// to return true. If this is ever the case (or if the current data
			// is
			// null), we force a new load.
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading() {
		// The Loader is in a stopped state, so we should attempt to cancel the
		// current load (if there is one).
		cancelLoad();

		// Note that we leave the observer as is; Loaders in a stopped state
		// should still monitor the data source for changes so that the Loader
		// will know to force a new load if it is ever started again.
	}

	@Override
	protected void onReset() {
		// Ensure the loader has been stopped.
		onStopLoading();

		// At this point we can release the resources associated with 'mData'.
		if (cachedSaleItems != null) {
			onReleaseResources(cachedSaleItems);
			cachedSaleItems = null;
		}

		/*
		 * // The Loader is being reset, so we should stop monitoring for
		 * changes. if (mObserver != null) { // TODO: unregister the observer
		 * mObserver = null; }
		 */
	}

	@Override
	public void onCanceled(List<ItemWithItemPackExtWeb> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled(data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		onReleaseResources(data);
	}

	protected void onReleaseResources(List<ItemWithItemPackExtWeb> data) {
		// For a simple List, there is nothing to do. For something like a
		// Cursor, we
		// would close it in this method. All resources associated with the
		// Loader
		// should be released here.
	}

	public void setItemCategoryId(Long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
}
