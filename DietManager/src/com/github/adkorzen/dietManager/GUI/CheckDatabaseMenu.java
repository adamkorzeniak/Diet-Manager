package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;

public class CheckDatabaseMenu {
	private static JFrame frame;
	private static GridBagConstraints c;
	private static JTextField filterInput;
	private static JButton filterButton, editButton;
	private static int monitorWidth, monitorHeight;
	private static JComboBox unitType;
	private static JList mealList;
	private static JScrollPane scroll;
	private static JLabel unitLabel;
	private static JLabel calorieLabel, carbsLabel, proteinsLabel, fatsLabel;
	private static JTextField caloriesPerUnit, carbsAmount, proteinsAmount, fatsAmount;

	public static void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Check database");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		filterInput = new JTextField();
		setGUIConstraints(c, 0, 0, 1.0, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(filterInput, c);

		filterButton = new JButton("Filter");
		setGUIConstraints(c, 1, 0, 0.3, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(filterButton, c);
		
		editButton = new JButton("Edit");
		setGUIConstraints(c, 2, 0, 0.1, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(editButton, c);
		

		
		mealList = new JList();
		mealList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mealList.setLayoutOrientation(JList.VERTICAL);
//		mealList.setListData(list);
		scroll = new JScrollPane(mealList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setGUIConstraints(c, 0, 1, 3, 1, 1.0 , 3.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(scroll, c);
		
		unitLabel = new JLabel("Unit:");
		setGUIConstraints(c, 0, 2, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(unitLabel, c);
		
		unitType = new JComboBox(UNITS.values());
		((JLabel) unitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 2, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 10));
		frame.add(unitType, c);
		
		
		calorieLabel = new JLabel("Calories per Unit:");
		setGUIConstraints(c, 0, 3, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(calorieLabel, c);
		
		caloriesPerUnit = new JTextField();
		caloriesPerUnit.setEditable(false);
		caloriesPerUnit.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 3, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 10));
		frame.add(caloriesPerUnit, c);
		
		carbsLabel = new JLabel("Carbs per gram:");
		setGUIConstraints(c, 0, 4, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(carbsLabel, c);
		
		carbsAmount = new JTextField();
		carbsAmount.setEditable(false);
		carbsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 4, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 10));
		frame.add(carbsAmount, c);
		
		proteinsLabel = new JLabel("Proteins per gram:");
		setGUIConstraints(c, 0, 5, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(proteinsLabel, c);
		
		proteinsAmount = new JTextField();
		proteinsAmount.setEditable(false);
		proteinsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 5, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 10));
		frame.add(proteinsAmount, c);
		
		fatsLabel = new JLabel("Fats per gram:");
		setGUIConstraints(c, 0, 6, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(fatsLabel, c);
		
		fatsAmount = new JTextField();
		fatsAmount.setEditable(false);
		fatsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 6, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 10, 10));
		frame.add(fatsAmount, c);
		
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 500) / 2, (monitorHeight - 750) / 2);
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getFrame().setVisible(true);
			}
		});
	}
}
