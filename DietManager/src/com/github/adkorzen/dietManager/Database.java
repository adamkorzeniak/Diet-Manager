package com.github.adkorzen.dietManager;

import java.util.Date;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.DayMenu;
import com.github.adkorzen.dietManager.GUI.MainMenu;

public class Database {
	public static void addToDatabase() {
		new AddToDatabaseMenu().createAndShowGUI();
	}

	public static void editDatabase() {
		System.out.println("database edited");
	}

	public static void checkDatabase() {
		System.out.println("database checked");
	}
	public static void proceed() {
		ManageDate.setDate((Date)MainMenu.getSpinerEditor().getValue());
		new DayMenu().createAndShowGUI();
	}
}
