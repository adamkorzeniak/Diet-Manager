package com.github.adkorzen.dietManager.GUI;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.github.adkorzen.dietManager.DatabaseManagement;

public class Table extends JTable {
	private Table table;
	private Date date;

	static String[] columnNames = { "Meal Name", "Carbs", "Proteins", "Fats", "Calories"};

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
		table.getColumnModel().getColumn(0).setPreferredWidth(300);

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
		data = 	DatabaseManagement.getInstance().getEntryData(date);
	}

}