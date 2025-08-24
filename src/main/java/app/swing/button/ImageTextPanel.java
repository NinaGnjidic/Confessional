package main.java.app.swing.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageTextPanel extends JPanel {
    private static final long serialVersionUID = 3775959994320542119L;
    private static final String DEFAULT_IMAGE_PATH = "/images/button.png";
    private static final int DEFAULT_PADDING = 75;
    private static final int DEFAULT_WIDTH = 180;
    private static final int DEFAULT_HEIGHT = 50;

    private final Image backgroundImage;
    private String text;
    private int padding;
    private Font font;

    public ImageTextPanel(String text, String imagePath, Font font) {
        this(text, imagePath, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }
    
    public ImageTextPanel(String text, Font font) {
        this(text, DEFAULT_IMAGE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }

    public ImageTextPanel(String text, String resourceName, int width, int height, int padding, Font font) {
        this.text = text;
        this.padding = padding;
        this.font = font;

        java.net.URL imgUrl = getClass().getResource(resourceName);
        if (imgUrl == null) {
            throw new IllegalArgumentException("Image not found: " + resourceName);
        }
        this.backgroundImage = new ImageIcon(imgUrl).getImage();

        setPreferredSize(new Dimension(width + padding * 2, height + padding * 2));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw image respecting padding
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, padding, padding, getWidth() - padding * 2, getHeight() - padding * 2, this);
        }

        // Draw centered text using the chosen font
        if (text != null && !text.isEmpty()) {
            g.setColor(Color.WHITE);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, x, y);
        }
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }
}
