package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import com.shape.visitor.VisitorDraw;

public abstract class Creature {
	private List<Inventory> listInventory;
	private List<CreatureBehavior> listCreatureBehavior;
	private int movementSpeed;
	private int horizontalUnits;
	private int verticalUnits;
	private int currentEnergyPoints;
	private int maximunEnergyPoints;
	private String spriteModel;
	private Point currentPosition;
	private int creatureType;
	private Rectangle previousSquare;

	public Rectangle getPreviousSquare() {
		return previousSquare;
	}

	public void setPreviousSquare(Rectangle previousSquare) {
		this.previousSquare = previousSquare;
	}

	public Creature(Point currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getCreatureType() {
		return creatureType;
	}

	public void setCreatureType(int creatureType) {
		this.creatureType = creatureType;
	}

	public int getCurrentEnergyPoints() {
		return currentEnergyPoints;
	}

	public void setCurrentEnergyPoints(int currentEnergyPoints) {
		this.currentEnergyPoints = currentEnergyPoints;
	}

	public int getMaximunEnergyPoints() {
		return maximunEnergyPoints;
	}

	public void setMaximunEnergyPoints(int maximunEnergyPoints) {
		this.maximunEnergyPoints = maximunEnergyPoints;
	}

	public String getSpriteModel() {
		return spriteModel;
	}

	public void setSpriteModel(String spriteModel) {
		this.spriteModel = spriteModel;
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Point currentPosition) {
		this.currentPosition = currentPosition;
	}

	public List<CreatureBehavior> getListCreatureBehavior() {
		return listCreatureBehavior;
	}

	public void setListCreatureBehavior(List<CreatureBehavior> listCreatureBehavior) {
		this.listCreatureBehavior = listCreatureBehavior;
	}

	public int getHorizontalUnits() {
		return horizontalUnits;
	}

	public void setHorizontalUnits(int horizontalUnits) {
		this.horizontalUnits = horizontalUnits;
	}

	public int getVerticalUnits() {
		return verticalUnits;
	}

	public void setVerticalUnits(int verticalUnits) {
		this.verticalUnits = verticalUnits;
	}

	public List<Inventory> getListInventory() {
		return listInventory;
	}

	public void setListInventory(List<Inventory> listInventory) {
		this.listInventory = listInventory;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public boolean iscreatureInsideOfStreets(List<Street> listStreets, int futureX, int futureY) {
		if (listStreets != null && listStreets.size() > 0) {
			Rectangle creature = new Rectangle(futureX, futureY, getHorizontalUnits(), getVerticalUnits());
			for (Street street : listStreets) {
				if (street.getLogicalForm().contains(creature)) {
					return true;
				}
			}
		}
		return false;
	}

	public abstract void paintComponent(Graphics g, VisitorDraw visitor);

}
