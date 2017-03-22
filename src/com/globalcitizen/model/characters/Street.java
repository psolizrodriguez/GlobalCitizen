package com.globalcitizen.model.characters;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

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
	Shape logicalForm;

	public Shape getLogicalForm() {
		return logicalForm;
	}

	public void setLogicalForm(Shape logicalForm) {
		this.logicalForm = logicalForm;
	}

	public Street(int direction, Point startingPoint, int streetWidth) {
		this.direction = direction;
		this.startingPoint = startingPoint;
		this.streetWidth = streetWidth;
		this.streetHeight = GlobalCitizenConstants.STREET_WIDTH;
		listCars = new ArrayList<>();
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

	public boolean createCar() {
		int initial_x = this.getStartingPoint().getX();
		int initial_y = this.getStartingPoint().getY();
		if (this.getDirection() == 90) {
			initial_x = initial_x - this.getStreetHeight() + GlobalCitizenConstants.SIDE_WIDTH;
		} else {
			if (this.getDirection() == 0) {
				initial_y += GlobalCitizenConstants.SIDE_WIDTH;
			}
		}
		Car car = new Car(this, initial_x, initial_y);
		listCars.add(car);
		return true;
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
