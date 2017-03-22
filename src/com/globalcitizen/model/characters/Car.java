package com.globalcitizen.model.characters;

import java.awt.Graphics;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.globalcitizen.model.viewpercy.GlobalCitizenUtils;
import com.shape.visitor.VisitorDraw;

public class Car extends Creature {
	private Street currentStreet;
	private int car;
	private String color;
	private int movingPattern;

	public Car(Street street, int x, int y) {

		super(new Point(x, y));
		this.setHorizontalUnits(40);
		this.setVerticalUnits(40);
		this.setCreatureType(GlobalCitizenConstants.CREATURE_TYPE_CAR);
		this.currentStreet = street;
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

	@Override
	public void paintComponent(Graphics g, VisitorDraw visitor) {
		visitor.onDrawCar(g, this);
	}

	public boolean moveCar(VisitorDraw visitor) {
		Point nextPosition = GlobalCitizenUtils.getNextLinePoint(this.currentStreet, this);
		if (nextPosition.getX() > currentStreet.getStartingPoint().getX() + currentStreet.getStreetWidth()
				|| nextPosition.getY() > currentStreet.getStartingPoint().getY() + currentStreet.getStreetWidth()) {
		} else {
			this.getCurrentPosition().setX(nextPosition.getX());
			this.getCurrentPosition().setY(nextPosition.getY());
		}
		visitor.repaint();
		return false;
	}

}
