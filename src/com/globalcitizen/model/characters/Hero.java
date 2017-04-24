package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.Visitor;

public class Hero extends Creature {

	public void paintComponent(Graphics g, Visitor visitor) {
		visitor.onDrawHero(g, this);
	}

	private int currentVerticalUp;
	public final ImageIcon[] verticalUpImages = new ImageIcon[] {
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/vu_01.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/vu_02.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/vu_03.png")) };

	private int currentVertical;
	public final ImageIcon[] verticalImages = new ImageIcon[] {
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/v_01.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/v_02.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/v_03.png")) };

	private int currentHorizontal;
	public final ImageIcon[] verticalhorizontal = new ImageIcon[] {
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/h_01.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/h_02.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/h_03.png")) };

	private int currentHorizontalRight;
	public final ImageIcon[] verticalhorizontalRigth = new ImageIcon[] {
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/hl_01.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/hl_02.png")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/hl_03.png")) };

	public int getCurrentVertical() {
		return currentVertical;
	}

	public void moveHorizontalRigth() {
		this.currentHorizontalRight++;
		switch (currentHorizontalRight) {
		case 1:
			this.setIcon(verticalhorizontalRigth[0]);
			break;
		case 2:
			this.setIcon(verticalhorizontalRigth[1]);
			break;
		case 3:
			this.setIcon(verticalhorizontalRigth[0]);
			break;
		case 4:
			this.setIcon(verticalhorizontalRigth[2]);
			this.currentHorizontalRight = 0;
			break;
		default:
			break;
		}

	}

	public void moveHorizontalLeft() {
		this.currentHorizontal++;
		switch (currentHorizontal) {
		case 1:
			this.setIcon(verticalhorizontal[0]);
			break;
		case 2:
			this.setIcon(verticalhorizontal[1]);
			break;
		case 3:
			this.setIcon(verticalhorizontal[0]);
			break;
		case 4:
			this.setIcon(verticalhorizontal[2]);
			this.currentHorizontal = 0;
			break;
		default:
			break;
		}

	}

	public void moveVerticalUp() {
		this.currentVerticalUp++;
		switch (currentVerticalUp) {
		case 1:
			this.setIcon(verticalUpImages[0]);
			break;
		case 2:
			this.setIcon(verticalUpImages[1]);
			break;
		case 3:
			this.setIcon(verticalUpImages[0]);
			break;
		case 4:
			this.setIcon(verticalUpImages[2]);
			this.currentVerticalUp = 0;
			break;
		default:
			break;
		}

	}

	public void moveVerticalDown() {
		this.currentVertical++;
		switch (currentVertical) {
		case 1:
			this.setIcon(verticalImages[0]);
			break;
		case 2:
			this.setIcon(verticalImages[1]);
			break;
		case 3:
			this.setIcon(verticalImages[0]);
			break;
		case 4:
			this.setIcon(verticalImages[2]);
			this.currentVertical = 0;
			break;
		default:
			break;
		}

	}

	public Hero(Point currentPosition, JLabel map) {
		super(currentPosition, map, 1, GlobalCitizenConstants.CREATURE_TYPE_HERO);
		this.setHorizontalUnits(GlobalCitizenConstants.HERO_WIDTH);
		this.setVerticalUnits(GlobalCitizenConstants.HERO_HEIGHT);
		this.setCreatureType(GlobalCitizenConstants.CREATURE_TYPE_HERO);
		this.currentVertical = 0;
		moveVerticalDown();
	}

	public boolean moveRight(List<Street> listStreets) {
		moveHorizontalRigth();
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x + 1, this.getCurrentPosition().y)) {
			this.getCurrentPosition().x += 1;
			return true;
		}
		return false;

	}

	public boolean moveUp(List<Street> listStreets) {
		moveVerticalUp();
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x, this.getCurrentPosition().y - 1)) {
			this.getCurrentPosition().y -= 1;
			return true;
		}
		return false;

	}

	public boolean moveLeft(List<Street> listStreets) {
		moveHorizontalLeft();
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x - 1, this.getCurrentPosition().y)) {
			this.getCurrentPosition().x -= 1;
			return true;
		}
		return false;

	}

	public boolean moveDown(List<Street> listStreets) {
		moveVerticalDown();
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x, this.getCurrentPosition().y + 1)) {
			this.getCurrentPosition().y += 1;
			return true;
		}
		return false;

	}

}
