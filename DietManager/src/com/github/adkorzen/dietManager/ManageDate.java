package com.github.adkorzen.dietManager;

import java.util.Calendar;
import java.util.Date;

public class ManageDate {
	
	public static Date currentDate;
	public static Date earliestDate;
	public static Date latestDate;
	
	static {
		Calendar calendar = Calendar.getInstance();
		currentDate = calendar.getTime();
		calendar.set(2000, 00, 01);
		earliestDate = calendar.getTime();
		calendar.set(2040, 00, 00);
		latestDate = calendar.getTime();
	}
	
	public static Date getMaxDate() {
		return latestDate;
	}
	public static Date getMinDate() {
		return earliestDate;
	}
	public static Date getDate() {
		return currentDate;
	}
}
