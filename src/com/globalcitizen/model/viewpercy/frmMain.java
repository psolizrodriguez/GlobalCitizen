package com.globalcitizen.model.viewpercy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Street;
import com.shape.visitor.VisitorDraw;

public class frmMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain window = new frmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1260, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 13, 331, 550);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(30, 72, 266, 383);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblX.setBounds(100, 29, 14, 16);
		panel_4.add(lblX);

		// Code for adding the level 1
		// Map Load
		List<Street> listStreet = new ArrayList<>();
		listStreet.add(new Street(1, new Point(0, 0), 250));
		listStreet.add(new Street(3, new Point(0, 150), 250));
		listStreet.add(new Street(1, new Point(200, 0), 250));
		// listStreet.add(new Street(1, new Point(0, 450), 850));
		listStreet.add(new Street(2, new Point(0, 0), 200));
		listStreet.add(new Street(4, new Point(200, 0), 200));
		//listStreet.add(new Street(2, new Point(400, 0), 200));
		listStreet.add(new Street(3, new Point(200, 150), 250));
		/*
		 * listStreet.add(new Street(90, new Point(450, 0), 500));
		 * listStreet.add(new Street(90, new Point(650, 0), 550));
		 * listStreet.add(new Street(90, new Point(850, 0), 550));
		 */
		// Map Streets movement
		listStreet.get(0).getStreets().add(listStreet.get(1));

		// Links the window to the keyboardlistenner.

		ImageIcon background = new ImageIcon(this.getClass().getResource("background_edited.png"));

		// Code for adding level 1

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GREEN);
		panel_3.setBounds(895, 576, 331, 144);
		frame.getContentPane().add(panel_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 13, 871, 550);
		frame.getContentPane().add(scrollPane);

		JPanel panel_1 = new JPanel();
		Hero hero = new Hero(new Point(300, 10), panel_1);

		Map level_1 = new Map(listStreet, hero);
		scrollPane.setColumnHeaderView(panel_1);
		VisitorDraw label = new VisitorDraw(level_1, background, lblX, panel_1);
		label.setBounds(8, 5, 852, 501);
		panel_1.setPreferredSize(new Dimension(850, 1000));
		panel_1.setLayout(null);
		panel_1.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(12, 576, 871, 144);

		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/landmarks_background.png")));
		lblNewLabel.setBounds(12, 13, 847, 118);
		panel_2.add(lblNewLabel);
		panel_4.setSize(new Dimension(label.getWidth() / 4, label.getHeight() / 4));
		ThreadsController c = new ThreadsController(label);
		label.setThreadsController(c);
		// Let's start the game now..
		frame.addKeyListener((KeyListener) new KeyboardListener(label));
		label.repaint();
		c.start();
	}
}
