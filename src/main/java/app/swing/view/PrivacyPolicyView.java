package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.PRIVACY_TEXT;
import static main.java.app.EnvironmentVariables.PRIVACY_TITLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.java.app.EnvironmentVariables;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.frame.StatefulPanel;

public class PrivacyPolicyView extends StatefulPanel {

	private static final long serialVersionUID = -7773204528442122200L;
	
	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_privacy.jpg";
	
	private Button label;
	private Button rightButton;

	public PrivacyPolicyView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, PRIVACY_TITLE, PRIVACY_TEXT);
	}
	
	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(130, 120, 100, 140));
		label = new Button(PRIVACY_TITLE, null, app.getFont().deriveFont(Font.BOLD, 16));
		label.hasShadow = true;
		label.setTextColor(Color.yellow);
		this.add(label, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.setOpaque(false);
		this.add(panel, BorderLayout.CENTER);
		
		String text = PRIVACY_TEXT;
		String rtext = text.replace("\\n", "\n");
		List<String> ltext = Arrays.asList(rtext.split("\n\n\n"));
		for (int i = 0; i < ltext.size(); i++) {
			JTextPane textPane = new JTextPane();
			if (i == ltext.size() - 1) {
				StyledDocument doc = textPane.getStyledDocument();
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
			}
			textPane.setText(ltext.get(i));
			textPane.setForeground(Color.YELLOW);
			textPane.setFont(app.getFont().deriveFont(Font.BOLD, 14));
			textPane.setEditable(false);
			textPane.setOpaque(false);
			textPane.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
			panel.add(textPane);
		}

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		rightButton = new HashButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, app.getFont());
		bottomPanel.add(rightButton, BorderLayout.EAST);

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		label.animateButton(() -> this.app.show(new ChooseTypeView(app)));
	}
	
	
	@Override
	public void hashButtonPressed() {
		label.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}
}
