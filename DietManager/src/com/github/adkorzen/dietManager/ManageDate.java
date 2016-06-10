package com.github.adkorzen.dietManager;

import java.util.Calendar;
import java.util.Date;

public class ManageDate {
	
	private static Date currentDate;
	private static Date earliestDate;
	private static Date latestDate;
	private static Calendar calendar;
	
	static {
		calendar = Calendar.getInstance();
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
	public static void setDate(Date date) {
		currentDate = date;
	}
	public static void add(int field, int amount) {
		calendar.setTime(currentDate);
		calendar.add(field, amount);
		currentDate = calendar.getTime();
	}
}
