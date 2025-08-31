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

public class Button extends JPanel {
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
    private final Color pressColor = Color.BLUE;

    private int pressOffset = 0; // vertical shift during press animation

    public Button(String text, Font font) {
        this(text, DEFAULT_IMAGE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }

    public Button(String text, String imagePath, Font font) {
        this(text, imagePath, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }

    public Button(String text, String imagePath, int width, int height, int padding, Font font) {
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

        // draw button image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, padding, padding + pressOffset,
                    getWidth() - padding * 2, getHeight() - padding * 2, this);
        }

        // draw text
        if (text != null && !text.isEmpty()) {
            g.setColor(textColor);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent() + pressOffset;
            g.drawString(text, x, y);
        }
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public void setFontSize(float size) {
        if (font != null) {
            font = font.deriveFont(size);
            repaint();
        }
    }

    /**
     * Animate the button like it’s being pressed (flashes and moves down)
     * then runs the callback.
     */
    public void animateButton(final Runnable afterAnimation) {
        final int[] step = {0};
        final int totalSteps = 4; // two down/up cycles
        final int moveAmount = 4; // pixels to move down
        Timer timer = new Timer(100, null);
        JPanel self = this;

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (step[0] >= totalSteps) {
                    pressOffset = 0;
                    textColor = Color.WHITE;
                    self.repaint();
                    ((Timer) e.getSource()).stop();
                    afterAnimation.run();
                    return;
                }

                // on even steps: pressed
                if (step[0] % 2 == 0) {
                    pressOffset = moveAmount;
                    textColor = pressColor;
                } else { // on odd steps: released
                    pressOffset = 0;
                    textColor = Color.WHITE;
                }

                step[0]++;
                self.repaint();
            }
        });

        timer.start();
    }
}
