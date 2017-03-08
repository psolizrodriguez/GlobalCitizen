package com.globalcitizen.model.characters;

import java.util.List;

public abstract class CreatureBehavior {
	private int movementPattern;
	private List<CreatureAction> listCreatureAction;
	
	public List<CreatureAction> getListCreatureAction() {
		return listCreatureAction;
	}

	public void setListCreatureAction(List<CreatureAction> listCreatureAction) {
		this.listCreatureAction = listCreatureAction;
	}

	public int getMovementPattern() {
		return movementPattern;
	}

	public void setMovementPattern(int movementPattern) {
		this.movementPattern = movementPattern;
	}

}
