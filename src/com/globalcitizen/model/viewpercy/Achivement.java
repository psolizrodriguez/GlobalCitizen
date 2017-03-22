package com.globalcitizen.model.viewpercy;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Achivement extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Achivement frame = new Achivement();
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
	public Achivement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				Achivement.class.getResource("/com/globalcitizen/model/viewpercy/liberty-bell-with-the.jpg")));
		lblNewLabel.setBounds(180, 35, 227, 287);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Liberty Bell");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(230, 0, 93, 37);
		contentPane.add(lblNewLabel_1);

		JTextArea lblNewLabel_2 = new JTextArea(
				"The Liberty Bell is an iconic symbol of American independence, "
				+ "\nlocated in Philadelphia, Pennsylvania. "
						+ "\nFormerly placed in the steeple of the Pennsylvania State House "
						+ "\n(now renamed Independence Hall), the bell today is located "
						+ "\nin the Liberty Bell Center in Independence National Historical Park. ");
		lblNewLabel_2.setBounds(43, 335, 488, 92);
		contentPane.add(lblNewLabel_2);
	}
}
