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

	public static void accessDatabase() {

		// Accessing Driver from JAR File
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Creating variable for connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false", "root", null);

			// Creating a query
			
			PreparedStatement statement = con.prepareStatement("SELECT * FROM scores");

			// Create variable to execute query
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				System.out.println(result.getString("score") + " " + result.getString("name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}