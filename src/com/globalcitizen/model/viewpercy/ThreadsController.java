package com.globalcitizen.model.viewpercy;

import com.shape.visitor.Visitor;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	long speed = 15;
	Visitor visitorDraw;
	boolean pauseGame;

	// Constructor of ControlleurThread
	ThreadsController(Visitor visitorDraw) {
		this.visitorDraw = visitorDraw;
	}

	// Important part :
	public void run() {
		while (true) {
			visitorDraw.mainUpdateProccess();

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
