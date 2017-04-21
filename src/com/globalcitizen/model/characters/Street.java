package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.shape.visitor.VisitorDraw;

public class Street {
	int direction;
	Point startingPoint;
	int streetWidth;
	int streetHeight;
	int rightSideWalkSize;
	int leftSideWalkSize;
	List<Car> listCars;
	List<Street> streets;

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public Street(int direction, Point startingPoint, int streetWidth) {
		this.direction = direction;
		this.startingPoint = startingPoint;
		if (direction == 2 || direction == 4) {
			this.streetWidth = GlobalCitizenConstants.STREET_WIDTH;
			this.streetHeight = streetWidth;
		} else {

			this.streetWidth = streetWidth;
			this.streetHeight = GlobalCitizenConstants.STREET_WIDTH;
		}

		listCars = new ArrayList<>();
		streets = new ArrayList<>();
	}

	public List<Car> getListCars() {
		return listCars;
	}

	public void setListCars(List<Car> listCars) {
		this.listCars = listCars;
	}

	public int getStreetHeight() {
		return streetHeight;
	}

	public void setStreetHeight(int streetHeight) {
		this.streetHeight = streetHeight;
	}

	public void paintComponent(Graphics g, VisitorDraw visitor) {
		visitor.onDrawStreet(g, this);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Point getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	public int getStreetWidth() {
		return streetWidth;
	}

	public void setStreetWidth(int streetWidth) {
		this.streetWidth = streetWidth;
	}

	public int getRightSideWalkSize() {
		return rightSideWalkSize;
	}

	public void setRightSideWalkSize(int rightSideWalkSize) {
		this.rightSideWalkSize = rightSideWalkSize;
	}

	public int getLeftSideWalkSize() {
		return leftSideWalkSize;
	}

	public void setLeftSideWalkSize(int leftSideWalkSize) {
		this.leftSideWalkSize = leftSideWalkSize;
	}

	public Car createCar(JPanel map) {
		int initial_x = this.getStartingPoint().x;
		int initial_y = this.getStartingPoint().y;

		switch (this.getDirection()) {
		case 2:
			break;
		case 3:
			initial_x = this.getStartingPoint().x + this.getStreetWidth() - GlobalCitizenConstants.CAR_WIDTH;
			break;
		case 4:
			initial_y = this.getStartingPoint().y + this.getStreetHeight() - GlobalCitizenConstants.CAR_WIDTH;
			break;
		default:
			break;
		}
		Car car = new Car(map, this, initial_x, initial_y);
		listCars.add(car);
		return car;
	}

	public boolean moveCars(VisitorDraw visitor) {
		if (listCars != null && listCars.size() > 0) {
			for (Car car : listCars) {
				car.moveCar(visitor);
			}
		} else {
			return false;
		}

		return true;
	}

}
