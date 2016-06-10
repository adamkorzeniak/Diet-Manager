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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddToDatabaseMenu {
	private boolean advancedOpen = false, advancedHistory = false;
	int monitorWidth, monitorHeight;
	private JFrame frame;
	private JButton advancedOptionsButton, addButton, closeButton;
	private JLabel mealNameLabel, primaryUnitLabel, calorieLabel;
	private JTextField mealNameInput;
	private JFormattedTextField primaryUnitInput, calorieInput;
	private JLabel carbsLabel, proteinLabel, fatLabel;
	private JFormattedTextField carbsAmount, proteinAmount, fatAmount;
	private GridBagConstraints c;
	private JComboBox primaryUnitType;
	private JPanel south;

	public void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Add to database");

		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		mealNameLabel = new JLabel("Meal Name:");
		setGUIConstraints(c, 0, 0, 3, 1, GridBagConstraints.BOTH, new Insets(10, 10, 4, 10));
		frame.add(mealNameLabel, c);

		mealNameInput = new JTextField();
		setGUIConstraints(c, 0, 1, 3, 1, GridBagConstraints.BOTH, new Insets(4, 10, 10, 10));
		frame.add(mealNameInput, c);

		primaryUnitLabel = new JLabel("Primary Unit:");
		setGUIConstraints(c, 0, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitLabel, c);

		primaryUnitInput = new JFormattedTextField("100");
		primaryUnitInput.setHorizontalAlignment(SwingConstants.CENTER);
		//
		setGUIConstraints(c, 1, 2, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitInput, c);

		primaryUnitType = new JComboBox(UNITS.values());
		((JLabel)primaryUnitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 2, 2, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitType, c);

		calorieLabel = new JLabel("Calories per Units");
		setGUIConstraints(c, 0, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieLabel, c);

		calorieInput = new JFormattedTextField("");
		calorieInput.setHorizontalAlignment(SwingConstants.CENTER);
		//
		setGUIConstraints(c, 2, 4, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieInput, c);

		advancedOptionsButton = new JButton("Advanced Options");
		advancedOptionsButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 5, 0.3, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		south = new JPanel();
		south.setLayout(new GridBagLayout());
		setGUIConstraints(c, 0, 6, 3, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		addButton = new JButton("Add");
		addButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 500)/2, (monitorHeight - 700)/2);
		frame.setSize(500, 400);
		frame.setResizable(false);
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
			
			carbsLabel = new JLabel("Carbohydronates in grams");
			carbsAmount = new JFormattedTextField("");
			proteinLabel = new JLabel("Proteins in grams");
			proteinAmount = new JFormattedTextField("");
			fatLabel = new JLabel("Fat in grams");
			fatAmount = new JFormattedTextField(""); }
		
		setGUIConstraints(c, 0, 5, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsLabel, c);
		
		//
		setGUIConstraints(c, 2, 5, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsAmount, c);

		setGUIConstraints(c, 0, 6, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinLabel, c);

		//
		setGUIConstraints(c, 2, 6, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinAmount, c);

		setGUIConstraints(c, 0, 7, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatLabel, c);

		//
		setGUIConstraints(c, 2, 7, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatAmount, c);
		
		advancedOptionsButton.setText("Hide");
		setGUIConstraints(c, 2, 8, 0.3, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		frame.remove(south);
		setGUIConstraints(c, 0, 9, 3, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		south.removeAll();
		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		frame.setSize(500, 570);
		frame.setVisible(true);
	}
	
	private void hideAdvancedOptions() {
		
		frame.remove(carbsLabel);
		frame.remove(carbsAmount);
		frame.remove(proteinLabel);
		frame.remove(proteinAmount);
		frame.remove(fatLabel);
		frame.remove(fatAmount);
		
		frame.remove(advancedOptionsButton);
		advancedOptionsButton.setText("Advanted Options");
		setGUIConstraints(c, 2, 5, 0.3, 0.5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 20));
		frame.add(advancedOptionsButton, c);

		frame.remove(south);
		setGUIConstraints(c, 0, 6, 3, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		south.add(addButton, c);

		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		south.add(closeButton, c);

		frame.setSize(500, 400);
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
