package com.shape.visitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Landmark;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Street;
import com.globalcitizen.model.puzzle.PuzzleEx;
import com.globalcitizen.model.viewpercy.GlobalCitizenConstants;
import com.globalcitizen.model.viewpercy.GlobalCitizenUtils;
import com.globalcitizen.model.viewpercy.MakeSound;
import com.globalcitizen.model.viewpercy.ThreadsController;
import com.globalcitizen.model.viewpercy.frmMain;

public class VisitorDraw extends Visitor {

	public void onDrawHero(Graphics g, Hero hero) {
		hero.setLocation(hero.getCurrentPosition().x, hero.getCurrentPosition().y);
		lblX.setLocation(hero.getCurrentPosition().x / 3 - 5, hero.getCurrentPosition().y / 3 - 5);
	}

	public void onDrawCar(Graphics g, Car car) {
		car.setLocation(car.getCurrentPosition().x, car.getCurrentPosition().y);
	}

	Map map;
	public JLabel pinPoint;
	Rectangle heroSquare;
	int mapCreated = 0;
	int carRefreshRateCounter = 0;
	int carRefreshRate = 1;
	int carCounter = 0;
	public ThreadsController threadsController;
	JLabel lblX;
	public int currentMapPiece = 1;
	boolean heroMoved = false;
	boolean triggerAnimationUp;
	boolean triggerAnimationDown;
	boolean carsMoving;
	JScrollPane scrollPane;
	JLabel menuItems;
	int auxXMenulandmarks;
	JLabel minimapPin;
	public boolean isInLandmark;
	frmMain frmMain;
	public PuzzleEx puzzleEx;
	boolean musicStarted;
	JLabel camera;
	JLabel backgroundPicture;
	public boolean gamePaused;
	public JLabel lblTimeToExplore;
	boolean showingAnimationAchivement;
	int previousBatterryIcon;
	List<Landmark> listLandmarks;
	public JLabel text;
	JLabel arrowDown, arrowUp;

	MakeSound makeSound;
	public Landmark currentLandmark;

	public void selectLandmark(Landmark landmark) {
		if (landmark != null) {
			if (currentLandmark != null) {
				clearLandmark(currentLandmark);
			}
			currentLandmark = landmark;
			currentLandmark.getButton().setBorder((new LineBorder(Color.GREEN, 5)));
			moveLandMarkPoint(landmark);
			frmMain.select.setVisible(true);
			frmMain.select.setBounds(currentLandmark.getButton().getLocation().x + 20, frmMain.select.getLocation().y,
					frmMain.select.getWidth(), frmMain.select.getHeight());
			arrowDown.setVisible(false);
			arrowUp.setVisible(false);
			if (landmark.getStartingPoint().y > this.getHeight() / 2) {
				if (currentMapPiece == 1) {
					arrowDown.setVisible(true);
					arrowUp.setVisible(false);
				}
			} else {
				if (currentMapPiece == 2) {
					arrowDown.setVisible(false);
					arrowUp.setVisible(true);
				}
			}
		} else {
			threadsController.say(
					"<html>Congratulation! You finished the first level!<br>Dedicated to IR and LS!<br>Special Thanks to Tyree and Dr. Hayward!<br>Contact me at percy.soliz.rodriguez@gmail.com for more info<br>Thanks for playing Global Citizen 1.0!</html>");
			carsMoving = false;
			/*
			 * JOptionPane.showMessageDialog(frmMain.frame,
			 * "Congratulation! You finished the first level!/n Dedicated to LS!/nSpecial Thanks to Tyree!"
			 * , "Finished", JOptionPane.INFORMATION_MESSAGE);
			 */
		}
	}

