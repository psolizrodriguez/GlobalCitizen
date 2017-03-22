package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.List;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.VisitorDraw;

public class Hero extends Creature {
	public Hero(Point currentPosition) {
		super(currentPosition);
		this.setHorizontalUnits(20);
		this.setVerticalUnits(20);
		this.setCreatureType(GlobalCitizenConstants.CREATURE_TYPE_HERO);
	}

	public void paintComponent(Graphics g, VisitorDraw visitor) {
		visitor.onDrawHero(g, this);
	}

	public boolean moveRight(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().getX() + 1,
				this.getCurrentPosition().getY())) {
			this.getCurrentPosition().setX(this.getCurrentPosition().getX() + 1);
			return true;
		}
		return false;

	}

	public boolean moveUp(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().getX(),
				this.getCurrentPosition().getY() - 1)) {
			this.getCurrentPosition().setY(this.getCurrentPosition().getY() - 1);
			return true;
		}
		return false;

	}

	public boolean moveLeft(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().getX() - 1,
				this.getCurrentPosition().getY())) {
			this.getCurrentPosition().setX(this.getCurrentPosition().getX() - 1);
			return true;
		}
		return false;

	}

	public boolean moveDown(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().getX(),
				this.getCurrentPosition().getY() + 1)) {
			this.getCurrentPosition().setY(this.getCurrentPosition().getY() + 1);
			return true;
		}
		return false;

	}

}
