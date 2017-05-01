package com.globalcitizen.model.viewpercy;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.shape.visitor.VisitorDraw;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	public boolean tutorial;
	long speed = 10;
	VisitorDraw visitorDraw;
	boolean pauseGame;
	frmMain frmMain;
	int secondsCounter;
	boolean flicker;
	public boolean initialAnimationCompleted;
	int introCounter;
	int introInterval;
	List<String> say = new ArrayList<>();
	int durationBubble = 400;
	int bubbleCounter = 0;

	public void say(String add) {
		bubbleCounter = durationBubble;
		say.add(add);
	}

	// Constructor of ControlleurThread
	ThreadsController(VisitorDraw visitorDraw, frmMain frmMain) {
		this.visitorDraw = visitorDraw;
		this.frmMain = frmMain;
	}

	public void resetGame() {
		speed = 10;
		pauseGame = false;
		secondsCounter = 0;
		flicker = false;
		initialAnimationCompleted = false;
		introCounter = 0;

	}

	public void executeEverySecond() {
		if (frmMain.timeLeft > 0 && !frmMain.mapLevel1.isInLandmark) {

			frmMain.mapLevel1.setValueInProgressBar(frmMain.timeLeft - 1);
		}

	}

	public void speak() {
		// System.out.println("bubble Counter" + bubbleCounter);
		if (say.size() > 0) {
			if (bubbleCounter == durationBubble) {
				visitorDraw.lblTimeToExplore.setVisible(true);
				visitorDraw.text.setText("");
				visitorDraw.pinPoint.setVisible(false);
				if (say.get(0).startsWith("<html>")) {
					visitorDraw.text.setText(say.get(0));
					visitorDraw.text.setIcon(new ImageIcon(
							new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/white_b.png"))
									.getImage().getScaledInstance(visitorDraw.text.getWidth(),
											visitorDraw.text.getHeight(), Image.SCALE_DEFAULT)));
				} else {
					visitorDraw.text.setText("");
					visitorDraw.text.setIcon(new ImageIcon(
							new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/" + say.get(0)))
									.getImage().getScaledInstance(visitorDraw.text.getWidth(),
											visitorDraw.text.getHeight(), Image.SCALE_DEFAULT)));
				}

				/*
				 * visitorDraw.lblTimeToExplore.setBounds(visitorDraw.getHero().
				 * getCurrentPosition().x,
				 * visitorDraw.getHero().getCurrentPosition().y -
				 * visitorDraw.lblTimeToExplore.getHeight(),
				 * visitorDraw.lblTimeToExplore.getWidth(),
				 * visitorDraw.lblTimeToExplore.getHeight());
				 */

			} else {
				if (bubbleCounter == 0) {
					if (visitorDraw.lblTimeToExplore.isVisible()) {
						visitorDraw.pinPoint.setVisible(true);
						visitorDraw.lblTimeToExplore.setVisible(false);

						say.remove(0);
						visitorDraw.selectLandmark(visitorDraw.nextIncompleteLandmark());
						if (say.size() > 0) {
							bubbleCounter = durationBubble + 1;
						} else {
							tutorial = true;
						}
					}
				}
			}
			bubbleCounter--;
		}
	}

	public void GameOveranimation() {
		visitorDraw.gamePaused = true;
		int width = frmMain.frame.getSize().width;
		int height = frmMain.frame.getSize().height;
		if (width > 360) {
			width -= 5;
		}
		/*
		 * if (height > 685) { height -= 1; }
		 */
		// if (width > 360 || height > 685) {
		if (width > 360) {
			frmMain.frame.setSize(width, height);
			frmMain.panel_4.setVisible(flicker);
			flicker = !flicker;
		} else {
			frmMain.panel_4.setVisible(false);
			pauseGame();
			frmMain.recharge.setVisible(true);
			frmMain.arrow.setVisible(true);
		}

	}

	public void initialAnimation() {
		visitorDraw.pinPoint.setVisible(false);
		if (introInterval >= 4) {

			if (visitorDraw.getMap().getHero().getCurrentPosition().y < 152) {
				visitorDraw.moveHero(40);
			} else {
				if (!visitorDraw.lblTimeToExplore.isVisible()) {
					visitorDraw.lblTimeToExplore.setVisible(true);
					visitorDraw.pinPoint.setVisible(false);
					visitorDraw.text.setText("<html>Let's explore the city!</html>");
					visitorDraw.text.setIcon(new ImageIcon(
							new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/white_b.png"))
									.getImage().getScaledInstance(visitorDraw.text.getWidth(),
											visitorDraw.text.getHeight(), Image.SCALE_DEFAULT)));

				} else {
					if (introCounter <= 60) {
						introCounter++;
						if (introCounter == 30) {
							/*
							 * visitorDraw.text.setIcon(new ImageIcon(new
							 * ImageIcon( frmMain.class.getResource(
							 * "/com/globalcitizen/model/viewpercy/white_b.png")
							 * ) .getImage().getScaledInstance(visitorDraw.text.
							 * getWidth(), visitorDraw.text.getHeight(),
							 * Image.SCALE_DEFAULT)));
							 */
							visitorDraw.text
									.setText("<html>Take me to " + visitorDraw.currentLandmark.getName() + "</html>");
						}
					} else {
						visitorDraw.selectLandmark(visitorDraw.currentLandmark);
						visitorDraw.lblTimeToExplore.setVisible(false);
						initialAnimationCompleted = true;
					}
				}

			}
			introInterval = 0;
		} else {
			introInterval++;
		}

	}

	// Important part :
	public void run() {
		while (true) {
			speak();
			if (tutorial) {
				if (!initialAnimationCompleted) {
					initialAnimation();
					visitorDraw.mainUpdateProccess();
				} else {

					if (frmMain.timeLeft > 0) {
						visitorDraw.mainUpdateProccess();
					} else {
						GameOveranimation();
					}
					if (secondsCounter == 0) {
						executeEverySecond();
						secondsCounter = 10;
					}
					secondsCounter--;
				}
			}
			try {
				while (pauseGame) {
					sleep(speed);
				}
				sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// delay between each move
	public void pauseGame() {
		pauseGame = true;
	}

	public void resumeGame() {
		pauseGame = false;
	}

}
