package com.github.adkorzen.dietManager.GUI;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.github.adkorzen.dietManager.DatabaseManagement;

public class Table extends JTable {
	private Table table;
	private Date date;

	static String[] columnNames = { "Meal Name", "Calories", "Carbs", "Proteins", "Fats" };

//	static String[][] data = { { "P³aki owsiane, 150 g", "350", "30", "10", "5" },
//			{ "Mleko, 400 g", "160", "15", "5", "10" } };
	
	
	static String[][] data = new String [0][0];

	public Table(Date d) {
		date = d;
		createTable();

	}

	public Table(String[][] date, String[] columnNames) {
		super(date, columnNames);
	}

	public void createTable() {
		setData();
		table = new Table(data, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 1; i < 5; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	public Date getDate() {
		return date;
	}

	public Table getTable() {
		return table;
	}
	
	public void setData() {
		data = new String [1][5];
//		DatabaseManagement.getInstance()s.getEntryData(date);
		data[0][0] = "seven";
		data[0][1] = "100";
	}

}