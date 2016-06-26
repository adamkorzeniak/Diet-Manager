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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.github.adkorzen.dietManager.DatabaseManagement;
import com.github.adkorzen.dietManager.Product;
import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;
import com.github.adkorzen.dietManager.Listener.NumberListener;

public class EditDatabaseMenu {
	private static JFrame frame;
	private static GridBagConstraints c;
	private static JLabel mealNameLabel, unitLabel;
	private static JLabel calorieLabel, carbsLabel, proteinsLabel, fatsLabel;
	private static int monitorHeight, monitorWidth;
	private static JTextField mealNameInput;
	private static JFormattedTextField unitAmount, caloriesPerUnit, carbsAmount, proteinsAmount, fatsAmount;
	private static JList mealList;
	private static JButton searchButton, newUnitButton;
	private static JScrollPane scroll;
	private static JComboBox unitType;
	private static JPanel south;
	private static JButton saveButton;
	private static JButton closeButton;
	private static DefaultListModel<String> listModel;
	private static ListSelectionModel selectionModel;
	private static boolean searching;

	public static void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Edit database");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		mealNameLabel = new JLabel("Meal Name:");
		setGUIConstraints(c, 0, 0, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(mealNameLabel, c);

		mealNameInput = new JTextField();
		setGUIConstraints(c, 1, 0, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(mealNameInput, c);

		searchButton = new JButton("Search");
		setGUIConstraints(c, 3, 0, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(searchButton, c);
		searchButton.addActionListener(new ButtonListener());

		listModel = new DefaultListModel<String>();
		mealList = new JList(listModel);
		mealList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mealList.setLayoutOrientation(JList.VERTICAL);
		scroll = new JScrollPane(mealList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setGUIConstraints(c, 1, 1, 2, 1, 2.0, 10.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		selectionModel = mealList.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!searching) {
					int index = selectionModel.getAnchorSelectionIndex();
					String meal = listModel.get(index);
					Product p = DatabaseManagement.getInstance().getProduct(meal);

					if (p.getPrimaryUnit().equals(UNITS.gram)) {
						unitType.setSelectedIndex(0);
					} else {
						unitType.setSelectedIndex(1);
					}
					unitAmount.setValue(p.getUnitDivider());
					unitAmount.setEditable(true);
					caloriesPerUnit.setValue(p.getCaloriesPerUnit());
					caloriesPerUnit.setEditable(true);
					carbsAmount.setValue(p.getCarbs());
					carbsAmount.setEditable(true);
					proteinsAmount.setValue(p.getProteins());
					proteinsAmount.setEditable(true);
					fatsAmount.setValue(p.getFats());
					fatsAmount.setEditable(true);

				}
			}
		});
		frame.add(scroll, c);

		unitLabel = new JLabel("Unit:");
		setGUIConstraints(c, 0, 2, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitLabel, c);

		unitAmount = new JFormattedTextField();
		unitAmount.setHorizontalAlignment(SwingConstants.CENTER);
		unitAmount.setEditable(false);
		unitAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(unitAmount, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 2, 0.5, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitAmount, c);

		unitType = new JComboBox(UNITS.values());
		((JLabel) unitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 2, 2, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitType, c);

		newUnitButton = new JButton("New Unit");
		setGUIConstraints(c, 3, 2, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(newUnitButton, c);

		calorieLabel = new JLabel("Calories per Unit:");
		setGUIConstraints(c, 0, 3, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(calorieLabel, c);

		caloriesPerUnit = new JFormattedTextField();
		caloriesPerUnit.setHorizontalAlignment(SwingConstants.CENTER);
		caloriesPerUnit.setEditable(false);
		caloriesPerUnit.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(caloriesPerUnit, e, 99999999);
			}
		});
		setGUIConstraints(c, 1, 3, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(caloriesPerUnit, c);

		carbsLabel = new JLabel("Carbs per gram:");
		setGUIConstraints(c, 0, 4, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(carbsLabel, c);

		carbsAmount = new JFormattedTextField();
		carbsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		carbsAmount.setEditable(false);
		carbsAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.doubleListener(carbsAmount, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 4, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(carbsAmount, c);

		proteinsLabel = new JLabel("Proteins per gram:");
		setGUIConstraints(c, 0, 5, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinsLabel, c);

		proteinsAmount = new JFormattedTextField();
		proteinsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		proteinsAmount.setEditable(false);
		proteinsAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.doubleListener(proteinsAmount, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 5, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(proteinsAmount, c);

		fatsLabel = new JLabel("Fats per gram:");
		setGUIConstraints(c, 0, 6, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatsLabel, c);

		fatsAmount = new JFormattedTextField();
		fatsAmount.setHorizontalAlignment(SwingConstants.CENTER);
		fatsAmount.setEditable(false);
		fatsAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.doubleListener(fatsAmount, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 6, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(fatsAmount, c);

		south = new JPanel();
		south.setLayout(new GridBagLayout());
		setGUIConstraints(c, 0, 7, 4, 3, 2.0, 3.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 50, 0, 50));
		saveButton.addActionListener(new ButtonListener());
		south.add(saveButton, c);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(30, 200, 10, 200));
		closeButton.addActionListener(new ButtonListener());
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
			if (e.getSource().equals(searchButton)) {
				searching = true;
				String search = mealNameInput.getText();
				DatabaseManagement.getInstance().searchMealTable(search);
				searching = false;
			} else if (e.getSource().equals(saveButton)) {
				if (!selectionModel.isSelectionEmpty()) {
					int index = selectionModel.getAnchorSelectionIndex();
					String name = listModel.get(index);
					System.out.println(caloriesPerUnit.getValue());
					Product p = new Product(name, (UNITS) unitType.getSelectedItem(), (int) unitAmount.getValue(),
							(double) caloriesPerUnit.getValue(), (double) carbsAmount.getValue(),
							(double) proteinsAmount.getValue(), (double) fatsAmount.getValue());
					DatabaseManagement.getInstance().saveProduct(p);
				}
			} else if (e.getSource().equals(closeButton)) {
				frame.dispose();
				MainMenu.getFrame().setVisible(true);
			}
		}
	}

	public static DefaultListModel<String> getListModel() {
		return listModel;
	}
}
