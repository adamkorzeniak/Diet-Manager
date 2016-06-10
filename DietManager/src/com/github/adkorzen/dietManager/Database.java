package com.github.adkorzen.dietManager;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.DayMenu;

public class Database {
	public static void addToDatabase() {
		new AddToDatabaseMenu().createAndShowGUI();
		System.out.println("added to database");
	}

	public static void editDatabase() {
		System.out.println("database edited");
	}

	public static void checkDatabase() {
		System.out.println("database checked");
	}
	public static void proceed() {
		new DayMenu().createAndShowGUI();
	}
}
