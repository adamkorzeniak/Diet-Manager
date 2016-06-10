package com.github.adkorzen.dietManager.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DayMenu {
	private JFrame frame;
	private JPanel northPanel;
	private JButton dayBack, dayForward, datePicked;
	public void createAndShowGUI() {
	MainMenu.getFrame().setVisible(false);
	frame = new JFrame("Add to database");
	
	dayBack = new JButton("<");
	dayBack.addActionListener(new DateButtonListener());
	dayForward = new JButton(">");
	dayBack.addActionListener(new DateButtonListener());
	datePicked = new JButton(MainMenu.getSpinerEditor().getTextField().getValue().toString());
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
				
			} else if (e.getSource() == dayForward) {
				
			} else if (e.getSource() == datePicked) {
				
			}
		}
		
	}
}
