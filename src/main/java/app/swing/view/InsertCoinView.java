package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.INSERT_COIN_TEXT;
import static main.java.app.EnvironmentVariables.INSERT_COIN_TITLE;
import static main.java.app.EnvironmentVariables.INSERT_COIN_LEFT_BUTTON_TEXT;
import static main.java.app.EnvironmentVariables.INSERT_COIN_RIGHT_BUTTON_TEXT;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import com.fazecast.jSerialComm.SerialPort;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.CoinListener;

public class InsertCoinView extends StatefulPanel implements CoinListener {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_coin.png";

	public InsertCoinView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, INSERT_COIN_TITLE, INSERT_COIN_TEXT);
		SerialPort serialPort = getSerialPort();
		if (serialPort != null)
			serialPort.addDataListener(this);

		app.playSound("/sounds/music.mp3");
	}

	@Override
	public void processData() {
		this.app.clearSelected();
	}

	@Override
	protected Component displayBottom() {
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);

		leftButton = new StarButton(INSERT_COIN_LEFT_BUTTON_TEXT, app.getFont());
		rightButton = new HashButton(INSERT_COIN_RIGHT_BUTTON_TEXT, app.getFont());

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
