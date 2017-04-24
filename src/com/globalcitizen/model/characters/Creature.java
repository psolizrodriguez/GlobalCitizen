package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.Visitor;

public abstract class Creature extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Rectangle currentSquare;
	private Shape currentLogicalShape;
	JLabel map;

	public Shape getCurrentLogicalShape() {
		return currentLogicalShape;
	}

	public void setCurrentLogicalShape(Shape currentLogicalShape) {
		this.currentLogicalShape = currentLogicalShape;
	}

	public Rectangle getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Rectangle currentSquare) {
		this.currentSquare = currentSquare;
	}

	public Rectangle getPreviousSquare() {
		return previousSquare;
	}

	public void setPreviousSquare(Rectangle previousSquare) {
		this.previousSquare = previousSquare;
	}

	/**
	 * @param creatureType
	 *            1 Hero, 2 ambulance
	 **/
	public Creature(Point currentPosition, JLabel map, int streetDirection, int creatureType) {
		super();
		this.currentPosition = currentPosition;
		this.map = map;
		ImageIcon background;
		if (creatureType == 1) {
			/*
			 * background = new ImageIcon( this.getClass().getResource(
			 * "/com/globalcitizen/model/viewpercy/Ambulance.png"));
			 */
			this.setText("");
			//this.setIcon(new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/v_01.png")));
			this.setHorizontalUnits(GlobalCitizenConstants.HERO_WIDTH);
			this.setVerticalUnits(GlobalCitizenConstants.HERO_HEIGHT);
		} else {
			if (streetDirection == 2 || streetDirection == 4) {
				this.setHorizontalUnits(GlobalCitizenConstants.CAR_HEIGHT);
				this.setVerticalUnits(GlobalCitizenConstants.CAR_WIDTH);
			} else {
				this.setHorizontalUnits(GlobalCitizenConstants.CAR_WIDTH);
				this.setVerticalUnits(GlobalCitizenConstants.CAR_HEIGHT);
			}
			background = new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/Ambulance.png"));
			this.setIcon(background);
		}
		this.setBounds(currentPosition.x, currentPosition.y, this.horizontalUnits, this.verticalUnits);
		// this.setIcon(background);
		map.add(this);

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
			for (Street street : listStreets) {
				if (street.getStartingPoint().getX() <= futureX
						&& street.getStartingPoint().getX() + street.getStreetWidth() >= futureX + horizontalUnits) {
					if (street.getStartingPoint().getY() <= futureY
							&& street.getStartingPoint().getY() + street.getStreetHeight() >= futureY + verticalUnits) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int moveScroll(List<Street> listStreets, int currentMapPiece) {
		if (listStreets != null && listStreets.size() > 0) {
			for (Street street : listStreets) {
				if (street.isHasToChangeView()) {
					if (currentPosition.y + this.getHeight() > street.getStartingPoint().getY()
							+ street.getStreetHeight() && currentMapPiece == 1) {
						return 2;
					} else {
						if (currentPosition.y < street.getStartingPoint().getY() && currentMapPiece == 2) {
							return 1;
						}
					}
				}

			}
		}
		return 0;
	}

	public abstract void paintComponent(Graphics g, Visitor visitor);

}
