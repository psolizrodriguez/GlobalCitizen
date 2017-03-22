package com.shape.visitor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Street;

public class VisitorDraw extends JLabel {
	Map map;
	Rectangle heroSquare;
	boolean mapCreated;
	int carRefreshRateCounter = 0;
	int carRefreshRate = 1;
	int carCounter = 0;

	public Rectangle getHeroSquare() {
		return heroSquare;
	}

	public void setHeroSquare(Rectangle heroSquare) {
		this.heroSquare = heroSquare;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public VisitorDraw(Map map, ImageIcon background) {
		super(background);
		this.map = map;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// We draw the streets
		if (!mapCreated) {
			if (map.getStreets().size() > 0) {
				for (Street street : map.getStreets()) {
					street.paintComponent(g, this);
				}
			}
			mapCreated = true;
		}
		// We draw the tourist
		map.getHero().paintComponent(g, this);
		// We refresh the cars
		if (map.getStreets().size() > 0) {
			for (Street street : map.getStreets()) {
				if (street.getListCars().size() > 0) {
					for (Car car : street.getListCars()) {
						car.paintComponent(g, this);
					}
				}
			}
		}

	}

	public void mainUpdateProccess() {
		if (carCounter == 50) {
			Random rand = new Random();
			int n = rand.nextInt(map.getStreets().size());
			map.getStreets().get(n).createCar();
			carCounter = 0;
		}
		carCounter++;
		map.moveCarsFromStreets(this);
	}

	public void onDrawStreet(Graphics g, Street street) {
		System.out.println("Being called " + street.getStartingPoint().getX() + " " + street.getStartingPoint().getY());
		// super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform oldXForm = g2d.getTransform();

		g2d.setColor(Color.WHITE);
		Rectangle rectangle = new Rectangle(street.getStartingPoint().getX(), street.getStartingPoint().getY(),
				street.getStreetWidth(), street.getStreetHeight());
		g2d.rotate(Math.toRadians(street.getDirection()), street.getStartingPoint().getX(),
				street.getStartingPoint().getY());
		Shape rotatedRect = g2d.getTransform().createTransformedShape(rectangle);
		/*
		 * Rectangle tangle = new Rectangle(0, 0, radius, radius);
		 * g2d.translate(centerX, centerY);
		 */

		/*
		 * g2d.rotate(Math.toRadians(street.getDirection()), rectangle.getX() +
		 * rectangle.width / 2, rectangle.getY() + rectangle.height / 2);
		 */
		// Draw Streets
		// g2d.fill(rectangle);
		street.setLogicalForm(rotatedRect);
		g2d.setTransform(oldXForm); // Restore transform

	}

	public void onDrawHero(Graphics g, Hero hero) {
		System.out.println("Being called " + hero.getCurrentPosition().getX() + " " + hero.getCurrentPosition().getY());
		// super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (heroSquare != null) {
			/*
			 * g2d.setColor(this.getBackground()); g2d.fill(heroSquare);
			 */
			g2d.clearRect(heroSquare.x, heroSquare.y, heroSquare.width, heroSquare.height);
		}

		g2d.setColor(Color.RED);
		Rectangle rect2 = new Rectangle(hero.getCurrentPosition().getX(), hero.getCurrentPosition().getY(),
				hero.getHorizontalUnits(), hero.getVerticalUnits());
		// g2d.draw(rect2);
		g2d.fill(rect2);

		heroSquare = rect2;
	}

	public void moveHero(int keycode) {
		boolean changed = false;
		switch (keycode) {
		case 39: // -> Right
			changed = getMap().getHero().moveRight(getMap().getStreets());
			System.out.println("right");
			break;
		case 38: // -> Top
			changed = getMap().getHero().moveUp(getMap().getStreets());
			System.out.println("Top");
			break;

		case 37: // -> Left
			changed = getMap().getHero().moveLeft(getMap().getStreets());
			System.out.println("left");
			break;

		case 40: // -> Bottom
			changed = getMap().getHero().moveDown(getMap().getStreets());
			System.out.println("bottom");
			break;

		default:
			break;
		}
		this.repaint();
	}

	public void onDrawCar(Graphics g, Car car) {
		System.out.println(
				"Being called onDrawCar " + car.getCurrentPosition().getX() + " " + car.getCurrentPosition().getY());
		Graphics2D g2d = (Graphics2D) g;
		if (car.getPreviousSquare() != null) {
			g2d.clearRect(car.getPreviousSquare().x, car.getPreviousSquare().y, car.getPreviousSquare().width,
					car.getPreviousSquare().height);
		}
		g2d.setColor(Color.BLUE);
		Rectangle rect2 = new Rectangle(car.getCurrentPosition().getX(), car.getCurrentPosition().getY(),
				car.getHorizontalUnits(), car.getVerticalUnits());
		g2d.fill(rect2);
		car.setPreviousSquare(rect2);
	}

	public void addComponentListener(ComponentEvent componentAdapter) {
		this.repaint();
	}
}
