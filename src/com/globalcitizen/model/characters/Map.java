package com.globalcitizen.model.characters;

import java.util.List;

import com.shape.visitor.VisitorDraw;

public class Map {
	List<Street> streets;
	List<Creature> creatures;
	List<Buildings> buildings;
	Hero hero;

	public Map(List<Street> streets, Hero hero) {
		this.streets = streets;
		this.hero = hero;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public List<Buildings> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Buildings> buildings) {
		this.buildings = buildings;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<Creature> creatures) {
		this.creatures = creatures;
	}

	public boolean moveCarsFromStreets(VisitorDraw visitor) {
		if (streets != null && streets.size() > 0) {
			for (Street street : streets) {
				street.moveCars(visitor);
			}
		}
		return true;
	}

}
