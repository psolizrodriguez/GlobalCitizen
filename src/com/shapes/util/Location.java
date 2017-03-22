package com.shapes.util;

public class Location extends ShapeDecorator {
	public Location(int x, int y, Shape shapes) {
		super(shapes);
	}
}
