package com.next.grocerysale.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import com.next.grocery.client.CityVillageWeb;
import com.next.grocery.client.CustomerWeb;
import com.next.grocery.client.DistrictWeb;
import com.next.grocery.client.ItemCategoryWeb;
import com.next.grocery.client.LocalAreaWeb;
import com.next.grocery.client.StateWeb;
import com.next.grocery.client.UserWeb;
import com.next.grocery.client.ext.ItemWithItemPackExtWeb;
import com.next.grocery.client.ext.requests.GetCityVillageRequest;
import com.next.grocery.client.ext.requests.GetDistrictsRequest;
import com.next.grocery.client.ext.requests.GetItemByCategoryRequest;
import com.next.grocery.client.ext.requests.GetLocalAreaRequest;
import com.next.grocery.client.ext.requests.LoginRequest;
import com.next.grocery.client.ext.requests.SaveCustomerRequest;
import com.next.grocery.client.ext.requests.SearchCustomerByCityRequest;
import com.next.grocery.client.ext.requests.SearchCustomerByLocalAreaRequest;
import com.next.grocerysale.server.services.DataServices;

public class DataServicesImpl implements DataServices {

	//private static final String baseUrl = "http://192.168.1.4:8081/Grocery/api/";
	private static final String baseUrl = "http://208.109.232.106:8082/grocery/api/";
	
	private static final String getAllItemCategoriesUrl = "category/getallcategories";
	private static final String getAllItemByCategoryUrl = "item/getitembycategory";
	private static final String getAllStatesUrl = "location/getstates";
	private static final String getAllDistrictsOfStatesUrl = "location/getdistricts";
	private static final String getAllCitiesOfDistrictsUrl = "location/getcityvillages";
	private static final String getAllLocalAreaOfCityUrl = "location/getlocalareas";
	
	private static final String loginUrl = "login";
	
	private static final String saveCustomerUrl = "customer/savecustomer";
	private static final String searchCustomerByCityUrl = "customer/searchcustomerbycity";
	private static final String searchCustomerByCityLocalAreaUrl = "customer/searchcustomerbylocalarea";
	private static final String getCustomerByIdUrl = "customer/getcustomerbyid";
	
	
	
	private static DataServices instance = new DataServicesImpl();
	private DataServicesImpl(){
		
	}
	public static DataServices getInstance(){
		return instance;
	}
	
