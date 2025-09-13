package main.java.app;

import java.awt.FontFormatException;
import java.io.IOException;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.view.InsertCoinView;

public class Application implements Runnable {
	@Override
	public void run() {
		try {

			StatefulApplication app = new StatefulApplication("Ispovjedaonica") {};
			StatefulPanel firstPanel = new InsertCoinView(app);
			app.start(firstPanel);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
