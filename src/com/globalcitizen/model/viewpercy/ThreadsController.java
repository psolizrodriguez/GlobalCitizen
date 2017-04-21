package com.globalcitizen.model.viewpercy;

import com.shape.visitor.VisitorDraw;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	long speed = 50;
	VisitorDraw visitorDraw;

	// Constructor of ControlleurThread
	ThreadsController(VisitorDraw visitorDraw) {
		this.visitorDraw = visitorDraw;
	}

	// Important part :
	public void run() {
		while (true) {
			visitorDraw.mainUpdateProccess();
			
			pauser();
		}
	}

	// delay between each move
	public void pauser() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Stops The Game
	public void stopTheGame() {

		while (true) {
			pauser();
		}
	}

}
