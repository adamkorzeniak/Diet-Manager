package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
import com.github.adkorzen.dietManager.ManageDate;

public class MainMenu {
	private static JFrame frame;
	int monitorWidth, monitorHeight;
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

		Date date = ManageDate.currentDate;
		Date earliestDate = ManageDate.earliestDate;
		Date latestDate = ManageDate.latestDate;

		System.out.println(earliestDate);

		model = new SpinnerDateModel(date, earliestDate, latestDate, 1);

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

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 500) / 2, (monitorHeight - 300) / 2);
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			JButton action = (JButton) evt.getSource();
			if (action == addToDatabaseButton) {
				Database.addToDatabase();
			} else if (action == editDatabaseButton) {
				Database.editDatabase();
			} else if (action == checkDatabaseButton) {
				Database.checkDatabase();
			} else if (action == openCalendarButton) {
				DateSetting.openCalendar();
			} else if (action == setDateTodayButton) {
				DateSetting.setDateToday();
			} else if (action == setDateYesterdayButton) {
				DateSetting.setDateYesterday();
			} else if (action == confirmButton) {
				Database.proceed();
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
