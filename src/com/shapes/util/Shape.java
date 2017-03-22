package com.shapes.util;

import java.awt.Color;

public interface Shape {

	void setLocation(int x, int y);

	void setForegroundColor(Color color);

	void setBackgroundColor(Color color);

	void draw();

	Rectangle boundingBox();

	default void accept(Draw draw){
		
	}
}
