package main.java.app.swing.view;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public interface Timeable {

	default void startTimer(long ms) {
		// Stop any previously running timer
		stopTimer();

		// Create and store a new timer
		Timer timer = new Timer();
		setTimer(timer);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(() -> {
					onTimerUp();
					stopTimer(); // stop the timer after running
				});
			}
		}, ms);
	}

	default void stopTimer() {
		Timer timer = getTimer();
		if (timer != null) {
			timer.cancel();
			setTimer(null);
		}
	}

	void onTimerUp();
	void setTimer(Timer timer);
	Timer getTimer();
}
