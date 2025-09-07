package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.CoinListener;

public class InsertCoinView extends StatefulPanel implements CoinListener {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_coin.png";
	private static final String TITLE = "UBACI MILODAR";
	private static final String TEXT = "PRITISNI CRVENI GUMB ZA POČETAK";

	public InsertCoinView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, TITLE, TEXT);
	}

	@Override
	public void processData() {
		this.app.clearSelected();
	}

	@Override
	protected Component displayBottom() {
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		leftButton = new StarButton("UPUTE", app.getFont());
		rightButton = new HashButton("RANG", app.getFont());

		bottomPanel.add(leftButton, BorderLayout.WEST);
		bottomPanel.add(rightButton, BorderLayout.EAST);

		return bottomPanel;
	}

	@Override
	public void button0Pressed() {
		label.animateButton(() -> app.show(new PrivacyPolicyView(app)));
	}

	@Override
	public void starButtonPressed() {
		leftButton.animateButton(() -> app.show(new InstructionsView(app)));
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> app.show(new RankingView(app)));
	}

	@Override
	public void bigRedButtonPressed() {
		if (app.getInsertedCoins() > 0.0)
			label.animateButton(() -> app.show(new PrivacyPolicyView(app)));
	}

	@Override
	public void onCoinInsert(float coinValue) {
		app.incrementInsertedCoins(coinValue);
	}
}
