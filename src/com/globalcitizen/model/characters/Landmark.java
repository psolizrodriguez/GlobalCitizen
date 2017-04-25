package com.globalcitizen.model.characters;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Landmark extends Street {
	ImageIcon mapImage;
	ImageIcon menuImage;
	int secondsToComplete;
	int secondsToWinChallenge;
	JButton button;

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

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
