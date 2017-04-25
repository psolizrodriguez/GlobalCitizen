package com.shape.visitor;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Creature;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Street;
import com.globalcitizen.model.viewpercy.ThreadsController;

public abstract class Visitor extends JLabel {
	/**
	 * 
	 */

	public abstract void onDrawHero(Graphics g, Hero hero);

	public abstract void onDrawCar(Graphics g, Car car);

	private static final long serialVersionUID = 1L;
	private Creature hero;

	public Creature getHero() {
		return hero;
	}

	public void setHero(Creature hero) {
		this.hero = hero;
	}

	public Visitor(ImageIcon background) {
		super(background);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public abstract void onDrawStreet(Graphics g, Street street);

	public abstract void mainUpdateProccess();

	public abstract void setThreadsController(ThreadsController threadsController);

	public abstract void moveHero(int keycode);

	public abstract void setScrollPane(JScrollPane scrollPane);

	public abstract void drawLandmarks(List<Street> streets);

}
