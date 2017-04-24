package com.globalcitizen.model.characters;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Landmark extends Street {
	ImageIcon mapImage;
	ImageIcon menuImage;
	int secondsToComplete;
	int secondsToWinChallenge;

	public Landmark(int direction, Point startingPoint, int streetWidth, ImageIcon mapImage, ImageIcon menuImage,
			int secondsToComplete, int secondsToWinChallenge) {

		super(direction, startingPoint, streetWidth);
		this.mapImage = mapImage;
		this.menuImage = menuImage;
		this.secondsToComplete = secondsToComplete;
		this.secondsToWinChallenge = secondsToWinChallenge;
		this.landmark = true;
	}

	public ImageIcon getMapImage() {
		return mapImage;
	}

	public void setMapImage(ImageIcon mapImage) {
		this.mapImage = mapImage;
	}

	public ImageIcon getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(ImageIcon menuImage) {
		this.menuImage = menuImage;
	}

	public int getSecondsToComplete() {
		return secondsToComplete;
	}

	public void setSecondsToComplete(int secondsToComplete) {
		this.secondsToComplete = secondsToComplete;
	}

	public int getSecondsToWinChallenge() {
		return secondsToWinChallenge;
	}

	public void setSecondsToWinChallenge(int secondsToWinChallenge) {
		this.secondsToWinChallenge = secondsToWinChallenge;
	}

}
