package main.java.app.swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.app.EnvironmentVariables;
import main.java.app.model.Displayable;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.ToggleButton;

public abstract class StatefulPanelWithButtons<T extends Displayable> extends StatefulPanel {

    private static final long serialVersionUID = 8245263042179031038L;
    
	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_default.jpg";

	private static final Image BACKGROUND_IMAGE = new ImageIcon(StatefulPanelWithButtons.class.getResource(BACKGROUND_IMAGE_PATH)).getImage();

    protected List<T> data = new ArrayList<>();
    private int pageSize = 6;
    private int pageIndex = 0;

    String title;
    protected Button label;
    protected Button rightButton;
    protected Button leftButton;
    private JPanel leftPanel;
    private JPanel rightPanel;

    protected final List<ToggleButton> dataButtons = new ArrayList<>();
    

    protected StatefulPanelWithButtons(StatefulApplication app, List<T> data, int pageSize, String title) {
        super(app, BACKGROUND_IMAGE);
        this.title = title;
        this.data = data;
        this.pageSize = pageSize;
    }

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 170, 100, 170));
		
		label = new Button(title, null, app.getFont().deriveFont(Font.BOLD, 50));
		label.setTextColor(Color.YELLOW);
		label.hasShadow = true;
		this.add(label, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		leftPanel.setOpaque(false);
		rightPanel.setOpaque(false);
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		centerPanel.setOpaque(false);
		this.add(centerPanel, BorderLayout.CENTER);

		displayControlsPanel();

		handleInput();
	}

	protected void displayControlsPanel() {
		JPanel bottomPanel = (JPanel) super.displayBottom();
		rightButton = createRightPanelControlButton();
		if (getMaxPage() != 0) {
			leftButton = createControlButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, pageIndex > 0,
					this::handlePrevious);
			bottomPanel.add(leftButton, BorderLayout.WEST);
		}
		bottomPanel.add(rightButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

    /**
     * Populates the left and right panels with toggle buttons representing
     * the current page of data. Buttons animate on click and call selection handlers
     * after animation completes.
     */
    public void handleInput() {
        leftPanel.removeAll();
        rightPanel.removeAll();

		leftPanel.add(Box.createRigidArea(new Dimension(0, pageSize > 1 ? 15 : 250)));
		rightPanel.add(Box.createRigidArea(new Dimension(0, pageSize > 1 ? 15 : 250)));

        dataButtons.clear();

        int start = pageIndex * pageSize * 2;
        int end = Math.min(start + pageSize * 2, data.size());
        List<T> currentPageData = data.subList(start, end);
        
        for(int i = 0; i < (end-start);i++) {
            ToggleButton letPanelButton = createDataButton(currentPageData.get(i), i);
            dataButtons.add(letPanelButton);
            leftPanel.add(letPanelButton);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            
            i++;
            
            T item = currentPageData.get(i);
            ToggleButton btn = createDataButton(item, i);
            dataButtons.add(btn);
            rightPanel.add(btn);
            rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        } 

        leftPanel.revalidate();
        rightPanel.revalidate();
        leftPanel.repaint();
        rightPanel.repaint();
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
		Button button = new HashButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, app.getFont());
		button.setPreferredSize(new Dimension(180 + 20 * 2, 50 + 20 * 2));
		return button;
	}
	
    private Button createControlButton(String label, boolean isEnabled, Runnable action) {
        Button button = new Button(label, app.getFont());
        button.setPreferredSize(new Dimension(180 + 20 * 2, 50 + 20 * 2));
        button.setEnabled(isEnabled);

        return button;
    }

    protected ToggleButton createDataButton(T item, int index) {
        ToggleButton toggle = new ToggleButton((index+1)+". " + item.getName(), app.getFont());
        toggle.setPreferredSize(new Dimension(250, 150));
        toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
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
