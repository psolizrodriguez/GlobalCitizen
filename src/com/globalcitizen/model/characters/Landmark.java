package com.globalcitizen.model.characters;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Landmark extends Street {
	ImageIcon mapImage;
	ImageIcon menuImage;
	int secondsToComplete;
	int secondsToWinChallenge;
	JButton button;
	String landmarkImage;
	String name;
	JLabel door;
	boolean startingPointTourist;

	public boolean isStartingPointTourist() {
		return startingPointTourist;
	}

	public void setStartingPointTourist(boolean startingPointTourist) {
		this.startingPointTourist = startingPointTourist;
	}

	public JLabel getDoor() {
		return door;
	}

	public void setDoor(JLabel door) {
		this.door = door;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandmarkImage() {
		return landmarkImage;
	}

	public void setLandmarkImage(String landmarkImage) {
		this.landmarkImage = landmarkImage;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Landmark(String name, int direction, Point startingPoint, int streetWidth, ImageIcon mapImage,
			ImageIcon menuImage, int secondsToComplete, int secondsToWinChallenge, String landmarkImage,
			boolean startingPointTourist) {

		super(direction, startingPoint, streetWidth);
		this.mapImage = mapImage;
		this.menuImage = menuImage;
		this.secondsToComplete = secondsToComplete;
		this.secondsToWinChallenge = secondsToWinChallenge;
		this.landmark = true;
		this.landmarkImage = landmarkImage;
		this.name = name;
		this.startingPointTourist = startingPointTourist;
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
