package com.github.adkorzen.dietManager.GUI;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GUICoinstraints {
	
	public static void setGUIConstraints(GridBagConstraints c, int posX, int posY, int width, int height, double weightx, double weighty, int fill,
			Insets inset) {
		c.gridx = posX;
		c.gridy = posY;
		c.gridwidth = width;
		c.gridheight = height;
		c.weightx = weightx;
		c.weighty = weighty;
		c.fill = fill;
		c.insets = inset;
	}

	public static void setGUIConstraints(GridBagConstraints c, int posX, int posY, double weightx, double weighty, int fill, Insets inset) {
		setGUIConstraints(c, posX, posY, 1, 1, weightx, weighty, fill, inset);
	}

	public static void setGUIConstraints(GridBagConstraints c, int posX, int posY, int width, int height, int fill, Insets inset) {
		setGUIConstraints(c, posX, posY, width, height, 1, 1, fill, inset);
	}

	public static void setGUIConstraints(GridBagConstraints c, int posX, int posY, int fill, Insets inset) {
		setGUIConstraints(c, posX, posY, 1, 1, 1, 1, fill, inset);
	}
}
