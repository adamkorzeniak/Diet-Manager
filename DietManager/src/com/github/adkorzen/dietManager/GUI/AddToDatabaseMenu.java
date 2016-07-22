package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.adkorzen.dietManager.Database;
import com.github.adkorzen.dietManager.Listener.NumberListener;

public class AddToDatabaseMenu {
	private static boolean advancedOpen = false, advancedHistory = false;
	private static int monitorWidth, monitorHeight;
	private static JFrame frame;
	private static JButton advancedOptionsButton, addButton, closeButton;
	private static JLabel mealNameLabel, primaryUnitLabel, calorieLabel;
	private static JTextField mealNameInput;
	private static JFormattedTextField primaryUnitInput, calorieInput;
	private static JLabel carbsLabel, proteinLabel, fatLabel;
	private static JFormattedTextField carbsAmount, proteinsAmount, fatAmount;
	private static GridBagConstraints c;
	private static JComboBox primaryUnitType;
	private static JPanel south;

	public static void createAndShowGUI() {
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

		primaryUnitInput = new JFormattedTextField();
		primaryUnitInput.setText("100");
		primaryUnitInput.setHorizontalAlignment(SwingConstants.CENTER);
		primaryUnitInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(primaryUnitInput, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 2, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitInput, c);

		primaryUnitType = new JComboBox(UNITS.values());
		((JLabel) primaryUnitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 2, 2, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitType, c);

		calorieLabel = new JLabel("Calories per Units");
		setGUIConstraints(c, 0, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieLabel, c);

		calorieInput = new JFormattedTextField();
		calorieInput.setHorizontalAlignment(SwingConstants.CENTER);
		calorieInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(calorieInput, e, 99999999);
			}
		});
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
		frame.setLocation((monitorWidth - 500) / 2, (monitorHeight - 700) / 2);
		frame.setSize(500, 400);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				advancedHistory = false;
				advancedOpen = false;
				MainMenu.getFrame().setVisible(true);
			}
		});
	}

	private static void showAdvancedOptions() {
		frame.remove(advancedOptionsButton);
		if (!advancedHistory) {

			carbsLabel = new JLabel("Carbohydronates in grams");
			carbsAmount = new JFormattedTextField();
			carbsAmount.setHorizontalAlignment(SwingConstants.CENTER);
			proteinLabel = new JLabel("Proteins in grams");
			proteinsAmount = new JFormattedTextField();
			proteinsAmount.setHorizontalAlignment(SwingConstants.CENTER);
			fatLabel = new JLabel("Fat in grams");
			fatAmount = new JFormattedTextField();
			fatAmount.setHorizontalAlignment(SwingConstants.CENTER);
			carbsAmount.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					NumberListener.doubleListener(carbsAmount, e, 9999);
				}
			});
			proteinsAmount.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					NumberListener.doubleListener(proteinsAmount, e, 9999);
				}
			});
			fatAmount.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					NumberListener.doubleListener(fatAmount, e, 9999);
				}
			});
		}

		setGUIConstraints(c, 0, 5, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsLabel, c);

		//
		setGUIConstraints(c, 2, 5, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(30, 10, 10, 10));
		frame.add(carbsAmount, c);

		setGUIConstraints(c, 0, 6, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinLabel, c);

		//
		setGUIConstraints(c, 2, 6, 0.3, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinsAmount, c);

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

	private static void hideAdvancedOptions() {

		frame.remove(carbsLabel);
		frame.remove(carbsAmount);
		frame.remove(proteinLabel);
		frame.remove(proteinsAmount);
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
		gram, 
		mililitr;
		
	}

	private static class ButtonListener implements ActionListener {

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
				if (!emptyInput()) {
					Database.addProductToDatabase();
				}
			} else if (e.getSource() == closeButton) {
				frame.dispose();
				advancedHistory = false;
				advancedOpen= false;
				MainMenu.getFrame().setVisible(true);
			}
		}
	}

	public boolean isAdvancedOpen() {
		return advancedOpen;
	}

	public static String getName() {
		return mealNameInput.getText();
	}

	public static UNITS getUnit() {
		return (UNITS) primaryUnitType.getSelectedItem();
	}

	public static int getUnitDivider() {
		return Integer.parseInt(primaryUnitInput.getText());
	}

	public static int getCalories() {
		return Integer.parseInt(calorieInput.getText());
	}

	public static double getCarbs() {
		if (carbsAmount == null || carbsAmount.getText().isEmpty()) {
			return 0.0;
		}
		return Double.parseDouble(carbsAmount.getText());
	}

	public static double getProteins() {
		if (proteinsAmount == null || proteinsAmount.getText().isEmpty()) {
			return 0.0;
		}
		return Double.parseDouble(proteinsAmount.getText());
	}

	public static double getFats() {
		if (fatAmount == null || fatAmount.getText().isEmpty()) {
			return 0.0;
		}
		return Double.parseDouble(fatAmount.getText());
	}

	public static boolean emptyInput() {
		String message = "";
		if (mealNameInput.getText().isEmpty()) {
			message += "<html>Insert meal name.<br>";
		}
		if (Integer.parseInt(primaryUnitInput.getText()) == 0) {
			message += "<html>Amount of primary units must be a positive number.<br>";
		}
		if (calorieInput.getText().isEmpty()) {
			message += "Insert amount of calories.";
		}
		if (message.length() > 0) {
			JLabel info = new JLabel(message);
			JOptionPane.showMessageDialog(frame, info);
			return true;
		}
		return false;
	}
}
