package com.github.adkorzen.dietManager.GUI;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Table extends JTable{
	JTable table;

	static String[] columnNames = { "Meal Name", "Calories", "Carbs", "Proteins", "Fats" };

	static String[][] data = { { "P³aki owsiane, 150 g", "350", "30", "10", "5" },
			{ "Mleko, 400 g", "160", "15", "5", "10" } };

	public Table() {
		table = new JTable(data, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER );
		for (int i = 1; i < 5; i++) {
		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
	
	public JTable getTable() {
		return table;
	}
	

	
	

}