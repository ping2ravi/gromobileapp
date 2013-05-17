package com.next.grocerysale.services.impl;

import com.next.grocerysale.server.services.DataServices;

public class DataServiceFactory {

	private static DataServices instance = DataServicesImpl.getInstance();
	public static DataServices getDataServices(){
		return instance;
	}
}
