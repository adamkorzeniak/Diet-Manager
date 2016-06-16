package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;

public class EditDatabaseMenu {
	private static JFrame frame;
	private static GridBagConstraints c;
	private static JLabel mealNameLabel, unitLabel;
	private static JLabel calorieLabel, carbsLabel, proteinsLabel, fatsLabel; 
	private static int monitorHeight, monitorWidth;
	private static JTextField mealNameInput;
	private static JTextField caloriesPerUnit, carbsAmount, proteinsAmount, fatsAmount;
	private static JTextArea mealList;
	private static JButton searchButton, newUnitButton;
	private static JScrollPane scroll;
	private static JComboBox unitType;
	private static JPanel south;
	private static JButton saveButton;
	private static JButton closeButton;

	public static void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Add to database");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		mealNameLabel = new JLabel("Meal Name:");
		setGUIConstraints(c, 0, 0, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(mealNameLabel, c);
		
		mealNameInput = new JTextField();
		setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(mealNameInput, c);
		
		searchButton = new JButton("Search");
		setGUIConstraints(c, 2, 0, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(searchButton, c);
		
		mealList = new JTextArea();
		mealList.setEditable(false);
		scroll = new JScrollPane(mealList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setGUIConstraints(c, 1, 1, 2.0 , 10.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(scroll, c);
		
		unitLabel = new JLabel("Unit:");
		setGUIConstraints(c, 0, 2, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitLabel, c);
		
		unitType = new JComboBox(UNITS.values());
		((JLabel) unitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitType, c);
		
		newUnitButton = new JButton("New Unit");
		setGUIConstraints(c, 2, 2, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(newUnitButton, c);
		
		calorieLabel = new JLabel("Calories per Unit:");
		setGUIConstraints(c, 0, 3, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieLabel, c);
		
		caloriesPerUnit = new JTextField();
		caloriesPerUnit.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(caloriesPerUnit, c);
		
		carbsLabel = new JLabel("Carbs per gram:");
		setGUIConstraints(c, 0, 4, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(carbsLabel, c);
		
		carbsAmount = new JTextField();
		carbsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(carbsAmount, c);
		
		proteinsLabel = new JLabel("Proteins per gram:");
		setGUIConstraints(c, 0, 5, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinsLabel, c);
		
		proteinsAmount = new JTextField();
		proteinsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 5, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinsAmount, c);
		
		fatsLabel = new JLabel("Fats per gram:");
		setGUIConstraints(c, 0, 6, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatsLabel, c);
		
		fatsAmount = new JTextField();
		fatsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 1, 6, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatsAmount, c);
		
		south = new JPanel();
		south.setLayout(new GridBagLayout());
		setGUIConstraints(c, 0, 7, 3, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(saveButton, c);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 500) / 2, (monitorHeight - 700) / 2);
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getFrame().setVisible(true);
			}
		});
	}
	
	private static class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		}
		
	}
}
