package com.globalcitizen.model.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.globalcitizen.model.characters.Landmark;
import com.shape.visitor.Visitor;

public class PuzzleEx extends JFrame {

	private JPanel panel;
	private BufferedImage source;
	private ArrayList<MyButton> buttons;

	ArrayList<Point> solution = new ArrayList();

	private Image image;
	private MyButton lastButton;
	private int width, height;
	private final int DESIRED_WIDTH = 300;
	private BufferedImage resized;
	Landmark landmark;
	Visitor visitor;

	public PuzzleEx(Landmark landmark, Visitor visitor) {
		this.landmark = landmark;
		this.visitor = visitor;
		initUI();
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initUI() {

		solution.add(new Point(0, 0));
		solution.add(new Point(0, 1));
		solution.add(new Point(0, 2));
		solution.add(new Point(1, 0));
		solution.add(new Point(1, 1));
		solution.add(new Point(1, 2));
		solution.add(new Point(2, 0));
		solution.add(new Point(2, 1));
		solution.add(new Point(2, 2));
		solution.add(new Point(3, 0));
		solution.add(new Point(3, 1));
		solution.add(new Point(3, 2));

		buttons = new ArrayList();

		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel.setLayout(new GridLayout(4, 3, 0, 0));

		try {
			source = loadImage();
			int h = getNewHeight(source.getWidth(), source.getHeight());
			resized = resizeImage(source, DESIRED_WIDTH, h, BufferedImage.TYPE_INT_ARGB);

		} catch (IOException ex) {
			Logger.getLogger(PuzzleEx.class.getName()).log(Level.SEVERE, null, ex);
		}

		width = resized.getWidth(null);
		height = resized.getHeight(null);

		add(panel, BorderLayout.CENTER);

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 3; j++) {

				image = createImage(new FilteredImageSource(resized.getSource(),
						new CropImageFilter(j * width / 3, i * height / 4, (width / 3), height / 4)));
				MyButton button = new MyButton(image);
				button.putClientProperty("position", new Point(i, j));

				if (i == 3 && j == 2) {
					lastButton = new MyButton();
					lastButton.setBorderPainted(false);
					lastButton.setContentAreaFilled(false);
					lastButton.setLastButton();
					lastButton.putClientProperty("position", new Point(i, j));
				} else {
					buttons.add(button);
				}
			}
		}

		Collections.shuffle(buttons);
		buttons.add(lastButton);

		for (int i = 0; i < 12; i++) {

			MyButton btn = buttons.get(i);
			panel.add(btn);
			btn.setBorder(BorderFactory.createLineBorder(Color.gray));
			btn.addActionListener(new ClickAction());
		}

		pack();
		setTitle(landmark.getName());
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.out.println("closing");
				visitor.closeMinigame(false);
			}
		});
	}

	private int getNewHeight(int w, int h) {

		double ratio = DESIRED_WIDTH / (double) w;
		int newHeight = (int) (h * ratio);
		return newHeight;
	}

	private BufferedImage loadImage() throws IOException {

		BufferedImage bimg = ImageIO.read(ClassLoader.getSystemResource(this.landmark.getLandmarkImage()));

		return bimg;
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	private class ClickAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			checkButton(e);
			checkSolution();
		}

		private void checkButton(ActionEvent e) {

			int lidx = 0;
			for (MyButton button : buttons) {
				if (button.isLastButton()) {
					lidx = buttons.indexOf(button);
				}
			}

			JButton button = (JButton) e.getSource();
			int bidx = buttons.indexOf(button);

			if ((bidx - 1 == lidx) || (bidx + 1 == lidx) || (bidx - 3 == lidx) || (bidx + 3 == lidx)) {
				Collections.swap(buttons, bidx, lidx);
				updateButtons();
			}
		}

		private void updateButtons() {

			panel.removeAll();

			for (JComponent btn : buttons) {

				panel.add(btn);
			}

			panel.validate();
		}
	}

	private void checkSolution() {

		ArrayList<Point> current = new ArrayList();

		for (JComponent btn : buttons) {
			current.add((Point) btn.getClientProperty("position"));
		}

		if (compareList(solution, current)) {
			// JOptionPane.showMessageDialog(panel, "Finished",
			// "Congratulation", JOptionPane.INFORMATION_MESSAGE);
			visitor.closeMinigame(true);
			this.dispose();
			// Code to resume the game
		}
	}

	public static boolean compareList(List ls1, List ls2) {
		return ls1.toString().contentEquals(ls2.toString());
	}

}