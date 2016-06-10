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

public class CalendarView {

	public static void showCalendar() {

		JFrame frame = new JFrame("Pick a date");
		JPanel panel = new JPanel();

		frame.setBounds(400, 400, 250, 100);

		Date date = (Date) MainMenu.getSpinerEditor().getTextField().getValue();
		JXDatePicker picker = new JXDatePicker(date);
		picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

		Calendar calendar = picker.getMonthView().getCalendar();
		calendar.set(2000, 00, 01);
		Date lowerBound = calendar.getTime();
		calendar.set(2040, 00, 00);
		Date upperBound = calendar.getTime();

		picker.getMonthView().setLowerBound(lowerBound);
		picker.getMonthView().setUpperBound(upperBound);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date datePicked = picker.getDate();
				MainMenu.getSpinerEditor().getTextField().setValue(datePicked);
				frame.dispose();
				MainMenu.getFrame().setEnabled(true);
			}
		});

		panel.add(picker);
		panel.add(ok);
		frame.getContentPane().add(panel);

		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getFrame().setEnabled(true);
			}
		});
	}
}
