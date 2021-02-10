package ru.myhome.DataFile;

public class CutPrice {
	private String type;
	private double cp1;
	private double cp2;
	private double thickMin;
	private double thickMax;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getСutPrice() {
		return cp1;
	}
	public double getСutPrice(double l) {
		return (l <= 200) ? cp1:cp2;
	}
	public void setСutPrice(double cp1, double cp2) {
		this.cp1 = cp1;
		this.cp2 = cp2;
	}
	public double getThickMin() {
		return thickMin;
	}
	public void setThickMin(double thickMin) {
		this.thickMin = thickMin;
	}
	public double getThickMax() {
		return thickMax;
	}
	public void setThickMax(double thickMax) {
		this.thickMax = thickMax;
	}
	@Override
	public String toString() {
		return type + ", cp1=" + cp1 + ", cp2=" + cp2 + ", thickMin=" + thickMin + ", thickMax="
				+ thickMax;
	}
	
}
