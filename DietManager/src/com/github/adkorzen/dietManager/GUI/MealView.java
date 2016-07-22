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
import java.lang.invoke.ConstantCallSite;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.adkorzen.dietManager.Database;
import com.github.adkorzen.dietManager.DatabaseManagement;
import com.github.adkorzen.dietManager.Helper;
import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.Listener.NumberListener;

public class MealView {
	
	private static JFrame frame;
	private static JPanel panel;
	private static int monitorWidth, monitorHeight;
	private static GridBagConstraints c;
	private static JTextField searchField;
	private static JButton searchButton;
	private static JComboBox mealNames, unitNames;
	private static JLabel amountLabel;
	private static JFormattedTextField amountField;
	private static JButton addButton;
	private static String[] array = {};
	
	public static void createView() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		searchField = new JTextField();
		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		panel.add(searchField, c);
		
		searchButton = new JButton("search");
		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String search = searchField.getText();
				System.out.println(search);
				DatabaseManagement.getInstance().searchMealTable(search);
			}
			
		});
		setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		panel.add(searchButton, c);
		
		mealNames = new JComboBox(array);
		setGUIConstraints(c, 0, 1, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		mealNames.addActionListener(new MealNameComboListener());
		panel.add(mealNames, c);
		
		amountLabel = new JLabel("Amount:");
		setGUIConstraints(c, 0, 2, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,0,10));
		panel.add(amountLabel, c);
		
		amountField = new JFormattedTextField();
		amountField.setHorizontalAlignment(SwingConstants.CENTER);
		amountField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(amountField, e, 9999);
			}
		});
		setGUIConstraints(c, 0, 3, GridBagConstraints.BOTH, new Insets(0,10,10,10));
		panel.add(amountField, c);
		
		unitNames = new JComboBox();
		setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(0,10,10,10));
		panel.add(unitNames, c);
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!(amountField.getText().length() < 1) && (unitNames.getSelectedItem()!=null)){
				Date date = ManageDate.getDate();
				String name = (String) mealNames.getSelectedItem();
				String unit = (String) unitNames.getSelectedItem();
				String stringAmount = amountField.getText();
				int amount = Helper.calculateAmount(name, unit, stringAmount);
				DatabaseManagement.getInstance().addNewEntry(date, name, amount);
				frame.dispose();
				DayMenu.updateTable();
				DayMenu.getFrame().setVisible(true);
			}}
		});
		setGUIConstraints(c, 0, 4, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		panel.add(addButton, c);
		
		
		frame.add(panel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 300) / 2, (monitorHeight - 300) / 2);
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				DayMenu.getFrame().setVisible(true);
			}
		});
	}
		public static void createView(String oldName, String oldAmount) {
			frame = new JFrame();
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			c = new GridBagConstraints();
			
			searchField = new JTextField();
			setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(10,10,10,10));
			panel.add(searchField, c);
			
			searchButton = new JButton("search");
			searchButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					String search = searchField.getText();
					DatabaseManagement.getInstance().searchMealTable(search);
				}
				
			});
			setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(10,10,10,10));
			panel.add(searchButton, c);
			
			mealNames = new JComboBox(array);
			setGUIConstraints(c, 0, 1, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,10,10));
			mealNames.addActionListener(new MealNameComboListener());
			mealNames.setSelectedItem(oldName);
			panel.add(mealNames, c);

			
			amountLabel = new JLabel("Amount:");
			setGUIConstraints(c, 0, 2, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,0,10));
			panel.add(amountLabel, c);
			
			amountField = new JFormattedTextField();
			amountField.setHorizontalAlignment(SwingConstants.CENTER);
			amountField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					NumberListener.integerListener(amountField, e, 9999);
				}
			});
			setGUIConstraints(c, 0, 3, GridBagConstraints.BOTH, new Insets(0,10,10,10));
			amountField.setText(oldAmount);
			panel.add(amountField, c);
			
			unitNames = new JComboBox();
			setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(0,10,10,10));
			panel.add(unitNames, c);
			
			addButton = new JButton("Save");
			addButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (!(amountField.getText().length() < 1) && (unitNames.getSelectedItem()!=null)){
					Date date = ManageDate.getDate();
					String name = (String) mealNames.getSelectedItem();
					String unit = (String) unitNames.getSelectedItem();
					String stringAmount = amountField.getText();
					int amount = Helper.calculateAmount(name, unit, stringAmount);
					DatabaseManagement.getInstance().updateEntry(date, oldName, name, oldAmount, amount);
					frame.dispose();
					DayMenu.updateTable();
					DayMenu.getFrame().setVisible(true);
				}}
			});
			setGUIConstraints(c, 0, 4, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,10,10));
			panel.add(addButton, c);
			
			
			frame.add(panel);
			DatabaseManagement.getInstance().searchMealTable(oldName);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			monitorWidth = (int) screenSize.getWidth();
			monitorHeight = (int) screenSize.getHeight();
			frame.setLocation((monitorWidth - 300) / 2, (monitorHeight - 300) / 2);
			frame.setSize(300, 300);
			frame.setResizable(false);
			frame.setVisible(true);

			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					DayMenu.getFrame().setVisible(true);
				}
			});
	}
	public static void setMealNameCombobox(JComboBox combo) {
		panel.remove(mealNames);
		mealNames = combo;
		mealNames.addActionListener(new MealNameComboListener());
		setGUIConstraints(c, 0, 1, 2, 1, GridBagConstraints.BOTH, new Insets(10,10,10,10));
		panel.add(mealNames, c);
		System.out.println((String)mealNames.getSelectedItem());
		DatabaseManagement.getInstance().searchSecondaryUnitTable((String)mealNames.getSelectedItem());
		SwingUtilities.updateComponentTreeUI(frame);
		
	}
	public static void setUnitNameCombobox(JComboBox combo) {
		panel.remove(unitNames);
		unitNames = combo;
		setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(0,10,10,10));
		panel.add(unitNames, c);
	}
	
	public static class MealNameComboListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
				int index = mealNames.getSelectedIndex();
				String name = (String) mealNames.getItemAt(index);
				System.out.println(name);
				DatabaseManagement.getInstance().searchSecondaryUnitTable(name);
				
		}
		
	}
}
