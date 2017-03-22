package com.shapes.util;

import java.awt.Color;

public abstract class ShapeDecorator implements Shape {
	protected Shape shapes;

	public ShapeDecorator(Shape shapes) {
		this.shapes = shapes;
	}

	public void draw() {
		shapes.draw();
	}

	public void setLocation(int x, int y) {
		shapes.setLocation(x, y);

	}

	public void setForegroundColor(Color color) {
		shapes.setForegroundColor(color);

	}

	public void setBackgroundColor(Color color) {
		shapes.setBackgroundColor(color);
	}

	public Rectangle boundingBox() {
		return shapes.boundingBox();
	}
}