package ru.myhome.DataFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CutPriceList {
	/*
	 * String type
	 * double thickness
	 * cut price
	 */
	
	private Map<String, ArrayList<CutPrice>> priceList;
	
	public double getCutPrice(String key, double thickness, double l) {
		ArrayList<CutPrice> item = priceList.get(key);
		for(CutPrice temp: item) {
			if(thickness >= temp.getThickMin() && thickness <= temp.getThickMax()) {
				return temp.getСutPrice(l);
			}
		}
		return 0.0;
	}
	public double getCutPrice(String key, double thickness) {
		ArrayList<CutPrice> item = priceList.get(key);
		for(CutPrice temp: item) {
			if(thickness >= temp.getThickMin() && thickness <= temp.getThickMax()) {
				return temp.getСutPrice(0.0);
			}
		}
		return 0.0;
	}
	public void addItem(CutPrice cutPrice) {
		if(priceList == null) priceList = new HashMap<String, ArrayList<CutPrice>>();
		String key = cutPrice.getType();
		if(priceList.containsKey(key)) {
			priceList.get(key).add(cutPrice);
		}
		else {
			ArrayList<CutPrice> temp = new ArrayList<CutPrice>();
			temp.add(cutPrice);
			priceList.put(key, temp);
		}
	}
	public ArrayList<String> getMaterialsList(){
		return new ArrayList<String>(priceList.keySet());
	}
}
