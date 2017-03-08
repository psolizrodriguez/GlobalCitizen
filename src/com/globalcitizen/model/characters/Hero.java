package com.globalcitizen.model.characters;

public class Hero extends Creature {
	private int energyPoints;
	private int maximunEnergyPoints;
	private String spriteModel;

	public int getEnergyPoints() {
		return energyPoints;
	}

	public void setEnergyPoints(int energyPoints) {
		this.energyPoints = energyPoints;
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

}
