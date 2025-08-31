package main.java.app.swing.view;

import java.awt.Component;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PressButtonView extends StatefulPanel {

	private static final long serialVersionUID = 7262437806001317631L;
	private static final String TITLE = "Pritisnite veliki crveni gumb!";

	public PressButtonView(StatefulApplication app) {
		super(app, TITLE, "");
	}
	
	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		label.animateButton(()-> this.app.show(new PrivacyPolicyView(app)));
	}

}
