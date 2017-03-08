package com.globalcitizen.model.view;

import java.awt.Color;
import java.util.ArrayList;

public class DataOfSquare {

	// ArrayList that'll contain the colors
	ArrayList<Color> C = new ArrayList<Color>();
	int color; // 2: snake , 1: food, 0:empty
	SquarePanel square;

	public DataOfSquare(int col) {

		// Lets add the color to the arrayList
		C.add(Color.darkGray);// 0
		C.add(Color.BLUE); // 1
		C.add(Color.white); // 2
		color = col;
		square = new SquarePanel(C.get(color));
	}

	public void lightMeUp(int c) {
		square.ChangeColor(C.get(c));
	}
}
