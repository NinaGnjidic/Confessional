package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PressButtonView extends StatefulPanel {

	private static final long serialVersionUID = 7262437806001317631L;
	private static final String TITLE = "Pritisnite veliki crveni gumb!";

	public PressButtonView(StatefulApplication app) {
		super(app);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel(TITLE, SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(app.getFontName(), Font.BOLD, 35));

		this.add(label, BorderLayout.CENTER);
		
		this.setFocusable(true);
	    this.requestFocusInWindow();
	}

	@Override
	public void onRedButton() {
		this.app.show(new PrivacyPolicyView(app));
	}

}
