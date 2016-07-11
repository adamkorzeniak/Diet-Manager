package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.security.AllPermission;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class UnitManagementMenu {

	private static String unitName = "";
	private static JFrame frame;
	private static int monitorWidth, monitorHeight;
	private static JLabel mealNameLabel, mealLabel, primaryUnitLabel, unitLabel, unitLabel2, relationLabel,
			relationUnitLabel;
	private static JFormattedTextField unitTextField;
	private static JButton newButton, deleteButton, saveButton, closeButton;
	private static GridBagConstraints c;
	private static DefaultListModel<String> listModel;
	private static JList mealList;
	private static ListSelectionModel selectionModel;
	private static JScrollPane scroll;
	private static JPanel south;
	private static Product p;
	private static boolean refreshing;

	public static void createAndShowGUI(String product) {

		p = DatabaseManagement.getInstance().getProduct(product);

		frame = new JFrame();
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		mealNameLabel = new JLabel("Meal Name:");
		setGUIConstraints(c, 0, 0, 0.1, 1.0, GridBagConstraints.BOTH, new Insets(4, 10, 4, 10));
		frame.add(mealNameLabel, c);

		mealLabel = new JLabel(product);
		setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(4, 10, 4, 10));
		frame.add(mealLabel, c);

		primaryUnitLabel = new JLabel("Primary Unit:");
		setGUIConstraints(c, 0, 1, 0.1, 1.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(primaryUnitLabel, c);

		unitLabel = new JLabel((p.getPrimaryUnit().toString()));
		setGUIConstraints(c, 1, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(unitLabel, c);

		listModel = new DefaultListModel<String>();
		mealList = new JList(listModel);
		mealList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mealList.setLayoutOrientation(JList.VERTICAL);
		scroll = new JScrollPane(mealList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setGUIConstraints(c, 1, 2, 1, 2, 3.0, 10.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		selectionModel = mealList.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!refreshing){
				int index = selectionModel.getAnchorSelectionIndex();
				unitName = listModel.get(index);
				String text = String.format("1 %s =", unitName);
				relationUnitLabel.setText(text);
				unitTextField.setEditable(true);}
			}
		});
		frame.add(scroll, c);

		newButton = new JButton("New");
		newButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 2, 0.1, 0.5, GridBagConstraints.BOTH, new Insets(40, 10, 40, 10));
		frame.add(newButton, c);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 3, 0.1, 0.5, GridBagConstraints.BOTH, new Insets(40, 10, 40, 10));
		frame.add(deleteButton, c);

		relationLabel = new JLabel("Relation:");
		setGUIConstraints(c, 0, 4, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(relationLabel, c);

		relationUnitLabel = new JLabel();
		setGUIConstraints(c, 0, 5, GridBagConstraints.BOTH, new Insets(4, 10, 4, 10));
		frame.add(relationUnitLabel, c);

		unitTextField = new JFormattedTextField();
		unitTextField.setHorizontalAlignment(SwingConstants.CENTER);
		unitTextField.setEditable(false);
		unitTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				NumberListener.integerListener(unitTextField, e, 99999);
			}
		});
		setGUIConstraints(c, 1, 5, GridBagConstraints.BOTH, new Insets(4, 10, 4, 10));
		frame.add(unitTextField, c);

		unitLabel2 = new JLabel((p.getPrimaryUnit().toString()));
		setGUIConstraints(c, 2, 5, 0.2, 1.0, GridBagConstraints.BOTH, new Insets(4, 10, 4, 10));
		frame.add(unitLabel2, c);

		south = new JPanel();
		south.setLayout(new GridBagLayout());
		setGUIConstraints(c, 0, 6, 3, 2, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(30, 0, 10, 0));
		frame.add(south, c);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, 1.0, 2.0, GridBagConstraints.BOTH, new Insets(0, 20, 0, 20));
		south.add(saveButton, c);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(10, 100, 10, 100));
		south.add(closeButton, c);
		
		DatabaseManagement.getInstance().searchSecondaryUnitTable(p);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 400) / 2, (monitorHeight - 500) / 2);
		frame.setSize(400, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				EditDatabaseMenu.getFrame().setEnabled(true);
			}
		});
	}

	private static class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			refreshing = true;
			if (e.getSource().equals(newButton)) {
				DatabaseManagement.getInstance().searchSecondaryUnitTable(p);
				JComponent[] message = new JComponent[2];
				message[0] = new JLabel("Name of unit:");
				JTextField name = new JTextField();
				message[1] = name;
				name.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if (name.getText().length() > 0) {
							unitName = name.getText();
							getListModel().addElement(unitName);
							String text = String.format("1 %s =", unitName);
							relationUnitLabel.setText(text);
							unitTextField.setEditable(true);
							mealList.setSelectedIndex(mealList.getComponentCount());
						} else {
							unitName = "";
							relationUnitLabel.setText(unitName);
							unitTextField.setEditable(false);
						}
					}
				});
				JOptionPane.showMessageDialog(frame, message, "Create custom unit", JOptionPane.INFORMATION_MESSAGE);
			} else if (e.getSource().equals(deleteButton)) {
				int index = selectionModel.getAnchorSelectionIndex();
				unitName = listModel.get(index);
				relationUnitLabel.setText("");
				unitTextField.setEditable(false);
				DatabaseManagement.getInstance().deleteSecondaryUnit(p, unitName);
				DatabaseManagement.getInstance().searchSecondaryUnitTable(p);

			} else if (e.getSource().equals(closeButton)) {
				EditDatabaseMenu.getFrame().setEnabled(true);
				frame.dispose();
			} else if (e.getSource().equals(saveButton)) {
				if (unitTextField.getText().length() > 0 && unitName.length() > 0) {
					int amount = Integer.parseInt(unitTextField.getText());
					DatabaseManagement.getInstance().addSecondaryUnit(p, unitName, amount);
					DatabaseManagement.getInstance().searchSecondaryUnitTable(p);
				}
			}
			refreshing = false;
		}

	}
	public static DefaultListModel<String> getListModel() {
		return listModel;
	}
}
