package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.INSERT_COIN_LEFT_BUTTON_TEXT;
import static main.java.app.EnvironmentVariables.INSERT_COIN_RIGHT_BUTTON_TEXT;
import static main.java.app.EnvironmentVariables.INSERT_COIN_TEXT;
import static main.java.app.EnvironmentVariables.INSERT_COIN_TITLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fazecast.jSerialComm.SerialPort;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.button.StarButton;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.listener.CoinListener;

public class InsertCoinView extends StatefulPanel implements CoinListener {
	private static final long serialVersionUID = -5509182536642826627L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_coin.jpg";
	private static final Image BACKGROUND_IMAGE = new ImageIcon(InsertCoinView.class.getResource(BACKGROUND_IMAGE_PATH))
			.getImage();

	private Button label = new Button(INSERT_COIN_TITLE, null, app.getFont().deriveFont(Font.BOLD, 90));;
	private Button text = new Button(INSERT_COIN_TEXT, null, app.getFont().deriveFont(Font.BOLD, 40));;
	private Button rightButton;
	private Button leftButton;

	public InsertCoinView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE);
		SerialPort serialPort = CoinListener.getSerialPort();
		if (serialPort != null)
			serialPort.addDataListener(this);

		this.label = app.getInsertedCoins() > 0 ? text:label;
		if(app.getInsertedCoins() >0) {
			app.playSound("/sounds/music.mp3");
		}
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(250, 120, 100, 140));

		this.label.setTextColor(Color.yellow);
		this.label.hasShadow = true;
		this.add(label, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(100, 100));
		bottomPanel.setOpaque(false);

		leftButton = new StarButton(INSERT_COIN_LEFT_BUTTON_TEXT, app.getFont());
		rightButton = new HashButton(INSERT_COIN_RIGHT_BUTTON_TEXT, app.getFont());

		bottomPanel.add(leftButton, BorderLayout.WEST);
		bottomPanel.add(rightButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public void button0Pressed() {
		app.playSound("/sounds/music.mp3");
		this.label.animateButton(() -> app.show(new PrivacyPolicyView(app)));
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
			this.label.animateButton(() -> app.show(new PrivacyPolicyView(app)));
		this.label.animateButton(() -> {
		});
	}

	@Override
	public void onCoinInsert(float coinValue) {
		app.incrementInsertedCoins(coinValue);
		this.label.animateButton(() -> app.show(new InsertCoinView(app)));
	}
}
