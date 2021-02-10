package ru.myhome.DataFile;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MaterialsList {
	
	private ArrayList<String> mList;
	
	@Autowired @Qualifier("mPrice")
	private MaterialPriceList mPriceList;
	
	@Autowired @Qualifier("cutPrice")
	private CutPriceList cutPriceList;

	public String getMaterial(String type) {
		if(mList.contains(type)) return type;
		//if(mList.)
		return null;
	}
	
	public MaterialPriceList getmPriceList() {
		return mPriceList;
	}

	public void setmPriceList(MaterialPriceList mPriceList) {
		this.mPriceList = mPriceList;
	}

	public CutPriceList getCutPriceList() {
		return cutPriceList;
	}

	public void setCutPriceList(CutPriceList cutPriceList) {
		this.cutPriceList = cutPriceList;
	}

	public MaterialsList() {
		System.out.println("Creating a new list of material...");
	}

	public ArrayList<String> getmList() {
		/*if (mList == null) {
			loadMList();
		}*/
		Collections.sort(mList);
		return mList;
	}

	public void setmList(ArrayList<String> mList) {
		this.mList = mList;
	}

	private void loadMList(){
		
		if(null == cutPriceList) System.out.println("cutPriceList is null");
		mList = new ArrayList<String>(cutPriceList.getMaterialsList());
		ArrayList<String> temp = new ArrayList<String>(mPriceList.getMaterialsList());
		if(!mList.containsAll(temp)) {
			for(String item: temp) {
				if(!mList.contains(item))
					mList.add(item);
			}
		}
	}
	public double getmCost(String type, double thickness) {
		
		return mPriceList.getCost(type, thickness);
	}
	public double getCutCost(String type, double thickness) {
		
		return cutPriceList.getCutPrice(type, thickness);
	}
	public double getCutCost(String type, double thickness, double l) {
		
		return cutPriceList.getCutPrice(type, thickness, l);
	}
}
