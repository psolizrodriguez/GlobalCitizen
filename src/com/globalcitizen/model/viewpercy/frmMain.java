package com.globalcitizen.model.viewpercy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.globalcitizen.model.characters.Creature;
import com.globalcitizen.model.characters.Landmark;
import com.globalcitizen.model.characters.Street;
import com.shape.visitor.VisitorDraw;

public class frmMain {

	public JFrame frame;
	JScrollPane scrollPane;
	VisitorDraw mapLevel1;
	Creature hero;
	public JPanel panel_2;
	public DigitalWatch panel_5;
	public JPanel panel_4;
	public JPanel panel_1;
	public JLabel powerBar;
	public JProgressBar progressBar;
	public int timeLeft;
	private JLabel mobile;
	private JLabel lblGlobalCitizen;
	public JButton recharge;

	public Creature getHero() {
		return hero;
	}

	public void setHero(Creature hero) {
		this.hero = hero;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

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

	public void drawLabelsObjectives(List<Street> listStreet) {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1241, 881);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 13, 334, 653);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBounds(25, 131, 266, 383);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblX = new JLabel();

		lblX.setBounds(100, 29, 20, 20);
		ImageIcon iconMinimapX = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/head_crop.png"));
		Image scaleImageMinimapX = iconMinimapX.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		lblX.setIcon(new ImageIcon(scaleImageMinimapX));
		panel_4.add(lblX);

		// Code for adding the level 1
		// Map Load
		List<Street> listStreet = new ArrayList<>();
		listStreet.add(new Street(1, new Point(0, 0), 850));
		listStreet.add(new Street(3, new Point(0, 150), 850));
		listStreet.add(new Street(1, new Point(0, 300), 850));
		listStreet.add(new Street(3, new Point(0, 450), 850));
		listStreet.add(new Street(1, new Point(0, 600), 850));
		listStreet.add(new Street(3, new Point(0, 750), 850));
		listStreet.add(new Street(1, new Point(0, 900), 850));
		listStreet.add(new Street(1, new Point(0, 1050), 850));
		listStreet.add(new Street(1, new Point(0, 1200), 850));
		// listStreet.add(new Street(1, new Point(0, 450), 850));
		listStreet.add(new Street(2, new Point(0, 0), 1250));
		listStreet.add(new Street(4, new Point(200, 0), 1250));
		listStreet.add(new Street(2, new Point(400, 0), 1250));
		listStreet.add(new Street(4, new Point(600, 0), 1250));
		listStreet.add(new Street(2, new Point(800, 0), 1250));

		listStreet.get(4).setHasToChangeView(true);

		ImageIcon zooIcon = new ImageIcon(this.getClass().getResource("philiZooIcon.png"));
		ImageIcon zoo = new ImageIcon(this.getClass().getResource("philiZoo.jpg"));
		// Now we add the landmarks
		listStreet.add(new Landmark("Philadelphia Zoo", 2, new Point(500, 250), 100, zooIcon, zoo, 60, 30,
				"objective_4.jpg", false));
		listStreet.add(new Landmark("Rocky Statue", 2, new Point(100, 850), 100, zooIcon, zoo, 60, 30,
				"objective_5.jpg", false));
		listStreet.add(new Landmark("Liberty Bell", 2, new Point(700, 400), 100, zooIcon, zoo, 60, 30,
				"objective_3.jpg", false));
		listStreet.add(new Landmark("Frankiln Institute", 2, new Point(700, 1000), 100, zooIcon, zoo, 60, 30,
				"objective_1.jpg", false));
		listStreet.add(new Landmark("Rhodium Museum", 2, new Point(500, 700), 100, zooIcon, zoo, 60, 30,
				"objective_6.jpg", false));
		listStreet.add(new Landmark("Independance Hall", 2, new Point(300, 550), 100, zooIcon, zoo, 60, 30,
				"objective_2.jpg", false));
		listStreet.add(new Landmark("Independance Hall", 2, new Point(300, 550), 100, zooIcon, zoo, 60, 30,
				"objective_2.jpg", false));
		listStreet.add(new Landmark("", 2, new Point(100, 100), 100, zooIcon, zoo, 60, 30, "objective_2.jpg", true));

