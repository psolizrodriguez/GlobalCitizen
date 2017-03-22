package com.shapes.client;

import java.awt.Color;

import com.shapes.util.Circle;
import com.shapes.util.Fill;
import com.shapes.util.Group;
import com.shapes.util.Location;
import com.shapes.util.Rectangle;
import com.shapes.util.Shape;
import com.shapes.util.Stroke;

public class MainRun {
	public static void main(String arg[]) {
		final Shape s = new Group(
				  new Stroke(Color.BLUE, new Rectangle(20, 10)),
				  new Location(100, 0, new Group(
				    new Fill(Color.RED, new Circle(5)),
				    new Circle(10),
				    new Circle(15)
				  )
				));
				//final Canvas canvas = ...
				//s.accept(new Draw(canvas));
	}
}
