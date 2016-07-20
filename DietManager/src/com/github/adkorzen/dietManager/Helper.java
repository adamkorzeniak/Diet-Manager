package com.github.adkorzen.dietManager;

import java.util.Calendar;
import java.util.Date;

import static com.github.adkorzen.dietManager.DatabaseManagement.getInstance;

public class Helper {
	
	public static double calculate(String productName, String amount) {
		int calories = getInstance().getCalories(productName);
		int divider = getInstance().getCaloriesDivider(productName);
		double result = (Double.parseDouble(amount) * calories / divider);
		return result;
	}

	public static String getDescription(String name, int amount, String unit) {
		String s = name + ", " + amount + " " + unit;
		return s;
	}

	public static int calculateAmount(String productName, String unitName, String amount) {
		if (getInstance().isPrimaryUnit(productName, unitName)) {
			int result = (int) Integer.parseInt(amount);
			return result;
		}
		int multiplier = getInstance().getMultiplier(productName, unitName);
		int result = (int) Integer.parseInt(amount) * multiplier;
		return result;
	}

	public static String dateToString(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String result = year + "-" + month + "-" + day;
		return result;
	}

	public static Date stringToDate(String s) {
		Calendar c = Calendar.getInstance();
		int year = Integer.parseInt(s.substring(0, 4));
		int month = Integer.parseInt(s.substring(4, 6)) - 1;
		int day = Integer.parseInt(s.substring(7));
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		Date d = c.getTime();
		return d;

	}
	
}
