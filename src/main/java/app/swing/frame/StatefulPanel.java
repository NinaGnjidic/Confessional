package main.java.app.swing.frame;

import javax.swing.JPanel;

import main.java.app.state.State;
import main.java.app.state.StatefulApplication;

public abstract class StatefulPanel extends JPanel implements State {

	private static final long serialVersionUID = -6129290005971012152L;

	protected final StatefulApplication app;
	
	public StatefulPanel(StatefulApplication app) {
		this.app = app;
	}
}
