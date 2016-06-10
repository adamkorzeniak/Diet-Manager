package com.github.adkorzen.dietManager.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.adkorzen.dietManager.DateSetting;
import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.GUI.CalendarView.Menu;

public class DayMenu {
	private static JFrame frame;
	private JPanel northPanel;
	private static JButton dayBack, dayForward, datePicked;
	private static DateFormat df;

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

		frame.setSize(500, 500);
		frame.setVisible(true);
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
