package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class InsertCoinView extends StatefulPanel implements Timeable {

	private static final long serialVersionUID = -5509182536642826627L;

	private static final long DELAY_15_SEC_IN_MS = 15000l;
	
	private Timer timer;

	public InsertCoinView(StatefulApplication app) {
		super(app);
		
		this.timer = new Timer();
	}
	
	@Override
	public Timer getTimer() {
		return this.timer;
	}

	@Override
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Insert coin to continue...", SwingConstants.CENTER);
		this.add(label, BorderLayout.CENTER);
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

		this.startTimer(DELAY_15_SEC_IN_MS);
	}

	@Override
	public void update() {
		this.stopTimer();
		
		this.app.show(new ChooseCategoryView(app));
	}
	
	@Override
	public void onTimerUp() {
		this.stopTimer();
		
		this.app.show(new WelcomeView(app));
	}

}
