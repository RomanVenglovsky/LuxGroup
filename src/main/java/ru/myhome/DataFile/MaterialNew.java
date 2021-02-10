package ru.myhome.DataFile;

public class MaterialNew {
	
	private String type;
	private int thickness;
	private double cost;
	private CutPrice сutPrice;
	private String provider;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getСutPrice() {
		return сutPrice.getCp1();
	}
	public double getСutPrice(double l) {
		return (l <= 200) ? сutPrice.getCp1():сutPrice.getCp2();
	}
	public void setСutPrice(double cp1, double cp2) {
		this.сutPrice.setCp1(cp1);
		this.сutPrice.setCp2(cp2);
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	private class CutPrice{
		private double cp1;
		private double cp2;
		public double getCp1() {
			return cp1;
		}
		public void setCp1(double cp1) {
			this.cp1 = cp1;
		}
		public double getCp2() {
			return cp2;
		}
		public void setCp2(double cp2) {
			this.cp2 = cp2;
		}
	}
}
