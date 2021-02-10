package ru.myhome.DataFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MaterialPriceList {
	
	/*
	 * String type
	 * double thickness
	 * double cost
	 * String provider
	 */
	private Map<String, Map<Double, MaterialPrice>> priceList;
	
	public double getCost(String key, double thickness) {
		Map<Double, MaterialPrice> item = priceList.get(key);
		try{
			return item.get(thickness).getCost();
		}
		catch (Exception e) {
			return 0;
		}
	}
	public String getProvider(String key, double thickness) {
		Map<Double, MaterialPrice> item = priceList.get(key);
		return item.get(thickness).getProvider();
	}
	
	public void addItem(MaterialPrice mPrice) {
		if(priceList == null) priceList = new HashMap<String, Map<Double, MaterialPrice>>();
		String key = mPrice.getType();
		if(priceList.containsKey(key)) {
			priceList.get(key).put(mPrice.getThickness(), mPrice);
		}
		else {
			Map<Double, MaterialPrice> temp = new HashMap<Double, MaterialPrice>();
			temp.put(mPrice.getThickness(), mPrice);
			priceList.put(key, temp);
		}
	}
	public ArrayList<String> getMaterialsList(){
		return new ArrayList<String>(priceList.keySet());
	}
}