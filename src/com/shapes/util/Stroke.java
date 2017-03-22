package com.shapes.util;

import java.awt.Color;

public class Stroke extends ShapeDecorator {

	public Stroke(Color color, Shape shapes) {
		super(shapes);
	}

}
