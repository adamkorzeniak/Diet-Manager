package com.github.adkorzen.dietManager;

import java.util.Calendar;
import java.util.Date;

import com.github.adkorzen.dietManager.GUI.CalendarView;
import com.github.adkorzen.dietManager.GUI.CalendarView.Menu;
import com.github.adkorzen.dietManager.GUI.DayMenu;
import com.github.adkorzen.dietManager.GUI.MainMenu;

public class DateSetting {
	
	public static void openCalendar() {
		if (CalendarView.getCallFrom() == Menu.DayMenu){
			DayMenu.getFrame().setEnabled(false);
		} else {
		MainMenu.getFrame().setEnabled(false);
		}
		CalendarView.showCalendar();
	}

	public static void setDateToday() {
		Calendar calendar = Calendar.getInstance();
		ManageDate.setDate(calendar.getTime());
		MainMenu.getSpinerEditor().setValue(ManageDate.getDate());
	}

	public static void setDateYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		MainMenu.getSpinerEditor().setValue(calendar.getTime());
	}
	
}
