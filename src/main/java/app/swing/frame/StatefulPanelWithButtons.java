package main.java.app.swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import main.java.app.EnvConfig;
import main.java.app.model.Displayable;
import main.java.app.state.StatefulApplication;

public abstract class StatefulPanelWithButtons<T extends Displayable> extends StatefulPanel {

	private static final long serialVersionUID = 8245263042179031038L;

	protected List<T> data = new ArrayList<>();
	private int pageSize = 4;
	private int pageIndex = 0;

	private JPanel leftPanel;
	private JPanel rightPanel;

	protected StatefulPanelWithButtons(StatefulApplication app, List<T> data, int pageSize) {
		super(app);
		this.data = data;
		this.pageSize = pageSize;
	}
	
	protected StatefulPanelWithButtons(StatefulApplication app, List<T> data, int pageSize, String backgroundImagePath) {
		super(app, backgroundImagePath);
		this.data = data;
		this.pageSize = pageSize;
	}
	
	@Override
	public void update() {
		//it is okay to do nothing
	}

	@Override
	public void handleDisplay() {
		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		leftPanel = new JPanel(new GridLayout(pageSize + 1, 1));
		rightPanel = new JPanel(new GridLayout(pageSize + 1, 1));
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void handleInput() {
		leftPanel.removeAll();
		rightPanel.removeAll();

		int start = pageIndex * pageSize * 2;
		int end = Math.min(start + pageSize * 2, data.size());
		List<T> currentPageData = data.subList(start, end);

		for (int i = 0; i < pageSize; i++) {
			if (i < currentPageData.size() / 2) {
				T item = currentPageData.get(i);
				JToggleButton btn = createDataButton(item);
				leftPanel.add(btn);
			} else {
				leftPanel.add(Box.createVerticalStrut(30));
			}

			int rightIndex = i + pageSize;
			if (rightIndex < currentPageData.size()) {
				T item = currentPageData.get(rightIndex);
				JToggleButton btn = createDataButton(item);
				rightPanel.add(btn);
			} else {
				rightPanel.add(Box.createVerticalStrut(30));
			}
		}

		JButton nextButton = createRightPanelControlButton();
		rightPanel.add(nextButton);
		JButton prevButton = createControlButton(EnvConfig.PREVIOUS_BUTTON_LABEL, pageIndex > 0, this::handlePrevious);
		leftPanel.add(prevButton);

		leftPanel.revalidate();
		rightPanel.revalidate();
		leftPanel.repaint();
		rightPanel.repaint();
	}

	private JButton createRightPanelControlButton() {
		int maxPage = (int) Math.ceil(data.size() / (double) (pageSize * 2)) - 1;
		boolean isLastPage = pageIndex >= maxPage;
		return isLastPage ? createDoneButton() : createNextButton();
	}

	private JButton createNextButton() {
		return createControlButton(EnvConfig.NEXT_BUTTON_LABEL, true, this::handleNext);
	}
	
	private JButton createDoneButton() {
		return createControlButton(EnvConfig.DONE_BUTTON_LABEL, true, this::handleDone);
	}
	
	private JButton createControlButton(String label, boolean isEnabled, Runnable action) {
		JButton nextButton = new JButton(label);
		nextButton.setEnabled(isEnabled);
		nextButton.addActionListener(e -> action.run());
		return nextButton;
	}

	private JToggleButton createDataButton(T item) {
		JToggleButton toggle = new JToggleButton(item.getName());
		boolean isSelected = isDataSelected(item);
		toggle.setSelected(isSelected);
		updateButtonStyle(toggle, isSelected);

		toggle.addActionListener(e -> {
			boolean nowSelected = toggle.isSelected();
			if (nowSelected) {
				onDataSelected(item);
			} else {
				onDataUnSelected(item);
			}
			updateButtonStyle(toggle, nowSelected);
		});

		return toggle;
	}
	

	private void updateButtonStyle(AbstractButton button, boolean isSelected) {
		if (isSelected) {
			button.setBackground(Color.GREEN);
			button.setForeground(Color.WHITE);
		} else {
			button.setBackground(null);
			button.setForeground(Color.BLACK);
		}
		button.setOpaque(true);
		button.setBorderPainted(true);
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
	
	protected abstract void handleDone();
	protected abstract boolean isDataSelected(T data);
	protected abstract void onDataSelected(T data);
	protected abstract void onDataUnSelected(T data);
}
