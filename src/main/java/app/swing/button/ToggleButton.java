package main.java.app.swing.button;

import java.awt.Color;
import java.awt.Font;

/**
 * A toggleable version of {@link Button}.
 * Each call to animateButton() flips the selected state,
 * updates the style accordingly, and then runs the callback.
 */
public class ToggleButton extends Button {
    private static final long serialVersionUID = 1L;

    private boolean selected = false;

    private Color selectedTextColor = Color.YELLOW;
    private Color unselectedTextColor = Color.WHITE;

    private Runnable onSelect;
    private Runnable onUnselect;

    public ToggleButton(String text, Font font) {
        super(text, font);
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

    public void setSelectedTextColor(Color c) {
        this.selectedTextColor = c;
        if (selected) updateStyle();
    }

    public void setUnselectedTextColor(Color c) {
        this.unselectedTextColor = c;
        if (!selected) updateStyle();
    }

    public void bindSelectionHandlers(Runnable onSelect, Runnable onUnselect) {
        this.onSelect = onSelect;
        this.onUnselect = onUnselect;
    }
}
