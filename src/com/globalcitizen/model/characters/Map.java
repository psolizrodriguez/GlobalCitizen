package com.globalcitizen.model.characters;

import java.util.List;

public class Map {
	List<Landmarks> landmarks;
	List<Streets> streets;
	List<Creature> creatures;
	List<Buildings> buildings;

	public List<Buildings> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Buildings> buildings) {
		this.buildings = buildings;
	}

	public List<Landmarks> getLandmarks() {
		return landmarks;
	}

	public void setLandmarks(List<Landmarks> landmarks) {
		this.landmarks = landmarks;
	}

	public List<Streets> getStreets() {
		return streets;
	}

	public void setStreets(List<Streets> streets) {
		this.streets = streets;
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<Creature> creatures) {
		this.creatures = creatures;
	}

}
