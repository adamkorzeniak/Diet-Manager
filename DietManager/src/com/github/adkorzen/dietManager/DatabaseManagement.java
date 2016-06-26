package com.github.adkorzen.dietManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;
import com.github.adkorzen.dietManager.GUI.EditDatabaseMenu;

public class DatabaseManagement {

	private static DatabaseManagement instance = null;
	private String host, database, login, password;
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("database.txt")));
			host = br.readLine();
			database = br.readLine();
			login = br.readLine();
			password = br.readLine();
			if (password.equals("null"))
				password = null;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DatabaseManagement() {
	}

	public static DatabaseManagement getInstance() {
		if (instance == null) {
			instance = new DatabaseManagement();
		}
		return instance;
	}

	public void createTables() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(host + database + "?autoReconnect=true&useSSL=false", login,
					password);
			DatabaseMetaData dbm = con.getMetaData();

			createMealTable(con, dbm);
			createSecondaryUnitsTable(con, dbm);
			createCalendarTable(con, dbm);
			createDaysTable(con, dbm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createMealTable(Connection con, DatabaseMetaData dbm) {
		try {
			ResultSet tables = dbm.getTables(null, null, "meal", null);
			if (tables.next()) {
			} else {
				String s = "CREATE TABLE meal (";
				String c1 = "Name VARCHAR(255) PRIMARY KEY";
				String c2 = ",Unit_amount INT";
				String c3 = ",Primary_Unit VARCHAR(255)";
				String c4 = ",Calories REAL";
				String c5 = ",Carbs REAL";
				String c6 = ",Proteins REAL";
				String c7 = ",Fats REAL";
				String end = ")";
				PreparedStatement statement = con.prepareStatement(s + c1 + c2 + c3 + c4 + c5 + c6 + c7 + end);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createSecondaryUnitsTable(Connection con, DatabaseMetaData dbm) {
		try {
			ResultSet tables = dbm.getTables(null, null, "secondary_units", null);
			if (tables.next()) {
			} else {
				String s = "CREATE TABLE secondary_units (";
				String c1 = "Meal_Name VARCHAR(255)";
				String c2 = ",Unit_Name VARCHAR(255)";
				String c3 = ",Multiplier REAL";
				String end = ")";
				PreparedStatement statement = con.prepareStatement(s + c1 + c2 + c3 + end);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createCalendarTable(Connection con, DatabaseMetaData dbm) {
		try {
			ResultSet tables = dbm.getTables(null, null, "calendar", null);
			if (tables.next()) {
			} else {
				String s = "CREATE TABLE calendar (";
				String c1 = "id INT PRIMARY KEY";
				String c2 = ",Date DATE";
				String end = ")";
				PreparedStatement statement = con.prepareStatement(s + c1 + c2 + end);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDaysTable(Connection con, DatabaseMetaData dbm) {
		try {
			ResultSet tables = dbm.getTables(null, null, "days", null);
			if (tables.next()) {
			} else {
				String s = "CREATE TABLE days (";
				String c1 = "id INT";
				String c2 = ",Name VARCHAR(255)";
				String c3 = ",Amount INT";
				String end = ")";
				PreparedStatement statement = con.prepareStatement(s + c1 + c2 + c3 + end);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProductToDatabase(Product p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(host + database + "?autoReconnect=true&useSSL=false", login,
					password);
			String s = String.format("INSERT INTO meal VALUES ('%s', %d, '%s', %f, %f, %f, %f)", p.getName(),
					p.getUnitDivider(), p.getPrimaryUnit(), p.getCaloriesPerUnit(), p.getCarbs(), p.getProteins(),
					p.getFats());
			System.out.println(s);
			PreparedStatement statement = con.prepareStatement(s);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchMealTable(String search) {

		try {
			Connection con = DriverManager.getConnection(host + database + "?autoReconnect=true&useSSL=false", login,
					password);
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Meal WHERE name LIKE '%" + search + "%'");
			ResultSet result = statement.executeQuery();
			EditDatabaseMenu.getListModel().clear();

			while (result.next()) {
				EditDatabaseMenu.getListModel().addElement(result.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product getProduct(String name) {
		
		int unitAmount = 0, calories = 0;
		double carbs = 0, proteins = 0, fats = 0;
		UNITS units = UNITS.gram;

		try {
			Connection con = DriverManager.getConnection(host + database + "?autoReconnect=true&useSSL=false", login,
					password);
			PreparedStatement statement = con.prepareStatement(String.format("SELECT * FROM Meal WHERE name = '%s'", name));
			ResultSet result = statement.executeQuery();
			
			result.next();
			unitAmount = result.getInt(2);
			String u = result.getString(3);
			units = UNITS.valueOf(u);
			calories = result.getInt(4);
			carbs = result.getDouble(5);
			proteins = result.getDouble(6);
			fats = result.getDouble(7);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Product(name, units, unitAmount, calories, carbs, proteins, fats);

	}

}