	public void clearLandmark(Landmark landmark) {
		if (currentLandmark.getButton() != null) {
			currentLandmark.getButton().setBorder(null);
		}

	}

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
		lblTimeToExplore = new JLabel();
		lblTimeToExplore.setBounds(50, 50, 550, 550);
		ImageIcon iconExplore = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/bubble_5.png"));
		Image scaleIconExplore = iconExplore.getImage().getScaledInstance(lblTimeToExplore.getWidth(),
				lblTimeToExplore.getHeight(), Image.SCALE_DEFAULT);
		lblTimeToExplore.setIcon(new ImageIcon(scaleIconExplore));
		lblTimeToExplore.setVisible(false);
		text = new JLabel("<html>Let's explore the city!</html>");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 15));
		text.setBounds(220, 22, 250, 300);
		/*
		 * text.setIcon(new ImageIcon( new ImageIcon(frmMain.class.getResource(
		 * "/com/globalcitizen/model/viewpercy/tuto_01.png"))
		 * .getImage().getScaledInstance(text.getWidth(), text.getHeight(),
		 * Image.SCALE_DEFAULT)));
		 */
		text.setIconTextGap(-text.getWidth());
		text.setOpaque(false);
		text.setLayout(null);

		lblTimeToExplore.add(text);
		pinPoint = new JLabel();
		ImageIcon icon = new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/arrowkk2.gif"));
		Image scaleImage = icon.getImage().getScaledInstance(150, 125, Image.SCALE_DEFAULT);
		pinPoint.setIcon(new ImageIcon(scaleImage));
		pinPoint.setBounds(0, 0, 150, 125);
		pinPoint.setOpaque(false);
		pinPoint.setVisible(false);
		this.add(pinPoint);

		this.frmMain = frmMain;
		makeSound = new MakeSound();
		camera = new JLabel();
		ImageIcon iconCamera = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/camera.png"));
		Image scaleIconCamera = iconCamera.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);

		camera.setIcon(new ImageIcon(scaleIconCamera));

		JButton close = new JButton();
		ImageIcon iconClose = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/close_button.png"));
		Image scaleiconClose = iconClose.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		close.setIcon(new ImageIcon(scaleiconClose));
		close.setBounds(432, 120, 23, 23);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMinigame(true);
			}
		});
		camera.add(close);
		camera.setVisible(false);
		frmMain.panel_1.add(camera);

		backgroundPicture = new JLabel();
		backgroundPicture.setBounds(0, 0, 850, 650);
		frmMain.panel_1.add(backgroundPicture);
		backgroundPicture.setVisible(false);

		this.add(lblTimeToExplore);

		arrowDown = new JLabel();
		arrowDown.setBounds(390, 580, 70, 100);
		arrowDown.setIcon(new ImageIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/arrowdown.gif")).getImage()
						.getScaledInstance(arrowDown.getWidth(), arrowDown.getHeight(), Image.SCALE_DEFAULT)));
		arrowDown.setVisible(false);
		this.add(arrowDown);
		arrowUp = new JLabel();
		arrowUp.setBounds(390, 620, 70, 100);
		arrowUp.setIcon(
				new ImageIcon(new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/arrowup.gif"))
						.getImage().getScaledInstance(arrowUp.getWidth(), arrowUp.getHeight(), Image.SCALE_DEFAULT)));
		arrowUp.setVisible(false);
		this.add(arrowUp);
	}

	public void reloadBackgroundImage(Landmark landmark) {
		ImageIcon iconObjective = new ImageIcon(ClassLoader.getSystemResource(landmark.getLandmarkImage() + ".jpg"));
		Image scaleIconObjective = iconObjective.getImage().getScaledInstance(backgroundPicture.getWidth(),
				backgroundPicture.getHeight(), Image.SCALE_DEFAULT);
		backgroundPicture.setIcon(new ImageIcon(scaleIconObjective));
		backgroundPicture.setVisible(true);
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
		// We draw the touristd
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
		if (showingAnimationAchivement) {
			fireAnimationLandmark();
		} else {
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
		}
		if (triggerAnimationDown) {
			System.out.println("calling trigger down" + scrollPane.getVerticalScrollBar().getValue() + " vs "
					+ scrollPane.getVerticalScrollBar().getMaximum());
			if (scrollPane.getVerticalScrollBar().getValue() < scrollPane.getVerticalScrollBar().getMaximum() / 2) {
				if (scrollPane.getVerticalScrollBar().getValue() + 40 >= scrollPane.getVerticalScrollBar().getMaximum()
						/ 2) {
					triggerAnimationDown = false;
					arrowDown.setVisible(false);
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					lblTimeToExplore.setBounds(50, 650, 550, 550);
				} else {
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + 40);
				}

			}

		} else {
			if (triggerAnimationUp) {
				System.out.println("calling trigger up");
				if (scrollPane.getVerticalScrollBar().getValue() > 0) {
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - 40);
				} else {
					triggerAnimationUp = false;
					arrowUp.setVisible(false);
					scrollPane.getVerticalScrollBar().setValue(0);
					lblTimeToExplore.setBounds(50, 50, 550, 550);
				}

			}
		}
		if (scrollPane != null) {
			scrollPane.getParent().repaint();
		}
	}

	public void touristWasHit() {
		gamePaused = false;
		// threadsController.stopTheGame();
	}

	public void drawLandmarks(List<Street> streets) {
		if (streets != null && streets.size() > 0) {
			int width = (int) Math.round(menuItems.getWidth() * 0.1475);
			int height = (int) Math.round(menuItems.getHeight() * 0.65);
			listLandmarks = new ArrayList<>();
			for (Street street : streets) {
				if (street.isLandmark()) {

					Landmark landmark = (Landmark) street;
					listLandmarks.add(landmark);

					if (!landmark.isStartingPointTourist()) {
						JButton btnNewButton_1 = new JButton();
						// Put images into the buttons
						btnNewButton_1.setBounds(auxXMenulandmarks, 26, width, height);
						ImageIcon iconObjective = new ImageIcon(
								ClassLoader.getSystemResource(landmark.getLandmarkImage() + "_false.jpg"));

						Image scaleIconObjective = iconObjective.getImage().getScaledInstance(btnNewButton_1.getWidth(),
								btnNewButton_1.getHeight(), Image.SCALE_DEFAULT);
						btnNewButton_1.setIcon(new ImageIcon(scaleIconObjective));
						// Putting images

						menuItems.add(btnNewButton_1);
						btnNewButton_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								selectLandmark(landmark);
							}
						});
						auxXMenulandmarks += width + 15;
						landmark.setButton(btnNewButton_1);
					}

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
					if (currentLandmark == null && !landmark.isCompleted()) {
						currentLandmark = landmark;
					}
				}

			}
		}
	}

	public void moveLandMarkPoint(Landmark landmark) {
		pinPoint.setLocation(landmark.getStartingPoint().x - 55, landmark.getStartingPoint().y - 100);
		pinPoint.setVisible(true);

		minimapPin.setLocation(pinPoint.getLocation().x / 3 + 5, pinPoint.getLocation().y / 3);
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
		selectLandmark(landmark);
		frmMain.panel_4.setVisible(false);
		frmMain.panel_5.setVisible(true);
		frmMain.panel_5.start(this);
		// puzzleEx = new PuzzleEx();
		gamePaused = true;
		puzzleEx = new PuzzleEx();
		puzzleEx.setBounds(37, 60, 300, 210);
		camera.add(puzzleEx);
		puzzleEx.loadImage(landmark, this);
		this.threadsController.pauseGame();
		if (currentMapPiece == 2) {
			backgroundPicture.setBounds(0, 600, 850, 650);
			camera.setBounds(174, 782, 500, 300);
		} else {
			backgroundPicture.setBounds(0, 0, 850, 650);
			camera.setBounds(174, 182, 500, 300);
		}
		camera.setVisible(true);
		reloadBackgroundImage(landmark);
		this.setVisible(false);

	}

	public void fireAnimationLandmark() {
		if (!puzzleEx.getLandmark().isCompleted() && puzzleEx.getLandmark().getButton().getWidth() > 5) {
			puzzleEx.getLandmark().getButton().setBounds(puzzleEx.getLandmark().getButton().getX(),
					puzzleEx.getLandmark().getButton().getY(), puzzleEx.getLandmark().getButton().getWidth() - 1,
					puzzleEx.getLandmark().getButton().getHeight());
		} else {
			if (!puzzleEx.getLandmark().isCompleted()) {
				ImageIcon iconObjective = new ImageIcon(
						ClassLoader.getSystemResource(puzzleEx.getLandmark().getLandmarkImage() + ".jpg"));
				Image scaleIconObjective = iconObjective.getImage().getScaledInstance(135, 100, Image.SCALE_DEFAULT);
				puzzleEx.getLandmark().getButton().setIcon(new ImageIcon(scaleIconObjective));
				puzzleEx.getLandmark().setCompleted(true);
			} else {

				if (puzzleEx.getLandmark().getButton().getWidth() < 135) {
					puzzleEx.getLandmark().getButton().setBounds(puzzleEx.getLandmark().getButton().getX(),
							puzzleEx.getLandmark().getButton().getY(),
							puzzleEx.getLandmark().getButton().getWidth() + 1,
							puzzleEx.getLandmark().getButton().getHeight());
				} else {
					showingAnimationAchivement = false;
				}
			}
		}

	}

	public void closeMinigame(boolean completed) {
		frmMain.panel_4.setVisible(true);
		frmMain.panel_5.setVisible(false);
		if (puzzleEx != null) {
			camera.remove(puzzleEx);
		}
		camera.setVisible(false);
		// puzzleEx = null;
		this.threadsController.resumeGame();
		gamePaused = false;
		this.setVisible(true);
		backgroundPicture.setVisible(false);
		if (completed) {
			// Trigger animation
			gotLandmark();

		}
	}

	public Landmark nextIncompleteLandmark() {
		for (Street street : map.getStreets()) {
			if (street.isLandmark() && !street.isStartingPointTourist()) {
				Landmark land = (Landmark) street;
				if (!land.isCompleted() && !land.isStartingPointTourist()) {
					return land;
				}
			}
		}
		return null;
	}

	public void gotLandmark() {
		// currentLandmark.setCompleted(true);
		this.showingAnimationAchivement = true;
		arrowDown.setVisible(false);
		arrowUp.setVisible(false);
		Landmark landmark = nextIncompleteLandmark();
		if (landmark != null) {
			selectLandmark(landmark);
			threadsController.say("tenor.gif");

		}
	}

	public void moveHero(int keycode) {
		if (!gamePaused) {

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
						System.out.println("currentMapPiece = " + currentMapPiece);
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
					if (!auxLandmark.isStartingPointTourist()) {
						selectLandmark(auxLandmark);
						executeMinigame(auxLandmark);
					}
					isInLandmark = true;
				}
				setValueInProgressBar(GlobalCitizenConstants.TIME_INTERVAL);

			} else {
				if (isInLandmark) {
					isInLandmark = false;
					System.out.println("is out Landmark");
					closeMinigame(false);
				}

			}
			this.repaint();
		}
	}

	public void setValueInProgressBar(int timeLeft) {
		if (timeLeft >= 0) {

			frmMain.progressBar.setValue(timeLeft);
			frmMain.powerBar.setText((timeLeft * 100 / GlobalCitizenConstants.TIME_INTERVAL) + " %");
			frmMain.timeLeft = timeLeft;
			int auxIcon = (int) ((Math.round(
					(timeLeft * 1.0 / GlobalCitizenConstants.TIME_INTERVAL * 1.0) * frmMain.battery_status.length)
					* 1));
			if (auxIcon == frmMain.battery_status.length) {
				auxIcon = frmMain.battery_status.length - 1;
			}
			if (auxIcon == 0) {
				if (timeLeft > 0) {
					auxIcon = 1;
				}
			}

			// System.out.println(auxIcon);
			if (auxIcon != previousBatterryIcon) {
				frmMain.battery.setIcon(new ImageIcon(frmMain.battery_status[auxIcon].getImage().getScaledInstance(
						frmMain.battery.getWidth(), frmMain.battery.getHeight(), Image.SCALE_DEFAULT)));
				previousBatterryIcon = auxIcon;
			}
		}
	}

	public boolean moveCar(Car car) {
		Point nextPosition = GlobalCitizenUtils.getNextLinePoint(car.getCurrentStreet(), car);
		// Check if we hit the tourist
		if (GlobalCitizenUtils.isTouristOnWay(map.getHero(), car, nextPosition)) {
			touristWasHit();
		} else {
			boolean isCarOnWay = GlobalCitizenUtils.isCarOnWay(map.getStreets(), nextPosition, car);
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
