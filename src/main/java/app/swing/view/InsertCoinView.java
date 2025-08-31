package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;

public class InsertCoinView extends StatefulPanel {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final String TITLE = "Ubacite kovanicu!";

	public InsertCoinView(StatefulApplication app) {
		super(app, TITLE, null);
	}

	@Override
	public void processData() {
		this.app.clearSelected();
	}

	@Override
	protected Component displayBottom() {
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		leftButton = new StarButton("  Upute", app.getFont());
		rightButton = new HashButton("      Rang lista", app.getFont());

		bottomPanel.add(leftButton, BorderLayout.WEST);
		bottomPanel.add(rightButton, BorderLayout.EAST);
		
		return bottomPanel;
	}

	@Override
	public void starButtonPressed() {
		leftButton.animateButton(() -> app.show(new InstructionsView(app)));
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> app.show(new RankingView(app)));
	}

	//TODO: should be on coin input
	@Override
	public void bigRedButtonPressed() {
		label.animateButton(() -> app.show(new PressButtonView(app)));
	}

}
