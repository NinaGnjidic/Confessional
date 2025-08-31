package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.ImageTextPanel;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;

public class InsertCoinView extends StatefulPanel {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final String TITLE = "Ubacite kovanicu!";

	private ImageTextPanel label;
	private StarButton starButton;
	private HashButton hashButton;

	public InsertCoinView(StatefulApplication app) {
		super(app);
	}

	@Override
	public void processData() {
		this.app.clearSelected();
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());

		label = new ImageTextPanel(TITLE, null, app.getFont().deriveFont(Font.BOLD, 24));
		this.add(label, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		starButton = new StarButton("  Upute", app.getFont());
		hashButton = new HashButton("      Rang lista", app.getFont());

		bottomPanel.add(starButton, BorderLayout.WEST);
		bottomPanel.add(hashButton, BorderLayout.EAST);

		this.add(bottomPanel, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public void onStar() {
		starButton.animateButton(() -> app.show(new InstructionsView(app)));
	}

	@Override
	public void onHash() {
		hashButton.animateButton(() -> app.show(new RankingView(app)));
	}

	//TODO: should be on coin input
	@Override
	public void onRedButton() {
		label.animateButton(() -> app.show(new PressButtonView(app)));
	}

}