		// Code for adding level 1

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GREEN);
		panel_3.setBounds(980, 680, 227, 144);
		frame.getContentPane().add(panel_3);

		KeyboardFocusManager keyManager;

		keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					mapLevel1.moveHero(e.getKeyCode());
					return true;
				}
				return false;
			}

		});

		panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(12, 680, 924, 144);

		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/film_strip.png")));
		lblNewLabel.setBounds(0, 0, 923, 144);
		panel_2.add(lblNewLabel);

		JLabel minimapPin = new JLabel("");

		ImageIcon iconMinimapPin = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/pin-animate.gif"));
		Image scaleImageMinimapPin = iconMinimapPin.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		minimapPin.setIcon(new ImageIcon(scaleImageMinimapPin));
		minimapPin.setBounds(0, 0, 50, 55);
		panel_4.add(minimapPin);
		minimapPin.setVisible(false);

		panel_5 = new DigitalWatch();
		panel_5.setBounds(25, 131, 283, 416);
		panel.add(panel_5);
		panel_5.setVisible(false);

		panel_1 = new JPanel();

		// test
		ImageIcon background = new ImageIcon(this.getClass().getResource("background_edited.png"));
		mapLevel1 = new VisitorDraw(listStreet, new Point(110, 100), background, lblX, lblNewLabel, minimapPin, this);
		mapLevel1.setIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/background_v3.png")));
		mapLevel1.setBounds(0, 0, 850, 1250);
		panel_1.setPreferredSize(new Dimension(850, 1250));
		panel_1.setLayout(null);
		panel_1.add(mapLevel1);

		scrollPane = new JScrollPane(panel_1);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(355, 13, 852, 653);
		frame.getContentPane().add(scrollPane);
		listStreet.get(1).createCar(mapLevel1);
		// listStreet.get(1).createCar(mapLevel1);
		setHero(mapLevel1.getHero());

		panel_4.setSize(new Dimension(mapLevel1.getWidth() / 3, mapLevel1.getHeight() / 3));

		powerBar = new JLabel("About 30 secods left");
		powerBar.setHorizontalAlignment(SwingConstants.CENTER);
		powerBar.setBounds(72, 46, 179, 21);
		panel.add(powerBar);

		progressBar = new JProgressBar();
		progressBar.setBounds(72, 46, 179, 21);
		progressBar.setMinimum(0);
		progressBar.setMaximum(GlobalCitizenConstants.TIME_INTERVAL);
		panel.add(progressBar);

		mobile = new JLabel("");
		mobile.setBounds(0, 80, 331, 528);

		ImageIcon iconmobile = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/mobile.png"));
		Image scalemobile = iconmobile.getImage().getScaledInstance(mobile.getWidth(), mobile.getHeight(),
				Image.SCALE_DEFAULT);
		mobile.setIcon(new ImageIcon(scalemobile));
		panel.add(mobile);

		recharge = new JButton();
		recharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				frmMain window = new frmMain();
				window.frame.setVisible(true);
			}
		});
		ImageIcon iconRecharge = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/recharge.png"));
		recharge.setBounds(263, 13, 52, 54);
		Image scaleIconRecharge = iconRecharge.getImage().getScaledInstance(recharge.getWidth(), recharge.getHeight(),
				Image.SCALE_DEFAULT);
		recharge.setIcon(new ImageIcon(scaleIconRecharge));

		panel.add(recharge);
		recharge.setVisible(false);

		lblGlobalCitizen = new JLabel("GLOBAL CITIZEN");
		lblGlobalCitizen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGlobalCitizen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGlobalCitizen.setBounds(72, 13, 179, 20);
		panel.add(lblGlobalCitizen);

		// Code for drawing Streets
		mapLevel1.drawLandmarks(listStreet);

		ThreadsController c = new ThreadsController(mapLevel1, this);
		mapLevel1.setThreadsController(c);
		// Let's start the game now..
		mapLevel1.setScrollPane(scrollPane);

		mapLevel1.repaint();
		timeLeft = GlobalCitizenConstants.TIME_INTERVAL;
		c.start();

	}
}
