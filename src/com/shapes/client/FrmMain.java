package com.shapes.client;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FrmMain extends JFrame {

	public FrmMain() {
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new MyCanvas());
		this.setVisible(true);
	}

	public static void main(String[] args) {
		FrmMain frmMain = new FrmMain();
	}

}
