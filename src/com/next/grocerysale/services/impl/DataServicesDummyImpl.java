package com.next.grocerysale.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.next.grocery.client.CityVillageWeb;
import com.next.grocery.client.CustomerWeb;
import com.next.grocery.client.DistrictWeb;
import com.next.grocery.client.ItemCategoryWeb;
import com.next.grocery.client.LocalAreaWeb;
import com.next.grocery.client.StateWeb;
import com.next.grocery.client.UserWeb;
import com.next.grocery.client.ext.ItemWithItemPackExtWeb;
import com.next.grocery.client.ext.requests.SaveCustomerRequest;
import com.next.grocerysale.server.services.DataServices;

public class DataServicesDummyImpl implements DataServices {

	private static DataServices instance = new DataServicesDummyImpl();
	private DataServicesDummyImpl(){
		
	}
	public static DataServices getInstance(){
		return instance;
	}
	@Override
	public List<ItemCategoryWeb> getAllItemCategories() {
		List<ItemCategoryWeb> returnList = new ArrayList<ItemCategoryWeb>();
		creteItemCategoryWeb(returnList,1L, "Fresh Food", 0L);
			creteItemCategoryWeb(returnList,19L, "Fresh Fruit", 1L);
				creteItemCategoryWeb(returnList,8L, "Apple & Pears", 19L);
				creteItemCategoryWeb(returnList,9L, "Bananas", 19L);
				creteItemCategoryWeb(returnList,10L, "Dry Fruits and Nuts", 19L);
				creteItemCategoryWeb(returnList,11L, "Grapes", 19L);
				creteItemCategoryWeb(returnList,12L, "Organic Fruits", 19L);
			creteItemCategoryWeb(returnList,20L, "Fresh Vegetables", 1L);
				creteItemCategoryWeb(returnList,21L, "Baby Vegetables", 20L);
				creteItemCategoryWeb(returnList,22L, "Carrots and Root Vegetables", 20L);
				creteItemCategoryWeb(returnList,23L, "Chillies Garlic & Ginger", 20L);
				creteItemCategoryWeb(returnList,24L, "Potatoes", 20L);

		creteItemCategoryWeb(returnList,2L, "Bakery", 0L);
			creteItemCategoryWeb(returnList,13L, "Bread", 2L);
				creteItemCategoryWeb(returnList,25L, "Brown Bread", 13L);
				creteItemCategoryWeb(returnList,26L, "White Bread", 13L);
				creteItemCategoryWeb(returnList,27L, "Half & Half Bread", 13L);
				creteItemCategoryWeb(returnList,28L, "Seeded Bread", 13L);
			creteItemCategoryWeb(returnList,14L, "Bread Roll & Bagels", 2L);
				creteItemCategoryWeb(returnList,29L, "Bagels", 14L);
				creteItemCategoryWeb(returnList,30L, "Bread Rolls", 14L);
			creteItemCategoryWeb(returnList,15L, "Wraps Pita & Naan", 2L);
				creteItemCategoryWeb(returnList,31L, "Naan", 15L);
				creteItemCategoryWeb(returnList,32L, "Pita", 15L);
				creteItemCategoryWeb(returnList,33L, "Wraps", 15L);
			creteItemCategoryWeb(returnList,16L, "Specialist Bakery", 2L);
				creteItemCategoryWeb(returnList,34L, "Bakery Free From", 16L);
			creteItemCategoryWeb(returnList,17L, "Sweet Treats", 2L);
				creteItemCategoryWeb(returnList,35L, "Cookies & Biscuits", 17L);
				creteItemCategoryWeb(returnList,36L, "Muffins and Buns", 17L);
			creteItemCategoryWeb(returnList,18L, "Cakes & Pies", 2L);
				creteItemCategoryWeb(returnList,37L, "Cake Slice", 18L);
				creteItemCategoryWeb(returnList,38L, "FlapJacks", 18L);
				creteItemCategoryWeb(returnList,39L, "Fruitpies", 18L);
				creteItemCategoryWeb(returnList,40L, "Large Cakes", 18L);
				creteItemCategoryWeb(returnList,41L, "Small cakes", 18L);
		creteItemCategoryWeb(returnList,3L, "Food cupboard", 0L);
			creteItemCategoryWeb(returnList,42L, "Tins Cans & Packets", 3L);
				creteItemCategoryWeb(returnList,43L, "Dried Friuts & Nuts", 42L);
				creteItemCategoryWeb(returnList,44L, "Instant Snacks", 42L);
				creteItemCategoryWeb(returnList,45L, "Tinned Fruits", 42L);
				creteItemCategoryWeb(returnList,46L, "Tinned Vegetables", 42L);
				creteItemCategoryWeb(returnList,47L, "Pulses", 42L);
			creteItemCategoryWeb(returnList,48L, "Cooking Ingredients", 3L);
				creteItemCategoryWeb(returnList,49L, "Gravy and Stock Cubes", 48L);
				creteItemCategoryWeb(returnList,50L, "Herbs Spices and Salt", 48L);
				creteItemCategoryWeb(returnList,51L, "Oils and Fats", 48L);
				creteItemCategoryWeb(returnList,52L, "Sauce Mixes", 48L);
			creteItemCategoryWeb(returnList,53L, "Cooking Sauces", 3L);
				creteItemCategoryWeb(returnList,54L, "Indian", 53L);
				creteItemCategoryWeb(returnList,55L, "Italian", 53L);
				creteItemCategoryWeb(returnList,56L, "Mexican", 53L);
				creteItemCategoryWeb(returnList,57L, "Oriental", 53L);
			creteItemCategoryWeb(returnList,58L, "Baking", 3L);
				creteItemCategoryWeb(returnList,59L, "Baking Mixes", 58L);
				creteItemCategoryWeb(returnList,60L, "Baking", 58L);
				creteItemCategoryWeb(returnList,61L, "Flour", 58L);
		creteItemCategoryWeb(returnList,4L, "Drinks", 0L);
			creteItemCategoryWeb(returnList,62L, "Baking", 4L);
				creteItemCategoryWeb(returnList,63L, "Soft Drinks", 62L);
				creteItemCategoryWeb(returnList,64L, "Fruit Juice", 62L);
				creteItemCategoryWeb(returnList,65L, "Tea", 62L);
				creteItemCategoryWeb(returnList,66L, "Coffee", 62L);
		creteItemCategoryWeb(returnList,5L, "Baby", 0L);
			creteItemCategoryWeb(returnList,67L, "Nappies", 5L);
				creteItemCategoryWeb(returnList,68L, "Huggiess Nappies", 67L);
				creteItemCategoryWeb(returnList,69L, "Nappy Sacks", 67L);
				creteItemCategoryWeb(returnList,70L, "Nature Nappies", 67L);
				creteItemCategoryWeb(returnList,71L, "Tesco Nappies", 67L);
			creteItemCategoryWeb(returnList,72L, "Baby Milk", 5L);
				creteItemCategoryWeb(returnList,73L, "Aptamil", 72L);
				creteItemCategoryWeb(returnList,74L, "Cow & Gate", 72L);
				creteItemCategoryWeb(returnList,75L, "SMA", 72L);
		creteItemCategoryWeb(returnList,6L, "Health & Beauty", 0L);
		creteItemCategoryWeb(returnList,7L, "Household", 0L);
		
		
		

		creteItemCategoryWeb(returnList,76L, "Apple & Pears", 1L);
		creteItemCategoryWeb(returnList,77L, "Bananas", 1L);
		creteItemCategoryWeb(returnList,78L, "Dry Fruits and Nuts", 1L);
		creteItemCategoryWeb(returnList,79L, "Grapes", 1L);
		creteItemCategoryWeb(returnList,80L, "Organic Fruits", 1L);

		return returnList;
	}
	private void creteItemCategoryWeb(List<ItemCategoryWeb> itemCategoryList,Long id,String name,Long parentId){
		//check if id already exists or parent id exists
		boolean parentFound = false;
		if(parentId == 0L){
			parentFound = true;
		}else{
			for(ItemCategoryWeb oneItemCategoryWeb:itemCategoryList){
				if(oneItemCategoryWeb.getId().equals(id)){
					throw new RuntimeException("Id "+ id+" already exists");
				}
				if(oneItemCategoryWeb.getId().equals(parentId)){
					parentFound = true;
				}
			}
			
		}
		if(!parentFound){
			throw new RuntimeException("Parent Id "+ parentId+" not found");
		}
		ItemCategoryWeb itemCategoryWeb = new ItemCategoryWeb();
		itemCategoryWeb.setId(id);
		itemCategoryWeb.setName(name);
		itemCategoryWeb.setParentCategoryId(parentId);
		itemCategoryList.add(itemCategoryWeb);
	}
	@Override
	public List<ItemWithItemPackExtWeb> getItemsOfCategory(Long categoryId,int pageNumber,int PageSize) {
		List<ItemWithItemPackExtWeb> returnList = new ArrayList<ItemWithItemPackExtWeb>();
		
		return returnList;
	}
	@Override
	public CustomerWeb saveCustomer(
			SaveCustomerRequest saveCustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CustomerWeb> getCustomerOfCity(Long cityId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CustomerWeb> getCustomerOfLocalArea(Long localAreaId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerWeb getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerWeb getCustomerByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<StateWeb> getAllStates() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<DistrictWeb> getAllDistrictOfState(Long stateId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CityVillageWeb> getAllCityVillageOfDistrict(Long districtId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LocalAreaWeb> getAllLocalAreaOfCityVillage(Long cityVillageId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserWeb login(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
