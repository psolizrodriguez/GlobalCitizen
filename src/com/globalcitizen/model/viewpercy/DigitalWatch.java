package com.globalcitizen.model.viewpercy;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DigitalWatch extends JPanel implements Runnable {
	Thread t = null;
	int hours = 0, minutes = 0, seconds = 0;
	String timeString = "";
	JButton b;

	DigitalWatch() {
		super();
		b = new JButton();
		b.setBounds(100, 100, 100, 50);

		this.add(b);
		this.setLayout(null);
		this.setVisible(true);
	}

	public void start() {
		try {
			t = new Thread(this);
			t.start();
			minutes = 6;
			seconds = 0;
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
		t = null;
	}

	public void printTime() {
		b.setText(timeString);
	}

	public static void main(String[] args) {
		new DigitalWatch();

	}
}
