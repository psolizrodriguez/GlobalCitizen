package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.VisitorDraw;

public class Hero extends Creature {
	public Hero(Point currentPosition, JPanel map) {
		super(currentPosition, map, 1);
		this.setHorizontalUnits(20);
		this.setVerticalUnits(20);
		this.setCreatureType(GlobalCitizenConstants.CREATURE_TYPE_HERO);
	}

	public void paintComponent(Graphics g, VisitorDraw visitor) {
		visitor.onDrawHero(g, this);
	}

	public boolean moveRight(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x + 1, this.getCurrentPosition().y)) {
			this.getCurrentPosition().x += 1;
			return true;
		}
		return false;

	}

	public boolean moveUp(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x, this.getCurrentPosition().y - 1)) {
			this.getCurrentPosition().y -= 1;
			return true;
		}
		return false;

	}

	public boolean moveLeft(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x - 1, this.getCurrentPosition().y)) {
			this.getCurrentPosition().x -= 1;
			return true;
		}
		return false;

	}

	public boolean moveDown(List<Street> listStreets) {
		if (iscreatureInsideOfStreets(listStreets, this.getCurrentPosition().x, this.getCurrentPosition().y + 1)) {
			this.getCurrentPosition().y += 1;
			return true;
		}
		return false;

	}

}
