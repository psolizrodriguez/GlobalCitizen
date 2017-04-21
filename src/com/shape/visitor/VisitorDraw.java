package com.shape.visitor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Street;
import com.globalcitizen.model.viewpercy.GlobalCitizenUtils;
import com.globalcitizen.model.viewpercy.ThreadsController;

public class VisitorDraw extends JLabel {
	Map map;
	Rectangle heroSquare;
	int mapCreated = 0;
	int carRefreshRateCounter = 0;
	int carRefreshRate = 1;
	int carCounter = 0;
	ThreadsController threadsController;
	JLabel lblX;
	JPanel mapContainer;

	public ThreadsController getThreadsController() {
		return threadsController;
	}

	public void setThreadsController(ThreadsController threadsController) {
		this.threadsController = threadsController;
	}

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

	public VisitorDraw(Map map, ImageIcon background, JLabel lblX, JPanel mapContainer) {
		super(background);
		this.map = map;
		this.lblX = lblX;
		this.mapContainer = mapContainer;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// We draw the streets
		//if (mapCreated < 2) {
			if (map.getStreets().size() > 0) {
				for (Street street : map.getStreets()) {
					street.paintComponent(g, this);
				}
			}
			mapCreated++;
		//}
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
		if (carCounter == 100) {
			Random rand = new Random();
			int n = rand.nextInt(map.getStreets().size());
			// map.getStreets().get(n).createCar();
			map.getStreets().get(0).createCar(this.mapContainer);
			carCounter = 0;
		}
		carCounter++;
		map.moveCarsFromStreets(this);
		this.mapContainer.repaint();
	}

	public void touristWasHit() {

		threadsController.stopTheGame();
	}

	public void onDrawStreet(Graphics g, Street street) {
		System.out.println("Being called " + street.getStartingPoint().getX() + " " + street.getStartingPoint().getY());
		// super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// AffineTransform oldXForm = g2d.getTransform();
		switch (street.getDirection()) {
		case 2:
			g2d.setColor(Color.ORANGE);
			break;
		case 3:
			g2d.setColor(Color.GREEN);
			break;
		case 4:
			g2d.setColor(Color.BLUE);
			break;
		default:
			g2d.setColor(Color.WHITE);
			break;
		}

		Rectangle rectangle = new Rectangle(street.getStartingPoint().x, street.getStartingPoint().y,
				street.getStreetWidth(), street.getStreetHeight());
		/*
		 * g2d.rotate(Math.toRadians(street.getDirection()),
		 * street.getStartingPoint().getX(), street.getStartingPoint().getY());
		 */
		// Shape rotatedRect =
		// g2d.getTransform().createTransformedShape(rectangle);
		/*
		 * Rectangle tangle = new Rectangle(0, 0, radius, radius);
		 * g2d.translate(centerX, centerY);
		 */

		/*
		 * g2d.rotate(Math.toRadians(street.getDirection()), rectangle.getX() +
		 * rectangle.width / 2, rectangle.getY() + rectangle.height / 2);
		 */
		// Draw Streets
		g2d.fill(rectangle);
		// street.setLogicalForm(rotatedRect);
		// g2d.setTransform(oldXForm); // Restore transform

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
		Rectangle rect2 = new Rectangle(hero.getCurrentPosition().x, hero.getCurrentPosition().y,
				hero.getHorizontalUnits(), hero.getVerticalUnits());

		// g2d.draw(rect2);
		g2d.fill(rect2);
		if (this.lblX != null) {
			this.lblX.setLocation(hero.getCurrentPosition().x / 4, hero.getCurrentPosition().y / 4);
		}

		heroSquare = rect2;
		hero.setCurrentLogicalShape(g2d.getTransform().createTransformedShape(rect2));
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

	public boolean moveCar(Car car) {
		Point nextPosition = GlobalCitizenUtils.getNextLinePoint(car.getCurrentStreet(), car);
		// Check if we hit the tourist
		if (GlobalCitizenUtils.isTouristOnWay(map.getHero(), car, nextPosition)) {
			touristWasHit();
		} else {
			// We check if the car has connected streets to the one it is
			// currently navigating
			if (nextPosition.getX() > car.getCurrentStreet().getStartingPoint().getX()
					+ car.getCurrentStreet().getStreetWidth()
					|| nextPosition.getY() > car.getCurrentStreet().getStartingPoint().getY()
							+ car.getCurrentStreet().getStreetWidth()) {
				if (car.getCurrentStreet().getStreets().size() > 0) {
					Random rand = new Random();
					// We get the total streets number connected to that street
					// and generate a random number for the car to follow
					int n = rand.nextInt(car.getCurrentStreet().getStreets().size());
					// We position the car inside of the new street
					car.getCurrentStreet().getStreets().get(n).createCar(this.mapContainer);
				}
			} else {
				car.getCurrentPosition().x = nextPosition.x;
				car.getCurrentPosition().y = nextPosition.y;
			}
		}
		car.setLocation(nextPosition.x, nextPosition.y);
		return false;
	}

	public void onDrawCar(Graphics g, Car car) {
		System.out.println("moving car");
		car.setLocation(car.getCurrentPosition().x, car.getCurrentPosition().y);
	}

	public void addComponentListener(ComponentEvent componentAdapter) {
		this.repaint();
	}
}
