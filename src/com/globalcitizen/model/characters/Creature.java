package com.globalcitizen.model.characters;

import java.util.List;

public abstract class Creature {
	private List<Inventory> listInventory;
	private List<CreatureBehavior> listCreatureBehavior;
	private int movementSpeed;
	private int horizontalUnits;
	private int verticalUnits;

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

}
