package ru.myhome.DataFile;

public class MaterialPrice {
	
	private Material material;
	private String provider;
	private double cost;
	
	public MaterialPrice() {
	}
	
	public MaterialPrice(String type, double thickness, double cost, String provider) {
		
		material = new Material(type, thickness);
		this.provider = provider;
		this.cost = cost;
	}

	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getType() {
		return material.getType();
	}
	public void setType(String type) {
		this.material.setType(type);
	}
	public double getThickness() {
		return material.getThickness();
	}
	public void setThickness(double thickness) {
		this.material.setThickness(thickness);
	}

	@Override
	public String toString() {
		return "MaterialPrice [material=" + material + ", provider=" + provider + ", cost=" + cost + "]";
	}
	

}
