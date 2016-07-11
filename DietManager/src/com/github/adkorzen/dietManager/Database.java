package com.github.adkorzen.dietManager;

import java.util.Date;

import javax.swing.JOptionPane;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.CheckDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.DayMenu;
import com.github.adkorzen.dietManager.GUI.EditDatabaseMenu;
import com.github.adkorzen.dietManager.GUI.MainMenu;
import static com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.*;

public class Database {
	public static void addToDatabase() {
		AddToDatabaseMenu.createAndShowGUI();
	}

	public static void editDatabase() {
		EditDatabaseMenu.createAndShowGUI();
	}

	public static void checkDatabase() {
		CheckDatabaseMenu.createAndShowGUI();
	}

	public static void proceed() {
		ManageDate.setDate((Date) MainMenu.getSpinerEditor().getValue());
		new DayMenu().createAndShowGUI();
	}

	public static void addProductToDatabase() {
		Product product = null;
		if (getCarbs() + getProteins() + getFats() > 0) {
			product = new Product(getName(), getUnit(), getUnitDivider(), getCalories(), getCarbs(), getProteins(),
					getFats());
		} else {
			product = new Product(getName(), getUnit(), getUnitDivider(), getCalories());
		}
		if (!DatabaseManagement.getInstance().contains(product)) {
			DatabaseManagement.getInstance().addProductToDatabase(product);
			JOptionPane.showMessageDialog(null, "You added product to database");

		} else {
			JOptionPane.showMessageDialog(null, "There is already an product with this name in database");
		}
	}
}
