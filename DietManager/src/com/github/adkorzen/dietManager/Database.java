package com.github.adkorzen.dietManager;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu;

public class Database {
	public void addToDatabase() {
		new AddToDatabaseMenu().createAndShowGUI();
		System.out.println("added to database");
	}

	public void editDatabase() {
		System.out.println("database edited");
	}

	public void checkDatabase() {
		System.out.println("database checked");
	}

	public void proceed() {
		System.out.println("Proceed to day menu");
	}
}
