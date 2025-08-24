package main.java.app.util;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public interface Timeable {

	default void startTimer(long ms) {
		stopTimer();

		Timer timer = new Timer();
		setTimer(timer);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(() -> {
					onTimerUp();
					stopTimer();
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
