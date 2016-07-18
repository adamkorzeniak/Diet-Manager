package com.github.adkorzen.dietManager.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

import com.github.adkorzen.dietManager.ManageDate;
import com.github.adkorzen.dietManager.GUI.CalendarView.Menu;

public class CalendarView {

	private static Menu callFrom;

	public static void showCalendar() {

		JFrame frame = new JFrame("Pick a date");
		JPanel panel = new JPanel();

		frame.setBounds(400, 400, 250, 100);

		Date date = ManageDate.getDate();
		JXDatePicker picker = new JXDatePicker(date);
		picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

		Date lowerBound = ManageDate.getMinDate();
		Date upperBound = ManageDate.getMaxDate();

		picker.getMonthView().setLowerBound(lowerBound);
		picker.getMonthView().setUpperBound(upperBound);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date datePicked = picker.getDate();
				ManageDate.setDate(datePicked);

				frame.dispose();

				if (CalendarView.getCallFrom() == Menu.DayMenu) {
					DayMenu.getFrame().setEnabled(true);
					DayMenu.updateDate();
					DayMenu.updateTable();
				} else {
					MainMenu.getFrame().setEnabled(true);
					MainMenu.getSpinerEditor().setValue(ManageDate.getDate());
				}

			}
		});

		panel.add(picker);
		panel.add(ok);
		frame.getContentPane().add(panel);

		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (CalendarView.getCallFrom() == Menu.DayMenu) {
					DayMenu.getFrame().setEnabled(true);
				} else {
					MainMenu.getFrame().setEnabled(true);
				}
			}
		});
	}

	public enum Menu {
		MainMenu, DayMenu
	}

	public static void setCallFrom(Menu object) {
		callFrom = object;
	}

	public static Menu getCallFrom() {
		return callFrom;
	}
}
