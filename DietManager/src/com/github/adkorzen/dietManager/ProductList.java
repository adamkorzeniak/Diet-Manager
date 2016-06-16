package com.github.adkorzen.dietManager;

import java.util.HashSet;

public class ProductList {
	private static HashSet<Product> products = new HashSet<Product>();
	
	public static void addProduct(Product p) {
		products.add(p);
	}
	public static HashSet<Product> getList() {
		return products;
	}
}
