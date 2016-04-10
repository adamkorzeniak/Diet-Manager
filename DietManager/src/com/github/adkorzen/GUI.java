package com.github.adkorzen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	JFrame frame;
	JButton addToDatabaseButton, editDatabaseButton, checkDatabaseButton;
	JButton openCalendarButton, setDateTodayButton, setDateYesterdayButton;
	JTextField DateTextField;
	JButton confirmButton;
	GridBagConstraints c;
	Database database;
	DateSetting dateSetting; 

	public void createAndShowGUI() {
		
		database = new Database();
		dateSetting = new DateSetting();
		
		frame = new JFrame("Diet Manager");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		addToDatabaseButton = new JButton("Add to database");
		addToDatabaseButton.addActionListener(new ButtonListener());
		System.out.println(addToDatabaseButton);
		setConstraints(0, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(addToDatabaseButton, c);

		editDatabaseButton = new JButton("Edit database");
		editDatabaseButton.addActionListener(new ButtonListener());
		setConstraints(1, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(editDatabaseButton, c);

		checkDatabaseButton = new JButton("Check database");
		checkDatabaseButton.addActionListener(new ButtonListener());
		setConstraints(2, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(checkDatabaseButton, c);

		openCalendarButton = new JButton("Open calendar");
		openCalendarButton.addActionListener(new ButtonListener());
		setConstraints(0, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(openCalendarButton, c);

		setDateTodayButton = new JButton("Today");
		setDateTodayButton.addActionListener(new ButtonListener());
		setConstraints(1, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(setDateTodayButton, c);

		setDateYesterdayButton = new JButton("Yesterday");
		setDateYesterdayButton.addActionListener(new ButtonListener());
		setConstraints(2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(setDateYesterdayButton, c);

		DateTextField = new JTextField("Confirm");
		// addToDatabaseButton.addActionListener(new ButtonListener());
		setConstraints(0, 2, 3, 1, 1.0, 0.1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(DateTextField, c);

		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ButtonListener());
		setConstraints(1, 3, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0));
		frame.add(confirmButton, c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
	
	public void setConstraints(int posX, int posY, int width, int height, double weightx, double weighty, int fill,
			Insets inset) {
		c.gridx = posX;
		c.gridy = posY;
		c.gridwidth = width;
		c.gridheight = height;
		c.weightx = weightx;
		c.weighty = weighty;
		c.fill = fill;
		c.insets = inset;
	}

	public void setConstraints(int posX, int posY, double weightx, double weighty, int fill, Insets inset) {
		setConstraints(posX, posY, 1, 1, weightx, weighty, fill, inset);
	}

	public void setConstraints(int posX, int posY, int width, int height, int fill, Insets inset) {
		setConstraints(posX, posY, width, height, 1, 1, fill, inset);
	}

	public void setConstraints(int posX, int posY, int fill, Insets inset) {
		setConstraints(posX, posY, 1, 1, 1, 1, fill, inset);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton action = (JButton) e.getSource();
			if (action == addToDatabaseButton) {
				database.addToDatabase();
			} else if (action == editDatabaseButton) {
				database.editDatabase();
			} else if (action == checkDatabaseButton) {
				database.checkDatabase();
			} else if (action == openCalendarButton) {
				dateSetting.openCalendar();
			} else if (action == setDateTodayButton) {
				dateSetting.setDateToday();
			} else if (action == setDateYesterdayButton) {
				dateSetting.setDateYesterday();
			} else if (action == confirmButton) {
				database.proceed();
			}
		}
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI().createAndShowGUI();
			}
		});
	}
}
