package com.github.adkorzen.dietManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Options {

	private static boolean caloriesOptionsSet = false;
	private static boolean compositionOptionsSet = false;

	private static int caloriesLimit;
	private static int[] percentage = new int[3];

	public static void setCaloriesOptionsSet(boolean cal) {
		caloriesOptionsSet = cal;
	}

	public static void setCompositionOptionsSet(boolean cal) {
		compositionOptionsSet = cal;
	}

	public static boolean getCaloriesOptionsSet() {
		return caloriesOptionsSet;
	}

	public static boolean getCompositionOptionsSet() {
		return compositionOptionsSet;
	}

	public static void setCaloriesLimit(int limit) {
		caloriesLimit = limit;
	}

	public static int getCaloriesLimit() {
		return caloriesLimit;
	}

	public static void setPercentage(int[] percentages) {
		percentage = percentages;
	}

	public static int getPercentage(int index) {
		if(index < 3) return percentage[index];
		return 0;
	}

	public static void storeSettings() {

		File file = new File("data/options.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			writer.write("");
			writer.write(caloriesOptionsSet + "\n");
			writer.write(compositionOptionsSet + "\n");
			writer.write(caloriesLimit + "\n");
			writer.write(percentage[0] + "\n");
			writer.write(percentage[1] + "\n");
			writer.write(percentage[2] + "");

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void accessData() {
		File file = new File("data/options.txt");
		try {

			if (file.exists()) {
				FileReader fileReader = new FileReader(file);
				BufferedReader bf = new BufferedReader(fileReader);
				caloriesOptionsSet = Boolean.parseBoolean(bf.readLine());
				compositionOptionsSet = Boolean.parseBoolean(bf.readLine());
				caloriesLimit = Integer.parseInt(bf.readLine());
				percentage = new int [3];
				percentage[0] = Integer.parseInt(bf.readLine());
				percentage[1] = Integer.parseInt(bf.readLine());
				percentage[2] = Integer.parseInt(bf.readLine());

				fileReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
