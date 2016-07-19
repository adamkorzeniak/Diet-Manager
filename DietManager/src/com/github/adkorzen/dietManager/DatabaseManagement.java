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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.github.adkorzen.dietManager.GUI.AddMealView;
import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;
import com.github.adkorzen.dietManager.GUI.UnitManagementMenu;

public class DatabaseManagement {

	private static DatabaseManagement instance = null;
	private String host, database, login, password;
	private PreparedStatement statement;
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

	public Connection establishConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(host + database + "?autoReconnect=true&useSSL=false", login,
				password);
		return con;
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
			Connection con = establishConnection();
			DatabaseMetaData dbm = con.getMetaData();

			createMealTable(con, dbm);
			createSecondaryUnitsTable(con, dbm);
			createCalendarTable(con, dbm);
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
				String c1 = "id INT PRIMARY KEY AUTO_INCREMENT";
				String c2 = ",Date DATE";
				String c3 = ",Name VARCHAR(255)";
				String c4 = ",Amount INT";
				String end = ")";
				PreparedStatement statement = con.prepareStatement(s + c1 + c2 + c3 + c4 + end);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProductToDatabase(Product p) {
		try {
			Connection con = establishConnection();
			String s = String.format("INSERT INTO meal VALUES ('%s', %d, '%s', %f, %f, %f, %f)", p.getName(),
					p.getUnitDivider(), p.getPrimaryUnit(), p.getCaloriesPerUnit(), p.getCarbs(), p.getProteins(),
					p.getFats());
			PreparedStatement statement = con.prepareStatement(s);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchMealTable(DefaultListModel<String> listModel, String search, boolean exactly) {

		try {
			Connection con = establishConnection();
			if (exactly)
				statement = con.prepareStatement("SELECT * FROM Meal WHERE name = '" + search + "'");
			else
				statement = con.prepareStatement("SELECT * FROM Meal WHERE name LIKE '%" + search + "%'");
			ResultSet result = statement.executeQuery();
			listModel.clear();

			while (result.next()) {
				listModel.addElement(result.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchMealTable(String search) {

		try {
			Connection con = establishConnection();
			statement = con.prepareStatement("SELECT * FROM Meal WHERE name LIKE '%" + search + "%'");
			ResultSet result = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String>();

			while (result.next()) {
				list.add(result.getString(1));
			}
			String[] newArray = list.toArray(new String[list.size()]);

			JComboBox newCombo = new JComboBox(newArray);
			AddMealView.setMealNameCombobox(newCombo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchSecondaryUnitTable(String search) {

		try {
			Connection con = establishConnection();
			statement = con.prepareStatement("SELECT * FROM Secondary_units WHERE Meal_name = '" + search + "'");
			ResultSet result = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String>();

			PreparedStatement nextStatement = con.prepareStatement("SELECT * FROM Meal WHERE name = '" + search + "'");
			ResultSet nextResult = nextStatement.executeQuery();
			if (nextResult.next()) {
				list.add(nextResult.getString(3));
			}
			while (result.next()) {
				list.add(result.getString(2));
			}
			String[] newArray = list.toArray(new String[list.size()]);

			JComboBox newCombo = new JComboBox(newArray);
			AddMealView.setUnitNameCombobox(newCombo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product getProduct(String name) {
		int unitAmount = 0, calories = 0;
		double carbs = 0, proteins = 0, fats = 0;
		UNITS units = UNITS.gram;

		try {
			Connection con = establishConnection();
			PreparedStatement statement = con
					.prepareStatement(String.format("SELECT * FROM Meal WHERE name = '%s'", name));
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

	public void saveProduct(Product p) {
		try {
			Connection con = establishConnection();
			String s = String.format(
					"UPDATE meal SET Unit_amount = %d, Primary_unit = '%s', Calories = %f, Carbs = %f, Proteins = %f, Fats = %f WHERE name = '%s'",
					p.getUnitDivider(), p.getPrimaryUnit(), p.getCaloriesPerUnit(), p.getCarbs(), p.getProteins(),
					p.getFats(), p.getName());
			PreparedStatement statement = con.prepareStatement(s);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSecondaryUnit(Product p, String string, int amount) {
		try {
			Connection con = establishConnection();
			String s = String.format("SELECT Count(Unit_name) FROM Secondary_Units WHERE unit_name = '%s'", string);
			PreparedStatement statement = con.prepareStatement(s);
			ResultSet result = statement.executeQuery();
			result.next();
			if (result.getInt(1) == 0) {
				String s1 = String.format("INSERT INTO secondary_units VALUES ('%s', '%s', %d)", p.getName(), string,
						amount);
				PreparedStatement statement1 = con.prepareStatement(s1);
				statement1.executeUpdate();
			} else {
				String s1 = String.format("UPDATE Secondary_units SET Multiplier = %d WHERE Unit_name = '%s'", amount,
						string);
				;
				PreparedStatement statement1 = con.prepareStatement(s1);
				statement1.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean contains(Product p) {
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con
					.prepareStatement(String.format("SELECT Count(name) FROM Meal WHERE name = '%s'", p.getName()));
			ResultSet result = statement.executeQuery();
			result.next();
			int count = result.getInt(1);

			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void searchSecondaryUnitTable(Product p) {
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT * FROM Secondary_units WHERE Meal_name = '%s'", p.getName()));
			ResultSet result = statement.executeQuery();
			UnitManagementMenu.getListModel().clear();

			while (result.next()) {
				UnitManagementMenu.getListModel().addElement(result.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteSecondaryUnit(Product p, String string) {
		try {
			Connection con = establishConnection();
			String s = String.format("DELETE FROM secondary_units WHERE Meal_Name = '%s' AND Unit_Name = '%s'",
					p.getName(), string);
			PreparedStatement statement = con.prepareStatement(s);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getSecondaryUnitMultiplier(Product p, String unitName) {
		int value = 0;
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT * FROM Secondary_units WHERE Meal_name = '%s' AND unit_name = '%s'",
							p.getName(), unitName));
			ResultSet result = statement.executeQuery();
			result.next();
			value = result.getInt(3);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void addNewEntry(Date date, String name, int amount) {
		try {
			Connection con = establishConnection();
			String d = Helper.dateToString(date);
			String s = "INSERT INTO calendar (Date, Name, Amount) VALUES ('" + d + "', '" + name + "', '" + amount
					+ "')";
			System.out.println(s);
			PreparedStatement statement = con.prepareStatement(s);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCalories(String mealName) {
		int value = 0;
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT Calories FROM Meal WHERE Name = '%s'", mealName));
			ResultSet result = statement.executeQuery();
			result.next();
			value = result.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public int getCaloriesDivider(String mealName) {
		int value = 0;
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT Unit_amount FROM Meal WHERE Name = '%s'", mealName));
			ResultSet result = statement.executeQuery();
			result.next();
			value = result.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public int getMultiplier(String mealName, String unitName) {
		int value = 0;
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT Multiplier FROM Secondary_units WHERE Meal_name = '%s' AND Unit_Name = '%s'", mealName, unitName));
			ResultSet result = statement.executeQuery();
			result.next();
			value = result.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public boolean isPrimaryUnit(String mealName, String unitName) {
		try {
			Connection con = establishConnection();
			PreparedStatement statement = con.prepareStatement(
					String.format("SELECT Primary_Unit FROM Meal WHERE Name = '%s'", mealName));
			ResultSet result = statement.executeQuery();
			result.next();
			String s = result.getString(1);
			
			if (s.equals(unitName)) return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
