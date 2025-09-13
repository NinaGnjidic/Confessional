package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.PREVIOUS_BUTTON_LABEL;
import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TEXT;
import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TITLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.frame.StatefulPanel;

public class InstructionsView extends StatefulPanel {

	private static final long serialVersionUID = 2661132117794606000L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_instructions.jpg";
	private Button rightButton;
	private Button label;

	protected InstructionsView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, INSTRUCTIONS_TITLE, INSTRUCTIONS_TEXT);
	}
	
	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(175, 120, 100, 140));
		
		label = new Button(INSTRUCTIONS_TITLE, null, app.getFont().deriveFont(Font.BOLD, 100));
		label.setTextColor(Color.blue);
		label.hasShadow = true;
		this.add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea(INSTRUCTIONS_TEXT);
		textArea.setForeground(Color.YELLOW);
		textArea.setFont(app.getFont().deriveFont(Font.BOLD, 24));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBorder(BorderFactory.createEmptyBorder(100, 170, 0, 160));
		this.add(textArea, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new BorderLayout(100, 100));
		bottomPanel.setOpaque(false);

		rightButton = new HashButton(PREVIOUS_BUTTON_LABEL, app.getFont());
		bottomPanel.add(rightButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
				
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}

}
