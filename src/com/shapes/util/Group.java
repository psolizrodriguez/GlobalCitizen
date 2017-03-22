package com.shapes.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group implements Shape {

	private List<Shape> childShapes = new ArrayList<Shape>();

	public Group(Shape... shapes) {
		this.childShapes = Arrays.asList(shapes);
	}

	public void add(Shape shapes) {
		childShapes.add(shapes);
	}

	public void remove(Shape shapes) {
		childShapes.remove(shapes);
	}

	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setForegroundColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackgroundColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		for (Shape shapes : childShapes) {
			shapes.draw();
		}
	}

	@Override
	public Rectangle boundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
