package com.github.adkorzen.dietManager;

import java.util.Calendar;

import com.github.adkorzen.dietManager.GUI.CalendarView;
import com.github.adkorzen.dietManager.GUI.MainMenu;

public class DateSetting {

	public void openCalendar() {
		MainMenu.getFrame().setEnabled(false);
		CalendarView.showCalendar();
	}

	public void setDateToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		MainMenu.getSpinerEditor().getTextField().setValue(calendar.getTime());
	}

	public void setDateYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		MainMenu.getSpinerEditor().getTextField().setValue(calendar.getTime());
	}
}
