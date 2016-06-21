package com.github.adkorzen.dietManager.GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;

public class Table {
	public static JTable table;

	static String[] columnNames = { "mealName", "calories", "carbs", "proteins", "fats" };

	static String[][] data = { { "P³aki owsiane, 150 g", "350", "30", "10", "5" },
			{ "Mleko, 400 g", "160", "15", "5", "10" } };

	public static void createTable() {
		table = new JTable(data, columnNames);
		table.setEnabled(false);
	}
	
	

}