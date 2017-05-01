package com.globalcitizen.model.viewpercy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.shape.visitor.VisitorDraw;

public class KeyboardListener extends KeyAdapter {

	VisitorDraw visitorDraw;

	public KeyboardListener(VisitorDraw VisitorDraw) {
		this.visitorDraw = visitorDraw;
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 65) {
			Achivement achivement = new Achivement();
			achivement.setLocationRelativeTo(null);
			achivement.setVisible(true);

		}
		if (visitorDraw.threadsController.initialAnimationCompleted && visitorDraw.threadsController.say.size() == 0) {
			visitorDraw.moveHero(e.getKeyCode());
		}

	}

}
