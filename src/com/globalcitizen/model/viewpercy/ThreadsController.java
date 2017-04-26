package com.globalcitizen.model.viewpercy;

import com.shape.visitor.VisitorDraw;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	long speed = 100;
	VisitorDraw visitorDraw;
	boolean pauseGame;
	frmMain frmMain;
	int secondsCounter;
	boolean flicker;
	boolean initialAnimationCompleted;
	int introCounter;

	// Constructor of ControlleurThread
	ThreadsController(VisitorDraw visitorDraw, frmMain frmMain) {
		this.visitorDraw = visitorDraw;
		this.frmMain = frmMain;
	}

	public void executeEverySecond() {
		if (frmMain.timeLeft > 0) {
			frmMain.timeLeft--;
			frmMain.progressBar.setValue(frmMain.timeLeft);
			frmMain.powerBar
					.setText("About " + (frmMain.timeLeft * 100 / frmMain.progressBar.getMaximum()) + " % left!");
		}

	}

	public void GameOveranimation() {
		visitorDraw.gamePaused = true;
		int width = frmMain.frame.getSize().width;
		int height = frmMain.frame.getSize().height;
		if (width > 360) {
			width -= 5;
		}
		if (height > 705) {
			height -= 1;
		}
		if (width > 360 || height > 705) {
			frmMain.frame.setSize(width, height);
			frmMain.panel_4.setVisible(flicker);
			flicker = !flicker;
		} else {
			frmMain.panel_4.setVisible(false);
			pauseGame();
			frmMain.recharge.setVisible(true);
		}

	}

	public void initialAnimation() {
		if (visitorDraw.getMap().getHero().getCurrentPosition().y < 152) {
			visitorDraw.moveHero(40);
		} else {
			if (!visitorDraw.lblTimeToExplore.isVisible()) {
				visitorDraw.lblTimeToExplore.setVisible(true);

			} else {
				if (introCounter < 10) {
					introCounter++;
				} else {
					visitorDraw.lblTimeToExplore.setVisible(false);
					initialAnimationCompleted = true;
					speed = 10;
				}

			}

		}

	}

	// Important part :
	public void run() {
		while (true) {
			if (!initialAnimationCompleted) {
				initialAnimation();
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
