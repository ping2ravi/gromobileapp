package com.next.grocerysale.util;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	public static String getSringFromJsonObject(JSONObject jsonObject, String field) {
		try {
			return jsonObject.getString(field);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
