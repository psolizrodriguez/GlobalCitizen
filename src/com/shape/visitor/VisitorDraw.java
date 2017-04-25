package com.shape.visitor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Landmark;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Street;
import com.globalcitizen.model.puzzle.PuzzleEx;
import com.globalcitizen.model.viewpercy.GlobalCitizenUtils;
import com.globalcitizen.model.viewpercy.MakeSound;
import com.globalcitizen.model.viewpercy.ThreadsController;
import com.globalcitizen.model.viewpercy.frmMain;

public class VisitorDraw extends Visitor {

	public void onDrawHero(Graphics g, Hero hero) {
		hero.setLocation(hero.getCurrentPosition().x, hero.getCurrentPosition().y);
		lblX.setLocation(hero.getCurrentPosition().x / 3, hero.getCurrentPosition().y / 3);
	}

	public void onDrawCar(Graphics g, Car car) {
		car.setLocation(car.getCurrentPosition().x, car.getCurrentPosition().y);
	}

	Map map;
	JLabel pinPoint;
	Rectangle heroSquare;
	int mapCreated = 0;
	int carRefreshRateCounter = 0;
	int carRefreshRate = 1;
	int carCounter = 0;
	ThreadsController threadsController;
	JLabel lblX;
	int currentMapPiece = 1;
	boolean heroMoved = false;
	boolean triggerAnimationUp;
	boolean triggerAnimationDown;
	boolean carsMoving;
	JScrollPane scrollPane;
	JLabel menuItems;
	int auxXMenulandmarks;
	JLabel minimapPin;
	boolean isInLandmark;
	frmMain frmMain;
	PuzzleEx puzzleEx;
	boolean musicStarted;

	MakeSound makeSound;

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

