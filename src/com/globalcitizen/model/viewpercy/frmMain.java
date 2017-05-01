package com.globalcitizen.model.viewpercy;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.border.LineBorder;

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
	private JLabel lblNewLabel_1;
	ThreadsController c;
	public JLabel battery;
	public JPanel panel;

	public final ImageIcon[] battery_status = new ImageIcon[] {
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/b.gif")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/b_1.gif")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/b_2.gif")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/b_3.gif")),
			new ImageIcon(this.getClass().getResource("/com/globalcitizen/model/viewpercy/b_4.gif")) };
	public JLabel arrow;
	private JLabel lblMyPhone;
	private JLabel iPhone_background;
	public JLabel select;

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
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() {
		 */
		try {
			frmMain window = new frmMain();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * } });
		 */
	}

	/**
	 * Create the application.
	 */
	public frmMain() {
		initialize();
	}

	public void drawLabelsObjectives(List<Street> listStreet) {

	}

	public void restartGame() {
		panel_4.setVisible(true);
		mapLevel1.getMap().getHero().setCurrentPosition(new Point(110, 100));
		mapLevel1.getMap().getHero().setLocation(110, 100);
		mapLevel1.text.setText("<html>Let's explore the city!</html>");
		mapLevel1.currentMapPiece = 1;
		scrollPane.getVerticalScrollBar().setValue(0);
		frame.setSize(1241, 881);
		mapLevel1.gamePaused = false;
		recharge.setVisible(false);
		arrow.setVisible(false);

		c.resetGame();
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

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 334, 629);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBounds(25, 100, 283, 417);
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
				"objective_4", false));

		/*
		 * listStreet.add(new Landmark("Philadelphia Zoo", 2, new Point(150,
		 * 150), 100, zooIcon, zoo, 60, 30, "objective_4", false));
		 */

		listStreet.add(
				new Landmark("Liberty Bell", 2, new Point(700, 400), 100, zooIcon, zoo, 60, 30, "objective_3", false));
		listStreet.add(new Landmark("Independance Hall", 2, new Point(300, 550), 100, zooIcon, zoo, 60, 30,
				"objective_2", false));
		listStreet.add(new Landmark("Franklin Institute", 2, new Point(700, 1000), 100, zooIcon, zoo, 60, 30,
				"objective_1", false));
		listStreet.add(
				new Landmark("Rocky Statue", 2, new Point(100, 850), 100, zooIcon, zoo, 60, 30, "objective_5", false));
		listStreet.add(new Landmark("Rhodium Museum", 2, new Point(500, 700), 100, zooIcon, zoo, 60, 30, "objective_6",
				false));

		listStreet.add(new Landmark("", 2, new Point(100, 100), 100, zooIcon, zoo, 60, 30, "objective_2.jpg", true));

		// Code for adding level 1

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(12, 647, 334, 176);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(12, 142, 299, 10);
		panel_3.add(progressBar);
		progressBar.setMinimum(0);
		progressBar.setMaximum(GlobalCitizenConstants.TIME_INTERVAL);

		powerBar = new JLabel("100%");
		powerBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		powerBar.setBounds(211, 54, 98, 75);
		panel_3.add(powerBar);
		powerBar.setHorizontalAlignment(SwingConstants.LEFT);

		battery = new JLabel();
		battery.setBounds(161, 54, 150, 75);
		panel_3.add(battery);
		battery.setIcon(new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/b_4.gif")));

		lblMyPhone = new JLabel("My Phone");
		lblMyPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMyPhone.setBounds(12, 13, 310, 16);
		panel_3.add(lblMyPhone);

		iPhone_background = new JLabel("");
		iPhone_background.setBounds(12, 54, 137, 75);
		iPhone_background.setIcon(new ImageIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/background_bar.png"))
						.getImage().getScaledInstance(iPhone_background.getWidth(), iPhone_background.getHeight(),
								Image.SCALE_DEFAULT)));

		panel_3.add(iPhone_background);

		KeyboardFocusManager keyManager;

		keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (mapLevel1.threadsController.initialAnimationCompleted
							&& mapLevel1.threadsController.say.size() == 0) {
						mapLevel1.moveHero(e.getKeyCode());
					}
					return true;
				}
				return false;
			}

		});

		panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(355, 10, 852, 150);

		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 857, 150);
		lblNewLabel.setIcon(new ImageIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/output_SgNnrV.gif"))
						.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
								Image.SCALE_DEFAULT)));

		panel_2.add(lblNewLabel);

		JLabel minimapPin = new JLabel("");
		minimapPin.setBounds(0, 0, 40, 40);
		ImageIcon iconMinimapPin = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/arrowkk2.gif"));
		Image scaleImageMinimapPin = iconMinimapPin.getImage().getScaledInstance(minimapPin.getWidth(),
				minimapPin.getHeight(), Image.SCALE_DEFAULT);
		minimapPin.setIcon(new ImageIcon(scaleImageMinimapPin));

		panel_4.add(minimapPin);
		minimapPin.setVisible(false);

		panel_5 = new DigitalWatch(mapLevel1);
		panel_5.setBounds(25, 100, 283, 416);
		panel.add(panel_5);
		panel_5.setVisible(false);

		panel_1 = new JPanel();

		// test
		ImageIcon background = new ImageIcon(this.getClass().getResource("Game Background_v3.png"));
		mapLevel1 = new VisitorDraw(listStreet, GlobalCitizenConstants.INITIAL_HERO_RESPAWN, background, lblX,
				lblNewLabel, minimapPin, this);

		mapLevel1.setIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/Game Background_v3.png")));
		mapLevel1.setBounds(0, 0, 850, 1250);
		panel_1.setPreferredSize(new Dimension(850, 1250));
		panel_1.setLayout(null);
		panel_1.add(mapLevel1);

		scrollPane = new JScrollPane(panel_1);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(355, 170, 852, 653);
		frame.getContentPane().add(scrollPane);
		listStreet.get(1).createCar(mapLevel1);
		// listStreet.get(1).createCar(mapLevel1);
		setHero(mapLevel1.getHero());

		panel_4.setSize(new Dimension(mapLevel1.getWidth() / 3, mapLevel1.getHeight() / 3));

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(0, 0, 283, 416);
		ImageIcon iconMinimapBackground = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/Game Background_v3.png"));
		Image scaledIconMinimapBackground = iconMinimapBackground.getImage().getScaledInstance(panel_4.getWidth(),
				panel_4.getHeight(), Image.SCALE_DEFAULT);
		lblNewLabel_1.setIcon(new ImageIcon(scaledIconMinimapBackground));
		panel_4.add(lblNewLabel_1);

		mobile = new JLabel();
		mobile.setBounds(0, 46, 334, 528);

		ImageIcon iconmobile = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/mobile.png"));
		Image scalemobile = iconmobile.getImage().getScaledInstance(mobile.getWidth(), mobile.getHeight(),
				Image.SCALE_DEFAULT);
		mobile.setIcon(new ImageIcon(scalemobile));
		panel.add(mobile);
		ImageIcon iconRecharge = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/recharge.gif"));
		// recharge.setVisible(false);

		lblGlobalCitizen = new JLabel("GLOBAL CITIZEN");
		lblGlobalCitizen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGlobalCitizen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGlobalCitizen.setBounds(72, 13, 179, 20);
		panel.add(lblGlobalCitizen);

		recharge = new JButton();
		recharge.setBounds(130, 573, 163, 54);
		panel.add(recharge);
		recharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// frame.dispose();
				/*
				 * frmMain window = new frmMain();
				 * window.frame.setVisible(true);
				 * window.mapLevel1.requestFocusInWindow(); initialize();
				 */
				restartGame();
			}
		});
		Image scaleIconRecharge = iconRecharge.getImage().getScaledInstance(recharge.getWidth(), recharge.getHeight(),
				Image.SCALE_DEFAULT);
		recharge.setIcon(new ImageIcon(scaleIconRecharge));
		recharge.setVisible(false);
		arrow = new JLabel("");
		arrow.setBounds(50, 573, 68, 54);
		arrow.setIcon(new ImageIcon(
				new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/right-arrow-29.gif"))
						.getImage().getScaledInstance(arrow.getWidth(), arrow.getHeight(), Image.SCALE_DEFAULT)));
		arrow.setVisible(false);
		panel.add(arrow);
		select = new JLabel("");
		select.setBounds(50, 13, 50, 50);
		select.setIcon(
				new ImageIcon(new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/select.gif"))
						.getImage().getScaledInstance(select.getWidth(), select.getHeight(), Image.SCALE_DEFAULT)));
		select.setVisible(false);
		lblNewLabel.add(select);

		// Code for drawing Streets
		mapLevel1.drawLandmarks(listStreet);

		c = new ThreadsController(mapLevel1, this);
		mapLevel1.setThreadsController(c);
		// Let's start the game now..
		mapLevel1.setScrollPane(scrollPane);

		mapLevel1.repaint();
		timeLeft = GlobalCitizenConstants.TIME_INTERVAL;
		// Tutorial
		mapLevel1.threadsController.say("tuto_01.png");
		mapLevel1.threadsController.say("tuto_02.png");
		mapLevel1.threadsController.say("tuto_03.png");
		mapLevel1.threadsController.say("tuto_04.png");
		mapLevel1.threadsController.say("tuto_05.png");
		mapLevel1.threadsController.say("tuto_06.png");
		mapLevel1.threadsController.say("tuto_07.png");
		c.start();

	}
}
