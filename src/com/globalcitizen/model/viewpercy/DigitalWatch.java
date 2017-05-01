package com.globalcitizen.model.viewpercy;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.shape.visitor.VisitorDraw;

public class DigitalWatch extends JPanel implements Runnable {
	Thread t = null;
	int hours = 0, minutes = 0, seconds = 0;
	String timeString = "";
	JButton b;
	VisitorDraw visitor;
	public JLabel text;
	boolean lostAnimation;
	int lostAnimationCounter;
	JLabel lostImage;
	JLabel cameraImage;

	DigitalWatch(VisitorDraw visitor) {
		super();
		b = new JButton();
		b.setBounds(100, 50, 100, 50);

		this.add(b);
		this.setLayout(null);
		this.setVisible(true);
		this.visitor = visitor;

		cameraImage = new JLabel();
		cameraImage.setBounds(50, 150, 200, 150);
		ImageIcon cameraIcon = new ImageIcon(
				frmMain.class.getResource("/com/globalcitizen/model/viewpercy/showimage.gif"));
		Image scaleCameraIcon = cameraIcon.getImage().getScaledInstance(cameraImage.getWidth(), cameraImage.getHeight(),
				Image.SCALE_DEFAULT);
		cameraImage.setIcon(new ImageIcon(scaleCameraIcon));

		this.add(cameraImage);

		lostImage = new JLabel();
		lostImage.setBounds(40, 150, 219, 120);
		ImageIcon lostIcon = new ImageIcon(frmMain.class.getResource("/com/globalcitizen/model/viewpercy/SsvcfhF.gif"));
		Image scaleLostIcon = lostIcon.getImage().getScaledInstance(lostImage.getWidth(), lostImage.getHeight(),
				Image.SCALE_DEFAULT);
		lostImage.setIcon(new ImageIcon(scaleLostIcon));
		lostImage.setVisible(false);
		this.add(lostImage);

		text = new JLabel("<html>Take a good picture before you miss your chance!</html>");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 15));
		text.setBounds(50, 280, 200, 100);

		text.setIconTextGap(-text.getWidth());
		text.setOpaque(true);
		text.setLayout(null);

		this.add(text);
	}

	public void start(VisitorDraw visitor) {
		text.setText("<html>Take a good picture before you miss your chance!</html>");
		cameraImage.setVisible(true);
		lostImage.setVisible(false);
		this.visitor = visitor;
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visitor.closeMinigame(false);
				System.out.println("clicked");
			}
		});
		try {
			t = new Thread(this);
			t.start();
			minutes = GlobalCitizenConstants.GLOBAL_MINUTES_PUZZLE;
			seconds = 5;
		} catch (Exception e) {
		}
	}

	public void run() {
		try {
			while (t != null) {
				if (minutes < 10) {
					timeString = "0";
				}
				timeString += minutes + ":";
				if (seconds < 10) {
					timeString += "0";
				}
				timeString += seconds;
				printTime();

				t.sleep(1000); // interval given in milliseconds
				if (seconds == 0 && minutes == 0) {

					stop();

					return;
				} else {
					if (seconds == 0) {
						if (minutes > 0) {
							minutes--;
							seconds = 59;
						}

					} else {
						seconds--;
					}
				}

			}
		} catch (Exception e) {
		}
	}

	public void stop() {
		text.setText("<html>You missed your chance, better luck next time!</html>");
		lostImage.setVisible(true);
		cameraImage.setVisible(false);
		visitor.puzzleEx.cameraImage.setVisible(true);
		b.setText("OK");
		t = null;
	}

	public void printTime() {
		b.setText(timeString);
	}
}
