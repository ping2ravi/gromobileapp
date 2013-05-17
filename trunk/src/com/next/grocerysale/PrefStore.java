package com.next.grocerysale;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefStore {

	private Activity mAct;
	private String PREFS_NAME = "OTG";

	public PrefStore(Activity aAct) {
		this.mAct = aAct;
	}

	public void saveServerPath(String sServerPath) {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);

		SharedPreferences.Editor editor = settings.edit();

		editor.putString("sServerPath", sServerPath);
		Log.i("PrefStore", "sServerPath " + sServerPath);
		editor.commit();
	}

	public String getServerPath() {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		String sServerPath = settings.getString("sServerPath", null);
		Log.i("PrefStore", "get sServerPath " + sServerPath);

		return sServerPath;
	}

	public void SaveLastVisit(String visitDateTime) {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);

		SharedPreferences.Editor editor = settings.edit();

		editor.putString("visitDateTime", visitDateTime);
		Log.i("PrefStore", "SAVELASTVISIT " + visitDateTime);
		editor.commit();
	}

	public String getLastVisitDetail() {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		Date nowDate_now = new Date();
		String visitTime = settings.getString("visitDateTime",
				nowDate_now.toString());
		Log.i("PrefStore", "get visitDateTime " + visitTime);

		return visitTime;
	}

	public void SaveLoginData(String userName, String password) {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("userName", userName);
		editor.putString("password", password);
		// Commit the edits!
		editor.commit();
		Log.i("PrefStore", "userName " + userName);
	}

	public void RemoveLoginData(String userName, String password) {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.remove("userName");
		editor.remove("password");
		// Commit the edits!
		editor.commit();
		Log.i("PrefStore", "userName " + userName);
	}

	public boolean getLoginStatus() {
		// Restore preferences
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		boolean loginValid = settings.getBoolean("loginValid", false);
		Log.i("PrefStore", "loginValid " + loginValid);
		return loginValid;
	}

	public String getUserName() {
		// Restore preferences
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		String userName = settings.getString("userName", null);
		Log.i("PrefStore", "userName " + userName);
		return userName;
	}

	public String getUserPassword() {
		// Restore preferences
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		String password = settings.getString("password", null);
		Log.i("PrefStore", "password " + password);
		return password;
	}

	public Boolean getRememberMe() {
		// Restore preferences
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		boolean rememberMe = settings.getBoolean("rememberMe", false);
		Log.i("PrefStore", "rememberMe " + rememberMe);
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("rememberMe", rememberMe);
		// Commit the edits!
		editor.commit();
		Log.i("PrefStore", "userName " + rememberMe);
	}

	public void saveFragmentButtonPosition(int position) {

		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_WRITEABLE);

		SharedPreferences.Editor editor = settings.edit();

		editor.putInt("position", position);

		editor.commit();

	}

	public int getFragmentButtonPosition() {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);

		int position = settings.getInt("position", 0);
		// Log.i("PrefStore", "durationFormatItemPosition "
		// + durationFormatItemPosition);

		return position;
	}

	public void saveOtgSize(int size) {

		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_WRITEABLE);

		SharedPreferences.Editor editor = settings.edit();

		editor.putInt("size", size);

		editor.commit();

	}

	public int getOtgSize() {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);

		int size = settings.getInt("size", 0);
		// Log.i("PrefStore", "durationFormatItemPosition "
		// + durationFormatItemPosition);

		return size;
	}

	public void saveTypeId(int type_id) {

		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_WRITEABLE);

		SharedPreferences.Editor editor = settings.edit();

		editor.putInt("type_id", type_id);

		editor.commit();

	}

	public int getTypeId() {
		SharedPreferences settings = mAct.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);

		int type_id = settings.getInt("type_id", 0);
		// Log.i("PrefStore", "durationFormatItemPosition "
		// + durationFormatItemPosition);

		return type_id;
	}

}
