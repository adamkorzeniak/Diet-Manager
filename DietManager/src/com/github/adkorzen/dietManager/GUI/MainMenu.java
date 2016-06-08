package com.github.adkorzen.dietManager.GUI;


import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.github.adkorzen.dietManager.Database;
import com.github.adkorzen.dietManager.DateSetting;

public class MainMenu {
	private static JFrame frame;
	private JButton addToDatabaseButton, editDatabaseButton, checkDatabaseButton;
	private JButton openCalendarButton, setDateTodayButton, setDateYesterdayButton;
	private JButton confirmButton;
	private JSpinner spinner;
	private SpinnerDateModel model;
	private static JSpinner.DateEditor dateEditor;
	private GridBagConstraints c;
	private Database database;
	private DateSetting dateSetting;

	public void createAndShowGUI() {
		
		database = new Database();
		dateSetting = new DateSetting();

		frame = new JFrame("Diet Manager");
		frame.getContentPane().setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		addToDatabaseButton = new JButton("Add to database");
		addToDatabaseButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(addToDatabaseButton, c);

		editDatabaseButton = new JButton("Edit database");
		editDatabaseButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 1, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(editDatabaseButton, c);

		checkDatabaseButton = new JButton("Check database");
		checkDatabaseButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(checkDatabaseButton, c);

		openCalendarButton = new JButton("Open calendar");
		openCalendarButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(openCalendarButton, c);

		setDateTodayButton = new JButton("Today");
		setDateTodayButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 1, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(setDateTodayButton, c);

		setDateYesterdayButton = new JButton("Yesterday");
		setDateYesterdayButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 2, 1, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(setDateYesterdayButton, c);


		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		calendar.set(2000, 00, 01);
		Date earliestDate = calendar.getTime();
		calendar.set(2040, 00, 00);
		Date latestDate = calendar.getTime();
		
		model = new SpinnerDateModel(initDate,
                earliestDate,
                latestDate,
                Calendar.DAY_OF_MONTH);
		
		spinner = new JSpinner(model);
		dateEditor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
		spinner.setEditor(dateEditor);
		dateEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
				
		setGUIConstraints(c, 0, 2, 3, 1, 1.0, 0.3, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(spinner, c);

		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ButtonListener());
		setGUIConstraints(c, 1, 3, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0));
		frame.add(confirmButton, c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
		
		
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			JButton action = (JButton) evt.getSource();
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
	
	public static JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu().createAndShowGUI();
			}
		});
	}
	
	public static JSpinner.DateEditor getSpinerEditor() {
		return dateEditor;
	}
}
