package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.ImageTextPanel;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;

public class InsertCoinView extends StatefulPanel {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final String TITLE = "Ubacite kovanicu!";
	private static final String LEFT_BUTTON_LABEL = "  Upute";
	private static final String RIGHT_BUTTON_LABEL = "      Rang lista";

	public InsertCoinView(StatefulApplication app) {
		super(app);
	}

	@Override
	public void preprocessData() {
		this.app.clearSelected();
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel(TITLE, SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(app.getFontName(), Font.BOLD, 35));

		this.add(label, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		ImageTextPanel leftButton = new StarButton(LEFT_BUTTON_LABEL, new Font(app.getFontName(), Font.PLAIN, 10));
		ImageTextPanel rightButton = new HashButton(RIGHT_BUTTON_LABEL, new Font(app.getFontName(), Font.PLAIN, 10));
		
	    bottomPanel.add(leftButton, BorderLayout.WEST);
	    bottomPanel.add(rightButton, BorderLayout.EAST);
	    this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void handleInput() {
		this.setFocusable(true);
	    this.requestFocusInWindow();
	    
	    //TODO: listen for coin input - on coin inserted update()
	}
	
	@Override
	public void onStar() {
		app.show(new InstructionsView(app));
	}

	@Override
	public void onHash() {
		app.show(new RankingView(app));
	}
	
	@Override
	public void onButton0() {
		this.update();
	}
	
	@Override
	public void onRedButton() {
		this.update();
	}

	@Override
	public void update() {
		this.app.show(new PressButtonView(app));
	}

}
