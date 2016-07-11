package com.github.adkorzen.dietManager;

import static com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.getCarbs;
import static com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.getFats;
import static com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.getProteins;

import java.util.TreeMap;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;

public class Product {
	private String name;
	private UNITS primaryUnit;
	private int unitDivider;
	private double caloriesPerUnit;
	private double carbs, proteins, fats;
	private TreeMap secondaryUnits;
	
	public Product(String name, AddToDatabaseMenu.UNITS primaryUnit, int unitDivider, double caloriesPerUnit, double carbs, double proteins, double fats) {
		this.name = name.toLowerCase();
		this.primaryUnit = primaryUnit;
		this.unitDivider = unitDivider;
		this.caloriesPerUnit = caloriesPerUnit;
		this.carbs = carbs;
		this.proteins = proteins;
		this.fats = fats;
	}
	public Product (String name, UNITS primaryUnit, int unitDivider, double caloriesPerUnit) {
		this(name, primaryUnit, unitDivider, caloriesPerUnit, 0.0, 0.0, 0.0);
	}
	
	public String toString() {
		String result = String.format("Product name: %s\nPrimary Unit: %d %s\nCalories: %.1f kcal", name, unitDivider, primaryUnit, caloriesPerUnit); 
		if (getCarbs() + getProteins() + getFats() > 0) {
			String optional = String.format("\nCarbs: %.2f g\nProteins: %.2f g\nFats: %.2f g", carbs, proteins, fats); 
			result += optional;
		}
		return result;
	}
	
	public String getName() {
		return name;
	}
	public UNITS getPrimaryUnit() {
		return primaryUnit;
	}
	public int getUnitDivider() {
		return unitDivider;
	}
	public double getCaloriesPerUnit() {
		return caloriesPerUnit;
	}
	public double getCarbs() {
		return carbs;
	}
	public double getProteins() {
		return proteins;
	}
	public double getFats() {
		return fats;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
