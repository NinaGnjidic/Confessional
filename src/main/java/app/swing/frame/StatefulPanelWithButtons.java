package main.java.app.swing.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

import main.java.app.EnvironmentVariables;
import main.java.app.model.Displayable;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.ToggleButton;

public abstract class StatefulPanelWithButtons<T extends Displayable> extends StatefulPanel {

    private static final long serialVersionUID = 8245263042179031038L;

    protected List<T> data = new ArrayList<>();
    private int pageSize = 4;
    private int pageIndex = 0;
    private boolean includeActionButtons;

    private JPanel leftPanel;
    private JPanel rightPanel;

    protected final List<ToggleButton> dataButtons = new ArrayList<>();

    protected StatefulPanelWithButtons(StatefulApplication app, List<T> data, int pageSize,
                                       boolean includeActionButtons, String title) {
        super(app, title, null);
        this.data = data;
        this.pageSize = pageSize;
        this.includeActionButtons = includeActionButtons;
    }

    @Override
    public void handleDisplay() {
        super.handleDisplay();
        handleInput();
    }

    @Override
    protected Component displayCenter(String text) {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        leftPanel = new JPanel(new GridLayout(includeActionButtons ? pageSize + 1 : pageSize, 1));
        rightPanel = new JPanel(new GridLayout(includeActionButtons ? pageSize + 1 : pageSize, 1));
        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        centerPanel.setOpaque(false);

        return centerPanel;
    }

    /**
     * Populates the left and right panels with toggle buttons representing
     * the current page of data. Buttons animate on click and call selection handlers
     * after animation completes.
     */
    public void handleInput() {
        leftPanel.removeAll();
        rightPanel.removeAll();
        dataButtons.clear();

        int start = pageIndex * pageSize * 2;
        int end = Math.min(start + pageSize * 2, data.size());
        List<T> currentPageData = data.subList(start, end);

        for (int i = 0; i < pageSize; i++) {
            if (i < currentPageData.size() / 2) {
                T item = currentPageData.get(i);
                ToggleButton btn = createDataButton(item);
                dataButtons.add(btn);
                leftPanel.add(btn);
            } else {
                leftPanel.add(Box.createVerticalStrut(30));
            }

            int rightIndex = i + pageSize;
            if (rightIndex < currentPageData.size()) {
                T item = currentPageData.get(rightIndex);
                ToggleButton btn = createDataButton(item);
                dataButtons.add(btn);
                rightPanel.add(btn);
            } else {
                rightPanel.add(Box.createVerticalStrut(30));
            }
        }

        leftPanel.revalidate();
        rightPanel.revalidate();
        leftPanel.repaint();
        rightPanel.repaint();
    }

    @Override
    protected Component displayBottom() {
        if (includeActionButtons) {
        	JPanel bottomPanel = (JPanel) super.displayBottom();
        	rightButton = createRightPanelControlButton();

        	if(getMaxPage() != 0) {
	        	leftButton = createControlButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, pageIndex > 0, this::handlePrevious);
	        	bottomPanel.add(leftButton, BorderLayout.WEST);
        	}
        	
            bottomPanel.add(rightButton, BorderLayout.EAST);
            
            
            return bottomPanel;
        }
        return null;
    }

	private Button createRightPanelControlButton() {
		return isLastPage() ? createDoneButton() : createNextButton();
	}

	protected boolean isLastPage() {
		return pageIndex >= getMaxPage();
	}

	private int getMaxPage() {
		return (int) Math.ceil(data.size() / (double) (pageSize * 2)) - 1;
	}

	private Button createNextButton() {
		return createControlButton(EnvironmentVariables.NEXT_BUTTON_LABEL, true, this::handleNext);
	}
	
	private Button createDoneButton() {
		return createControlButton(EnvironmentVariables.DONE_BUTTON_LABEL, true, this::handleDone);
	}
	
    private Button createControlButton(String label, boolean isEnabled, Runnable action) {
        Button button = new Button(label, app.getFont());
        button.setPreferredSize(new Dimension(180 + 20 * 2, 50 + 20 * 2));
        button.setEnabled(isEnabled);

        return button;
    }

    private ToggleButton createDataButton(T item) {
        ToggleButton toggle = new ToggleButton(item.getName(), app.getFont());
        toggle.setPreferredSize(new Dimension(180 + 20 * 2, 50 + 20 * 2));
        boolean isSelected = isDataSelected(item);
        toggle.setSelected(isSelected);
        toggle.bindSelectionHandlers(() -> onDataSelected(item), () -> onDataUnSelected(item));
        return toggle;
    }

    private void handlePrevious() {
        if (pageIndex > 0) {
            pageIndex--;
            handleInput();
        }
    }

    private void handleNext() {
        pageIndex++;
        handleInput();
    }

    @Override
    public void button1Pressed() {
        animateDataButtonAtIndex(0);
    }

    @Override
    public void button2Pressed() {
        animateDataButtonAtIndex(1);
    }

    @Override
    public void button3Pressed() {
        animateDataButtonAtIndex(2);
    }

    @Override
    public void button4Pressed() {
        animateDataButtonAtIndex(3);
    }

    @Override
    public void button5Pressed() {
        animateDataButtonAtIndex(4);
    }

    @Override
    public void button6Pressed() {
        animateDataButtonAtIndex(5);
    }

    private void animateDataButtonAtIndex(int index) {
        if (index >= 0 && index < dataButtons.size()) {
            ToggleButton toggle = dataButtons.get(index);
            toggle.animateButton(()->{});
        }
    }

    protected void handleDone() {
        // Override in subclass if needed
    }

    protected abstract boolean isDataSelected(T data);

    protected abstract void onDataSelected(T data);

    protected abstract void onDataUnSelected(T data);

}