	public static <T> T postSpringData(String url, Class<T> responseType,Object message) {
		Log.i("DataServicesImpl","Hitting Post Url "+ url);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application", "json"));
		
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(message,requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		ResponseEntity<T> responseNtity = restTemplate.postForEntity(url, requestEntity, responseType);
		Log.i("DataServicesImpl","responseNtity.getBody : "+ responseNtity.getBody());
		return responseNtity.getBody();
	}
	public static <T> T getSpringData(String url, Class<T> responseType) {
		Log.i("DataServicesImpl","Hitting Get Url "+ url);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application", "json"));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		ResponseEntity<T> responseNtity = restTemplate.getForEntity(url, responseType);
		Log.i("DataServicesImpl","responseNtity.getBody : "+ responseNtity.getBody());
		return responseNtity.getBody();
	}
	
	@Override
	public List<ItemCategoryWeb> getAllItemCategories() {
		String url = baseUrl + getAllItemCategoriesUrl;
		ItemCategoryWeb[] itemCategoryList = getSpringData(url, ItemCategoryWeb[].class);
		return Arrays.asList(itemCategoryList);
	}

	@Override
	public List<ItemWithItemPackExtWeb> getItemsOfCategory(Long itemCategoryId,int pageNumber,int pageSize) {
		GetItemByCategoryRequest getItemByCategoryRequest = new GetItemByCategoryRequest();
		getItemByCategoryRequest.setItemCategoryId(itemCategoryId);
		getItemByCategoryRequest.setPageNumber(pageNumber);
		getItemByCategoryRequest.setPageSize(pageSize);
		String url = baseUrl + getAllItemByCategoryUrl;
		ItemWithItemPackExtWeb[] itemWithItemPackExtList = postSpringData(url, ItemWithItemPackExtWeb[].class, getItemByCategoryRequest);
		return Arrays.asList(itemWithItemPackExtList);
	}

	@Override
	public CustomerWeb saveCustomer(SaveCustomerRequest saveCustomerRequest) {
		String url = baseUrl + saveCustomerUrl;
		CustomerWeb customerWeb = postSpringData(url, CustomerWeb.class, saveCustomerRequest);
		return customerWeb;
	}

	@Override
	public List<CustomerWeb> getCustomerOfCity(Long cityId, int pageNumber,int pageSize) {
		SearchCustomerByCityRequest searchCustomerByCityRequest = new SearchCustomerByCityRequest();
		searchCustomerByCityRequest.setCityId(cityId);
		searchCustomerByCityRequest.setPageNumber(pageNumber);
		searchCustomerByCityRequest.setPageSize(pageSize);
		String url = baseUrl + searchCustomerByCityUrl;
		CustomerWeb[] customerList = postSpringData(url, CustomerWeb[].class, searchCustomerByCityRequest);
		return Arrays.asList(customerList);
	}

	@Override
	public List<CustomerWeb> getCustomerOfLocalArea(Long localAreaId, int pageNumber, int pageSize) {
		SearchCustomerByLocalAreaRequest searchCustomerByLocalAreaRequest = new SearchCustomerByLocalAreaRequest();
		searchCustomerByLocalAreaRequest.setLocalAreaId(localAreaId);
		searchCustomerByLocalAreaRequest.setPageNumber(pageNumber);
		searchCustomerByLocalAreaRequest.setPageSize(pageSize);
		String url = baseUrl + searchCustomerByCityLocalAreaUrl;
		CustomerWeb[] customerList = postSpringData(url, CustomerWeb[].class, searchCustomerByLocalAreaRequest);
		return Arrays.asList(customerList);
	}

	@Override
	public CustomerWeb getCustomerById(Long customerId) {
		String url = baseUrl + getCustomerByIdUrl+"/"+customerId;
		CustomerWeb customerWeb = getSpringData(url, CustomerWeb.class);
		return customerWeb;
	}

	@Override
	public CustomerWeb getCustomerByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateWeb> getAllStates() {
		String url = baseUrl + getAllStatesUrl;
		StateWeb[] stateWeb = getSpringData(url, StateWeb[].class);
		return Arrays.asList(stateWeb);
	}

	@Override
	public List<DistrictWeb> getAllDistrictOfState(Long stateId) {
		String url = baseUrl + getAllDistrictsOfStatesUrl;
		GetDistrictsRequest getDistrictsRequest = new GetDistrictsRequest();
		getDistrictsRequest.setStateId(stateId);
		DistrictWeb[] districtList = postSpringData(url, DistrictWeb[].class,getDistrictsRequest);
		return Arrays.asList(districtList);
	}

	@Override
	public List<CityVillageWeb> getAllCityVillageOfDistrict(Long districtId) {
		String url = baseUrl + getAllCitiesOfDistrictsUrl;
		GetCityVillageRequest getCityVillageRequest = new GetCityVillageRequest();
		getCityVillageRequest.setDistrictId(districtId);
		CityVillageWeb[] cityList = postSpringData(url, CityVillageWeb[].class,getCityVillageRequest);
		return Arrays.asList(cityList);
	}

	@Override
	public List<LocalAreaWeb> getAllLocalAreaOfCityVillage(Long cityVillageId) {
		String url = baseUrl + getAllLocalAreaOfCityUrl;
		GetLocalAreaRequest getLocalAreaRequest = new GetLocalAreaRequest();
		getLocalAreaRequest.setCityVillageId(cityVillageId);
		LocalAreaWeb[] localAreaList = postSpringData(url, LocalAreaWeb[].class,getLocalAreaRequest);
		return Arrays.asList(localAreaList);
	}
	@Override
	public UserWeb login(String loginId, String password) {
		String url = baseUrl + loginUrl;
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setLoginId(loginId);
		loginRequest.setPassword(password);
		UserWeb user = postSpringData(url, UserWeb.class,loginRequest);
		return user;
	}

}
