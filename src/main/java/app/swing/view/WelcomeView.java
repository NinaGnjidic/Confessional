package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class WelcomeView extends StatefulPanel {

	private static final long serialVersionUID = -8127853837608217250L;

	public WelcomeView(StatefulApplication app) {
		super(app);
	}
	
	@Override
	public void preprocessData() {
		this.app.clearSelected();
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Welcome! Press any key to continue...", SwingConstants.CENTER);
		this.add(label);
	}

	@Override
	public void handleInput() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key pressed: " + e.getKeyChar());
				update();
			}
		});
	}

	@Override
	public void update() {
		this.app.show(new InsertCoinView(app));
	}
}
