package com.globalcitizen.model.viewpercy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Map;
import com.globalcitizen.model.characters.Point;
import com.globalcitizen.model.characters.Street;
import com.shape.visitor.VisitorDraw;

public class Test1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 frame = new Test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Test1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(870, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Map Load
		List<Street> listStreet = new ArrayList<>();
		listStreet.add(new Street(0, new Point(0, 0), 850));
		listStreet.add(new Street(0, new Point(0, 150), 850));
		listStreet.add(new Street(0, new Point(0, 300), 850));
		listStreet.add(new Street(0, new Point(0, 450), 850));
		listStreet.add(new Street(90, new Point(50, 0), 500));
		listStreet.add(new Street(90, new Point(250, 0), 350));
		listStreet.add(new Street(90, new Point(450, 0), 500));
		listStreet.add(new Street(90, new Point(650, 0), 550));
		listStreet.add(new Street(90, new Point(850, 0), 550));

		Hero hero = new Hero(new Point(300, 300));

		Map level_1 = new Map(listStreet, hero);
		// Map

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 850, 500);
		contentPane.add(panel);
		panel.setLayout(null);

		// Links the window to the keyboardlistenner.

		ImageIcon background = new ImageIcon(this.getClass().getResource("background_edited.png"));
		VisitorDraw label = new VisitorDraw(level_1, background);
		label.setBounds(0, 0, 850, 500);
		panel.add(label);
		this.setLocationRelativeTo(null);

		ThreadsController c = new ThreadsController(label);
		// Let's start the game now..
		this.addKeyListener((KeyListener) new KeyboardListener(label));
		label.repaint();
		c.start();
	}
}
