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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.github.adkorzen.dietManager.Database;
import com.github.adkorzen.dietManager.DatabaseManagement;
import com.github.adkorzen.dietManager.Product;
import com.github.adkorzen.dietManager.GUI.AddToDatabaseMenu.UNITS;
import com.github.adkorzen.dietManager.Listener.NumberListener;

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
	private static DefaultListModel<String> listModel;
	private static ListSelectionModel selectionModel;
	private static boolean searching = false;
	private static JFormattedTextField unitAmount;

	public static void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Check database");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		filterInput = new JTextField();
		setGUIConstraints(c, 0, 0, 1.0, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(filterInput, c);

		filterButton = new JButton("Filter");
		filterButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 1, 0, 0.3, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(filterButton, c);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 0, 0.1, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(editButton, c);
		
		listModel = new DefaultListModel<String>();
		mealList = new JList(listModel);
		mealList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mealList.setLayoutOrientation(JList.VERTICAL);
		scroll = new JScrollPane(mealList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setGUIConstraints(c, 0, 1, 3, 1, 1.0 , 3.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
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
					
					unitAmount.setText(Integer.toString(p.getUnitDivider()));
					caloriesPerUnit.setText(Double.toString(p.getCaloriesPerUnit()));
					carbsAmount.setText(Double.toString(p.getCarbs()));
					proteinsAmount.setText(Double.toString(p.getProteins()));
					fatsAmount.setText(Double.toString(p.getFats()));
					
				}
			}
		});
		frame.add(scroll, c);
		
		unitLabel = new JLabel("Unit:");
		setGUIConstraints(c, 0, 2, 0.2, 0.1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		frame.add(unitLabel, c);
		
		unitAmount = new JFormattedTextField();
		unitAmount.setHorizontalAlignment(SwingConstants.CENTER);
		unitAmount.setEditable(false);
		unitAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(unitAmount, e, 9999);
			}
		});
		setGUIConstraints(c, 1, 2, 0.1, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitAmount, c);
		
		unitType = new JComboBox(UNITS.values());
		((JLabel) unitType.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		setGUIConstraints(c, 2, 2, 2, 1, 0.4, 0.1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
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
	private static class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(filterButton)){
				searching = true;
				String search = filterInput.getText();
				DatabaseManagement.getInstance().searchMealTable(listModel, search, false);
				searching = false;
			} else if (event.getSource().equals(editButton)) {
				if  (selectionModel.getAnchorSelectionIndex() >= 0) {
				int index = selectionModel.getAnchorSelectionIndex();
				String name = listModel.get(index);
				frame.dispose();
				Database.editDatabase();
				EditDatabaseMenu.setMealName(name);
				DatabaseManagement.getInstance().searchMealTable(EditDatabaseMenu.getListModel(), name, true);
				EditDatabaseMenu.setSelection();
			}}
		}
		
	}
}
