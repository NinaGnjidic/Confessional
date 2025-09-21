package main.java.app.swing.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A toggleable version of {@link Button}.
 * Each call to animateButton() flips the selected state,
 * updates the style accordingly, and then runs the callback.
 */
public class ToggleButton extends Button {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_IMAGE_PATH = "/images/button.png";

    private boolean selected = false;

    private Color selectedTextColor = Color.YELLOW;
    private Color unselectedTextColor = Color.WHITE;

    private Runnable onSelect;
    private Runnable onUnselect;
    
    public ToggleButton(String text, Font font) {
        this(text, font, DEFAULT_IMAGE_PATH);
    }

    public ToggleButton(String text, Font font, String imagePath) {
        super(text, imagePath, font);
        updateStyle();
    }

    @Override
    public void animateButton(Runnable afterAnimation) {
        // Let the base animation run first
        super.animateButton(() -> {
            // After animation finishes: flip toggle state
            selected = !selected;
            updateStyle();

            // Fire callbacks
            if (selected && onSelect != null) {
                onSelect.run();
            } else if (!selected && onUnselect != null) {
                onUnselect.run();
            }

            if (afterAnimation != null) {
                afterAnimation.run();
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        updateStyle();
    }

    private void updateStyle() {
        if (selected) {
            setTextColor(selectedTextColor);
        } else {
            setTextColor(unselectedTextColor);
        }
        repaint();
    }

    @Override
    protected void drawBorder(Graphics g, int x, int y, int width, int height) {
        super.drawBorder(g, x, y, width, height);

        if (selected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(6, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g2.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
            g2.dispose();
        }
    }

	public void bindSelectionHandlers(Runnable onSelect, Runnable onUnselect) {
        this.onSelect = onSelect;
        this.onUnselect = onUnselect;
    }
}
