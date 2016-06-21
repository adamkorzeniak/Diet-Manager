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
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.github.adkorzen.dietManager.DatabaseManagement;
import com.github.adkorzen.dietManager.Database;
import com.github.adkorzen.dietManager.DateSetting;
import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.GUI.CalendarView.Menu;

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
	private JTextField spinnerTextField;

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

		Date date = ManageDate.getDate();
		Date earliestDate = ManageDate.getMinDate();
		Date latestDate = ManageDate.getMaxDate();

		model = new SpinnerDateModel(date, earliestDate, latestDate, 1);

		spinner = new JSpinner(model);
		dateEditor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
		spinner.setEditor(dateEditor);
		dateEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spinnerTextField = (JTextField) dateEditor.getTextField();
		setGUIConstraints(c, 0, 2, 3, 1, 1.0, 0.3, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		frame.add(spinner, c);

		spinnerTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});

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
				CalendarView.setCallFrom(Menu.MainMenu);
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
		Locale.setDefault(Locale.US);
		DatabaseManagement.getInstance().createTables();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu().createAndShowGUI();
			}
		});
	}

	public static JFormattedTextField getSpinerEditor() {
		return dateEditor.getTextField();
	}
}
