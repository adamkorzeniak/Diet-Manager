package com.github.adkorzen.dietManager.GUI;

import static com.github.adkorzen.dietManager.GUI.GUICoinstraints.setGUIConstraints;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.github.adkorzen.dietManager.DateSetting;
import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.GUI.CalendarView.Menu;

public class DayMenu {
	private static JFrame frame;
	private JPanel northPanel, southPanel;
	private int monitorWidth, monitorHeight;
	private GridBagConstraints c;
	private static JButton dayBack, dayForward, datePicked, addMealButton, deleteButton, editButton;
	private static DateFormat df;
	private static JScrollPane scroll;
	private static JTable table;

	public void createAndShowGUI() {
		MainMenu.getFrame().setVisible(false);
		frame = new JFrame("Add to database");

		dayBack = new JButton("<");
		dayBack.addActionListener(new DateButtonListener());
		dayForward = new JButton(">");
		dayForward.addActionListener(new DateButtonListener());
		df = new SimpleDateFormat("dd/MM/yyyy");
		datePicked = new JButton(df.format(ManageDate.getDate()));
		datePicked.addActionListener(new DateButtonListener());

		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.PAGE_START);
		northPanel.add(dayBack, BorderLayout.LINE_START);
		northPanel.add(datePicked, BorderLayout.CENTER);
		northPanel.add(dayForward, BorderLayout.LINE_END);
		
		southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());
		
		c = new GridBagConstraints();
		
		addMealButton = new JButton("Add Meal");
		setGUIConstraints(c, 0, 0, 2, 1, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10));
		southPanel.add(addMealButton, c);
		
		deleteButton = new JButton("Delete");
		setGUIConstraints(c, 0, 1, GridBagConstraints.BOTH, new Insets(10, 30, 10, 30));
		southPanel.add(deleteButton, c);
		
		editButton = new JButton("Edit");
		setGUIConstraints(c, 1, 1, GridBagConstraints.BOTH, new Insets(10, 30, 10, 30));
		southPanel.add(editButton, c);
		
		frame.add(southPanel, BorderLayout.PAGE_END);
		
		table = new Table().getTable();
		scroll = new JScrollPane(table);
		frame.getContentPane().add(scroll);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		monitorWidth = (int) screenSize.getWidth();
		monitorHeight = (int) screenSize.getHeight();
		frame.setLocation((monitorWidth - 500) / 2, (monitorHeight - 550) / 2);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getFrame().setVisible(true);
			}
		});
	}

	public class DateButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == dayBack) {
				ManageDate.add(Calendar.DAY_OF_MONTH, -1);
				updateDate();
			} else if (e.getSource() == dayForward) {
				ManageDate.add(Calendar.DAY_OF_MONTH, 1);
				updateDate();
			} else if (e.getSource() == datePicked) {
				CalendarView.setCallFrom(Menu.DayMenu);
				DateSetting.openCalendar();
			}
		}
	}

	public static void updateDate() {
		datePicked.setText(df.format(ManageDate.getDate()));
	}

	public static JFrame getFrame() {
		return frame;
	}
}