	public VisitorDraw(List<Street> streets, Point heroStartingPoint, ImageIcon background, JLabel lblX,
			JLabel menuItems, JLabel minimapPin, frmMain frmMain) {
		super(background);
		this.map = new Map(streets, new Hero(heroStartingPoint, this));
		this.lblX = lblX;
		lblX.setLocation(heroStartingPoint.x / 3, heroStartingPoint.y / 3);
		this.carsMoving = true;
		this.menuItems = menuItems;
		this.minimapPin = minimapPin;
		auxXMenulandmarks = 10;
		pinPoint = new JLabel();
		ImageIcon icon = new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/pin-animate.gif"));
		Image scaleImage = icon.getImage().getScaledInstance(150, 125, Image.SCALE_DEFAULT);
		pinPoint.setIcon(new ImageIcon(scaleImage));
		pinPoint.setBounds(0, 0, 150, 125);
		pinPoint.setOpaque(false);
		pinPoint.setVisible(false);
		this.add(pinPoint);

		this.frmMain = frmMain;
		makeSound = new MakeSound();

	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*
		 * // We draw the streets if (map.getStreets().size() > 0) { for (Street
		 * street : map.getStreets()) { street.paintComponent(g, this); } }
		 */
		// We draw the tourist
		if (heroMoved) {
			map.getHero().paintComponent(g, this);
			heroMoved = false;
		}

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

	public void playBackbroundMusic() {
		if (!musicStarted) {
			makeSound.playSound("strings3.wav");
			musicStarted = true;
		}
	}

	public void mainUpdateProccess() {
		if (carsMoving) {
			carCounter++;
			if (carCounter == 100) {
				Random rand = new Random();
				int n = rand.nextInt(map.getStreets().size());
				if (!map.getStreets().get(n).isLandmark()) {
					map.getStreets().get(n).createCar(this);
				}
				carCounter = 0;
				// playBackbroundMusic();
			}

			map.moveCarsFromStreets(this);
		}
		if (triggerAnimationDown) {
			System.out.println("calling trigger down" + scrollPane.getVerticalScrollBar().getValue() + " vs "
					+ scrollPane.getVerticalScrollBar().getMaximum());
			if (scrollPane.getVerticalScrollBar().getValue() < scrollPane.getVerticalScrollBar().getMaximum() / 2) {
				if (scrollPane.getVerticalScrollBar().getValue() + 50 >= scrollPane.getVerticalScrollBar().getMaximum()
						/ 2) {
					triggerAnimationDown = false;
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				} else {
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + 50);
				}

			}

		} else {
			if (triggerAnimationUp) {
				System.out.println("calling trigger up");
				if (scrollPane.getVerticalScrollBar().getValue() > 0) {
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - 50);
				} else {
					triggerAnimationUp = false;
					scrollPane.getVerticalScrollBar().setValue(0);
				}

			}
		}
		if (scrollPane != null) {
			scrollPane.getParent().repaint();
		}
	}

	public void touristWasHit() {
		carsMoving = false;
		// threadsController.stopTheGame();
	}

	public void drawLandmarks(List<Street> streets) {
		if (streets != null && streets.size() > 0) {
			for (Street street : streets) {
				if (street.isLandmark()) {
					Landmark landmark = (Landmark) street;
					JButton btnNewButton_1 = new JButton();
					btnNewButton_1.setBounds(auxXMenulandmarks, 23, 135, 100);
					menuItems.add(btnNewButton_1);
					landmark.setButton(btnNewButton_1);
					// Adding the door
					JLabel door = new JLabel();
					ImageIcon iconDoor = new ImageIcon(
							frmMain.class.getResource("/com/globalcitizen/model/viewpercy/door.gif"));
					Image scaleImageDoor = iconDoor.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
					door.setIcon(new ImageIcon(scaleImageDoor));
					door.setBounds(landmark.getStartingPoint().x, landmark.getStartingPoint().y, 50, 50);
					door.setOpaque(false);
					door.setVisible(true);
					this.add(door);
					landmark.setDoor(door);
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							moveLandMarkPoint(landmark);
						}
					});
					auxXMenulandmarks += 155;
				}

			}
		}
	}

	public void moveLandMarkPoint(Landmark landmark) {
		pinPoint.setLocation(landmark.getStartingPoint().x-55, landmark.getStartingPoint().y - 100);
		pinPoint.setVisible(true);

		minimapPin.setLocation(pinPoint.getLocation().x / 3, pinPoint.getLocation().y / 3);
		minimapPin.setVisible(true);
	}

	public void onDrawStreet(Graphics g, Street street) {
		Graphics2D g2d = (Graphics2D) g;
		/*
		 * switch (street.getDirection()) { case 2: g2d.setColor(Color.ORANGE);
		 * break; case 3: g2d.setColor(Color.GREEN); break; case 4:
		 * g2d.setColor(Color.BLUE); break; default: g2d.setColor(Color.WHITE);
		 * break; }
		 */

		if (street.isLandmark()) {

			g2d.setColor(Color.YELLOW);
			Rectangle rectangle = new Rectangle(street.getStartingPoint().x, street.getStartingPoint().y,
					street.getStreetWidth(), street.getStreetHeight());
			g2d.fill(rectangle);
		}
	}

	public void executeMinigame(Landmark landmark) {
		frmMain.panel_4.setVisible(false);
		frmMain.panel_5.setVisible(true);
		frmMain.panel_5.start();
		puzzleEx = new PuzzleEx(landmark, this);
		this.threadsController.pauseGame();
		this.setVisible(false);

	}

	public void closeMinigame(boolean completed) {
		frmMain.panel_4.setVisible(true);
		frmMain.panel_5.setVisible(false);
		frmMain.panel_5.stop();
		this.setVisible(true);
		this.threadsController.resumeGame();
	}

	public void moveHero(int keycode) {

		switch (keycode) {
		case 39: // -> Right
			heroMoved = getMap().getHero().moveRight(getMap().getStreets());
			break;
		case 38: // -> Top
			heroMoved = getMap().getHero().moveUp(getMap().getStreets());
			if (heroMoved) {
				int auxCurrentMapPiece = getMap().getHero().moveScroll(map.getStreets(), currentMapPiece);
				if (auxCurrentMapPiece != 0) {
					currentMapPiece = auxCurrentMapPiece;
					if (currentMapPiece == 1) {
						triggerAnimationUp = true;

					}
				}
			}
			break;

		case 37: // -> Left
			heroMoved = getMap().getHero().moveLeft(getMap().getStreets());
			break;

		case 40: // -> Bottom
			heroMoved = getMap().getHero().moveDown(getMap().getStreets());
			if (heroMoved) {
				int auxCurrentMapPiece = getMap().getHero().moveScroll(map.getStreets(), currentMapPiece);
				if (auxCurrentMapPiece != 0) {
					currentMapPiece = auxCurrentMapPiece;
					System.out.println("currentMapPiece = " + currentMapPiece);
					if (currentMapPiece == 2) {
						triggerAnimationDown = true;
					}
				}
			}
			break;

		default:
			break;
		}

		// Check if hero is inside of landmark
		Landmark auxLandmark = getMap().getHero().iscreatureInsideOfLandmark(getMap().getStreets());
		if (auxLandmark != null) {
			if (!isInLandmark) {
				System.out.println("is in Landmark");
				executeMinigame(auxLandmark);
				isInLandmark = true;
			}
		} else {
			if (isInLandmark) {
				isInLandmark = false;
				System.out.println("is out Landmark");
				closeMinigame(false);
			}

		}
		this.repaint();
	}

	public boolean moveCar(Car car) {
		Point nextPosition = GlobalCitizenUtils.getNextLinePoint(car.getCurrentStreet(), car);
		// Check if we hit the tourist
		if (GlobalCitizenUtils.isTouristOnWay(map.getHero(), car, nextPosition)) {
			touristWasHit();
		} else {
			boolean isCarOnWay = GlobalCitizenUtils.isCarOnWay(map.getStreets(), nextPosition, car);
			System.out.println("isCarOnWay = " + isCarOnWay);
			if (!isCarOnWay) {

				switch (car.getCurrentStreet().getDirection()) {
				case 2:
					if (nextPosition.getY() > car.getCurrentStreet().getStartingPoint().getY()
							+ car.getCurrentStreet().getStreetHeight()) {
						return false;

					}
					break;
				case 3:
					if (nextPosition.getX() + car.getWidth() < 0) {
						return false;

					}
					break;
				case 4:
					if (nextPosition.getX() - car.getHeight() < 0) {
						return false;

					}
					break;
				default:
					if (nextPosition.getX() > car.getCurrentStreet().getStartingPoint().getX()
							+ car.getCurrentStreet().getStreetWidth()) {
						return false;

					}
					break;
				}
				car.getCurrentPosition().x = nextPosition.x;
				car.getCurrentPosition().y = nextPosition.y;
			}
			return true;
		}
		return true;
	}

	public void addComponentListener(ComponentEvent componentAdapter) {
		this.repaint();
	}
}
