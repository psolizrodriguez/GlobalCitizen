package com.shapes.util;

import java.awt.Color;

public class Fill extends ShapeDecorator {
	public Fill(Color color, Shape shapes) {
		super(shapes);
	}
}
