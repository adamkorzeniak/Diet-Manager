package com.github.adkorzen.dietManager.Listener;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class NumberListener {
	public static void integerListener(JTextField field, KeyEvent e, int limit) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE) {
			e.consume();
		} else if (!field.getText().isEmpty() && Integer.parseInt(field.getText()) > limit
				&& field.getSelectedText() == null) {
			e.consume();
		}
	}

	public static void doubleListener(JTextField field, KeyEvent e, double limit) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD) {
			e.consume();
		} else if (!field.getText().isEmpty() && Double.parseDouble(field.getText()) > limit
				&& field.getSelectedText() == null) {
			e.consume();
		} else if (field.getText().contains(".")) {
			if (c == KeyEvent.VK_PERIOD) {
				e.consume();
			} else {
				int dot = field.getText().indexOf('.');
				if (field.getText().length() - dot > 2 && field.getCaretPosition() > dot && field.getSelectedText() == null) {
					e.consume();
				}
			}
		}
	}
}
