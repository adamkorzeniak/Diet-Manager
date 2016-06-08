package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddToDatabaseMenu {
	private boolean advancedOpen = false, advancedHistory = false;
	private JFrame frame;
	private JButton advancedOptionsButton, addButton, closeButton;
	private JTextField mealNameField, mealNameInput, primaryUnitField, calorieField;
	private JFormattedTextField primaryUnitInput, calorieInput;
	private JTextField carbsField, proteinField, fatField;
	private JFormattedTextField carbsAmount, proteinAmount, fatAmount;
	private GridBagConstraints c;
	private JComboBox primaryUnitType;
	private JPanel south;

	public void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Add to database");

		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		mealNameField = new JTextField("Meal Name:");
		mealNameField.setEditable(false);
		setGUIConstraints(c, 0, 0, 3, 1, GridBagConstraints.BOTH, new Insets(10, 10, 4, 10));
		frame.add(mealNameField, c);

		mealNameInput = new JTextField();
		mealNameInput.setEditable(true);
		setGUIConstraints(c, 0, 1, 3, 1, GridBagConstraints.BOTH, new Insets(4, 10, 10, 10));
		frame.add(mealNameInput, c);

		primaryUnitField = new JTextField("Primary Unit:");
		primaryUnitField.setEditable(false);
		setGUIConstraints(c, 0, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitField, c);

		primaryUnitInput = new JFormattedTextField("");
		mealNameInput.setEditable(true);
		//
		setGUIConstraints(c, 1, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitInput, c);

		primaryUnitType = new JComboBox(UNITS.values());
		setGUIConstraints(c, 2, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitType, c);

		calorieField = new JTextField("Calories per Units");
		calorieField.setEditable(false);
		setGUIConstraints(c, 0, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieField, c);

		calorieInput = new JFormattedTextField("");
		calorieInput.setEditable(true);
		//
		setGUIConstraints(c, 2, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieInput, c);

		advancedOptionsButton = new JButton("Advanced Options");
		advancedOptionsButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 5, 1.0, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		south = new JPanel();
		south.setLayout(new GridBagLayout());
		setGUIConstraints(c, 0, 6, 3, 3, 2.0, 2.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		addButton = new JButton("Add");
		addButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		frame.setSize(700, 500);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				advancedHistory = false;
				MainMenu.getFrame().setVisible(true);
			}
		});
	}

	private void showAdvancedOptions() {
		frame.remove(advancedOptionsButton);
		if (!advancedHistory) {
			
			carbsField = new JTextField("Carbohydronates in grams");
			carbsField.setEditable(false);
			
			carbsAmount = new JFormattedTextField("");
			carbsAmount.setEditable(true);
			
			proteinField = new JTextField("Proteins in grams");
			proteinField.setEditable(false);
			
			proteinAmount = new JFormattedTextField("");
			proteinAmount.setEditable(true);
			
			fatField = new JTextField("Fat in grams");
			fatField.setEditable(false);
			
			fatAmount = new JFormattedTextField("");
			fatAmount.setEditable(true); }
		
		setGUIConstraints(c, 0, 5, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsField, c);
		
		//
		setGUIConstraints(c, 2, 5, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsAmount, c);

		setGUIConstraints(c, 0, 6, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinField, c);

		//
		setGUIConstraints(c, 2, 6, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinAmount, c);

		setGUIConstraints(c, 0, 7, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatField, c);

		//
		setGUIConstraints(c, 2, 7, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatAmount, c);
		
		advancedOptionsButton.setText("Hide");
		setGUIConstraints(c, 2, 8, 1.0, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		frame.remove(south);
		setGUIConstraints(c, 0, 9, 3, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		south.removeAll();
		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		frame.setSize(700, 700);
		frame.setVisible(true);
	}
	
	private void hideAdvancedOptions() {
		
		frame.remove(carbsField);
		frame.remove(carbsAmount);
		frame.remove(proteinField);
		frame.remove(proteinAmount);
		frame.remove(fatField);
		frame.remove(fatAmount);
		
		frame.remove(advancedOptionsButton);
		advancedOptionsButton.setText("Advanted Options");
		setGUIConstraints(c, 2, 5, 1.0, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		frame.remove(south);
		setGUIConstraints(c, 0, 6, 3, 3, 2.0, 2.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		frame.setSize(700, 500);
		frame.setVisible(true);
		
		advancedHistory = true;
	}

	public enum UNITS {
		gram, mililitr
	}

	public class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == advancedOptionsButton) {
				if (!advancedOpen) {
					advancedOpen = true;
					showAdvancedOptions();
				} else {
					advancedOpen = false;
					hideAdvancedOptions();
				}
			} else if (e.getSource() == addButton) {
				System.out.println("Added");
			} else if (e.getSource() == closeButton) {
				frame.dispose();
				advancedHistory = false;
				MainMenu.getFrame().setVisible(true);
			}

		}

	}

}
