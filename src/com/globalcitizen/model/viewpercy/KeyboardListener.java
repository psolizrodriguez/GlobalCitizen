package com.globalcitizen.model.viewpercy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.shape.visitor.Visitor;

public class KeyboardListener extends KeyAdapter {

	Visitor visitorDraw;

	public KeyboardListener(Visitor visitorDraw) {
		this.visitorDraw = visitorDraw;
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 65){
			Achivement achivement = new Achivement();
			achivement.setLocationRelativeTo(null);
			achivement.setVisible(true);
			
		}
		visitorDraw.moveHero(e.getKeyCode());
	}

}
