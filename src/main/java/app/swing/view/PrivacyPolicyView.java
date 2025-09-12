package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.PRIVACY_TEXT;
import static main.java.app.EnvironmentVariables.PRIVACY_TITLE;

import java.awt.Component;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PrivacyPolicyView extends StatefulPanel {

	private static final long serialVersionUID = -7773204528442122200L;
	
	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_privacy.jpg";

	public PrivacyPolicyView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, PRIVACY_TITLE, PRIVACY_TEXT);
	}

	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		label.animateButton(() -> this.app.show(new ChooseTypeView(app)));
	}

}
