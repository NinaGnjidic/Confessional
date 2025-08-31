package main.java.app.swing.view;

import java.awt.Component;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PrivacyPolicyView extends StatefulPanel {

	private static final long serialVersionUID = -7773204528442122200L;
	private static final String TITLE = "Suglasnost privatnosti";
	private static final String TEXT = "Korištenjem ovog sadržaja prihvaćate uvjete i dajete suglasnost za njegovo korištenje u skladu s navedenom svrhom. Neovlašteno umnožavanje, dijeljenje ili zlouporaba nije dopuštena. Organizator/autor zadržava pravo izmjene ili povlačenja suglasnosti u bilo kojem trenutku.";

	public PrivacyPolicyView(StatefulApplication app) {
		super(app, TITLE, TEXT);
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
