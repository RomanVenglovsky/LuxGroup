package ru.myhome.DataFile;

public class Material {
	
	private String type;
	private double thickness;
	
	public Material() {
	}
	public Material(String type, double thickness) {
		
		this.type = type;
		this.thickness = thickness;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getThickness() {
		return thickness;
	}
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
	
}
