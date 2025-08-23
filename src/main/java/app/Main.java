package main.java.app;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.view.WelcomeView;

public class Main {

	public static void main(String[] args) {
		StatefulApplication app = new StatefulApplication("Ispovjedaonica") {};
		StatefulPanel firstPanel = new WelcomeView(app);
		
		app.start(firstPanel);
	}
}
