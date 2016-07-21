package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

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
import java.util.Date;

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

import com.github.adkorzen.dietManager.DatabaseManagement;
import com.github.adkorzen.dietManager.Helper;
import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.Options;
import com.github.adkorzen.dietManager.GUI.MealView.MealNameComboListener;
import com.github.adkorzen.dietManager.Listener.NumberListener;

public class OptionsMenu {

	private static JFrame frame;
	private static JPanel panel;
	private static GridBagConstraints c;
	private static JFormattedTextField caloriesLimitField, carbsField, proteinsField, fatsField;
	private static JLabel caloriesLimitLabel, carbsLabel, proteinsLabel, fatsLabel;
	private static int monitorWidth, monitorHeight;
	private static JButton saveButton, deleteButton, advancedButton;

	public static void create() {
		String calorieLimit = "";
		if (Options.getCaloriesOptionsSet()) {
			calorieLimit = Integer.toString(Options.getCaloriesLimit());
		}
		String carbs = "";
		String proteins = "";
		String fats = "";
		if (Options.getCompositionOptionsSet()) {
			carbs = Integer.toString(Options.getPercentage(0));
			proteins = Integer.toString(Options.getPercentage(1));
			fats = Integer.toString(Options.getPercentage(2));
		}

		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		caloriesLimitLabel = new JLabel("Daily Limit of Calories:");
		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		panel.add(caloriesLimitLabel, c);

		caloriesLimitField = new JFormattedTextField();
		caloriesLimitField.setText(calorieLimit);
		setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		caloriesLimitField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(caloriesLimitField, e, 999);
			}
		});
		panel.add(caloriesLimitField, c);

		carbsLabel = new JLabel("Percentage of calories from carbs:");
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		panel.add(carbsLabel, c);

		carbsField = new JFormattedTextField();
		carbsField.setText(carbs);
		setGUIConstraints(c, 1, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		carbsField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(carbsField, e, 9);
			}
		});
		panel.add(carbsField, c);

		proteinsLabel = new JLabel("Percentage of calories from proteins:");
		setGUIConstraints(c, 0, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		panel.add(proteinsLabel, c);

		proteinsField = new JFormattedTextField();
		proteinsField.setText(proteins);
		setGUIConstraints(c, 1, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		proteinsField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(proteinsField, e, 9);
			}
		});
		panel.add(proteinsField, c);

		fatsLabel = new JLabel("Percentage of calories from fats:");
		setGUIConstraints(c, 0, 3, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		panel.add(fatsLabel, c);

		fatsField = new JFormattedTextField();
		fatsField.setText(fats);
		setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		fatsField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(fatsField, e, 9);
			}
		});
		panel.add(fatsField, c);

		saveButton = new JButton("Save");
		setGUIConstraints(c, 0, 4, 2, 1, GridBagConstraints.BOTH, new Insets(10, 100, 10, 100));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (caloriesLimitField.getText().length() < 1) {
					JOptionPane.showMessageDialog(frame, "Calories: Invalid Input");
				} else {
					int limit = Integer.parseInt(caloriesLimitField.getText());
					Options.setCaloriesLimit(limit);
					Options.setCaloriesOptionsSet(true);
					if (carbsField.getText().length() > 0 && proteinsField.getText().length() > 0
							&& fatsField.getText().length() > 0) {
						int carbs = Integer.parseInt(carbsField.getText());
						int proteins = Integer.parseInt(proteinsField.getText());
						int fats = Integer.parseInt(fatsField.getText());

						if (carbs + proteins + fats == 100) {
							int[] percentages = { carbs, proteins, fats };
							Options.setPercentage(percentages);
							Options.setCompositionOptionsSet(true);
						} else {
							JOptionPane.showMessageDialog(frame, "Percentage doesn't sum up to 100");
						}
					} else if (carbsField.getText().length() > 0 || proteinsField.getText().length() > 0
							|| fatsField.getText().length() > 0) {
						JOptionPane.showMessageDialog(frame, "You need to provide value for all components or neither");
					}
				}
				Options.storeSettings();
			}
		});
		panel.add(saveButton, c);

		deleteButton = new JButton("Clear Memory");
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Options.setCaloriesLimit(0);
				Options.setCaloriesOptionsSet(false);
				Options.setPercentage(new int[] { 0, 0, 0 });
				Options.setCompositionOptionsSet(false);
				Options.storeSettings();
				caloriesLimitField.setText("");
				carbsField.setText("");
				proteinsField.setText("");
				fatsField.setText("");
			}
		});
		setGUIConstraints(c, 0, 5, GridBagConstraints.BOTH, new Insets(10, 50, 10, 50));
		panel.add(deleteButton, c);

		frame.add(panel);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 400) / 2, (monitorHeight - 300) / 2);
		frame.setSize(400, 300);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getFrame().setEnabled(true);
			}
		});

	}

	public static JFrame getFrame() {
		return frame;
	}
}
