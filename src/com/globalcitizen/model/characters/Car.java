package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLabel;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.Visitor;
import com.shape.visitor.VisitorDraw;

public class Car extends Creature {
	private Street currentStreet;
	private int car;
	private String color;
	private int movingPattern;

	@Override
	public void paintComponent(Graphics g, Visitor visitor) {
		visitor.onDrawCar(g, this);
	}

	public Car(JLabel map, Street street, int x, int y) {
		super(new Point(x, y), map, street.getDirection(), GlobalCitizenConstants.CREATURE_TYPE_CAR);
		this.setText("X");

		this.setCreatureType(GlobalCitizenConstants.CREATURE_TYPE_CAR);
		this.currentStreet = street;
		this.map = map;
	}

	public Street getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(Street currentStreet) {
		this.currentStreet = currentStreet;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMovingPattern() {
		return movingPattern;
	}

	public void setMovingPattern(int movingPattern) {
		this.movingPattern = movingPattern;
	}

	public boolean moveCar(VisitorDraw visitor) {
		return visitor.moveCar(this);
	}

}
