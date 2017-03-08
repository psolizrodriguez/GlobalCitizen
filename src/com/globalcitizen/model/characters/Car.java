package com.globalcitizen.model.characters;

public class Car extends Creature {
	private int car;
	private String color;
	private int movingPattern;

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

}
