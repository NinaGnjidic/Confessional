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
    private static final int DEFAULT_WIDTH = 230;
    private static final int DEFAULT_HEIGHT = 70;

    private final Image backgroundImage;
    private String text;
    private int padding;
    private Font font;
    private Image iconImage;
    private int iconTextGap = 8; // space between icon and text
    public boolean hasShadow = false;

    private Color textColor = Color.black;
    private final Color pressColor = Color.BLUE;

    private int pressOffset = 0; // vertical shift during press animation

    public Button(String text, Font font) {
        this(text, DEFAULT_IMAGE_PATH, null, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }
    
    public Button(String text, Font font,  String iconImagePath) {
        this(text, DEFAULT_IMAGE_PATH, iconImagePath, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }
    
    public Button(String text, String imagePath, Font font) {
        this(text, imagePath, null, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PADDING, font);
    }

    protected Button(String text, String imagePath, String iconImagePath, int width, int height, int padding, Font font) {
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
        
        if (iconImagePath != null) {
            java.net.URL iconUrl = getClass().getResource(iconImagePath);
            if (iconUrl != null) {
                this.iconImage = new ImageIcon(iconUrl).getImage();
            }
        }

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

        if (text != null && !text.isEmpty()) {
            g.setColor(textColor);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();

            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            int iconWidth = 0;
            int iconHeight = 0;

            // scale icon to ~60% of button height
            if (iconImage != null) {
                double scaleFactor = 0.6; // smaller = icon height is 60% of button height
                iconHeight = (int) (getHeight() * scaleFactor);
                double aspectRatio = (double) iconImage.getWidth(this) / iconImage.getHeight(this);
                iconWidth = (int) (iconHeight * aspectRatio);
            }

            int totalWidth = textWidth + (iconImage != null ? iconWidth + iconTextGap : 0);

            // center icon+text block horizontally
            int startX = (getWidth() - totalWidth) / 2;
            int textY = (getHeight() - fm.getHeight()) / 2 + textHeight + pressOffset;

            // draw icon
            if (iconImage != null) {
                int iconY = (getHeight() - iconHeight) / 2 + pressOffset; // vertically centered
                g.drawImage(iconImage, startX, iconY, iconWidth, iconHeight, this);
                startX += iconWidth + iconTextGap;
            }

            // draw text
            if (hasShadow) {
            	g.setColor(new Color(0, 0, 139));
            	g.drawString(text, startX - 10, textY + 10);
            	g.setColor(textColor);
            }
            g.drawString(text, startX, textY);
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
                    textColor = Color.yellow;
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
                    textColor = Color.yellow;
                }

                step[0]++;
                self.repaint();
            }
        });

        timer.start();
    }
    
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }
}
