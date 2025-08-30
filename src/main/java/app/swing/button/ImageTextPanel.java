package main.java.app.swing.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageTextPanel extends JPanel {
	private static final long serialVersionUID = 3775959994320542119L;
	private static final String DEFAULT_IMAGE_PATH = "/images/button.png";
	private static final int DEFAULT_PADDING = 20;
	private static final int DEFAULT_WIDTH = 180;
	private static final int DEFAULT_HEIGHT = 50;

	private final Image backgroundImage;
	private String text;
	private int padding;
	private Font font;

	private Color textColor = Color.WHITE;
	private final Color pressColor = Color.RED;

	public ImageTextPanel(String text, Font font) {
		this(text, DEFAULT_IMAGE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
	}

	public ImageTextPanel(String text, String imagePath, Font font) {
		this(text, imagePath, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
	}

	public ImageTextPanel(String text, String imagePath, int width, int height, int padding, Font font) {
		this.text = text;
		this.padding = padding;
		this.font = font;

		Image img = null;
		if (imagePath != null) {
			java.net.URL imgUrl = getClass().getResource(imagePath);
			if (imgUrl != null) {
				img = new ImageIcon(imgUrl).getImage();
			}
		}
		this.backgroundImage = img;

		setPreferredSize(new Dimension(width + padding * 2, height + padding * 2));
		setOpaque(false); // respect PNG transparency
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw PNG button background
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, padding, padding, getWidth() - padding * 2, getHeight() - padding * 2, this);
		}

		// draw text
		if (text != null && !text.isEmpty()) {
			g.setColor(textColor);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			int x = (getWidth() - fm.stringWidth(text)) / 2;
			int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
			g.drawString(text, x, y);
		}
	}

	public void flash(int flashes) {
		Timer timer = new Timer(100, null);
		timer.addActionListener(new ActionListener() {
			int step = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (step >= flashes * 2) {
					textColor = Color.WHITE;
					((Timer) e.getSource()).stop();
					repaint();
					return;
				}
				textColor = (step % 2 == 0) ? pressColor : Color.WHITE;
				step++;
				repaint();
			}
		});
		timer.start();
	}

	/** Animate button with flashing effect, then execute callback */
	public void animateButton(final Runnable afterAnimation) {
		final int[] step = { 0 };
		Timer timer = new Timer(100, null);
		JPanel self = this;
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (step[0] >= 4) { // two flashes
					self.repaint();
					((Timer) e.getSource()).stop();
					afterAnimation.run();
					return;
				}
				// simple flash effect
				textColor = (step[0] % 2 == 0) ? pressColor : Color.WHITE;
				step[0]++;
				self.repaint();
			}
		});
		timer.start();
	}
}
