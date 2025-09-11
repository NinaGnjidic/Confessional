package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TEXT;
import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TITLE;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class InstructionsView extends StatefulPanel {

	private static final long serialVersionUID = 2661132117794606000L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_instructions.png";

	protected InstructionsView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, INSTRUCTIONS_TITLE, INSTRUCTIONS_TEXT);
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}

}
