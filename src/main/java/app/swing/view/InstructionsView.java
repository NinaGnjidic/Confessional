package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.PREVIOUS_BUTTON_LABEL;
import static main.java.app.EnvironmentVariables.PRIVACY_TEXT;
import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TEXT;
import static main.java.app.EnvironmentVariables.INSTRUCTIONS_TITLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
		this.setBorder(new EmptyBorder(130, 120, 100, 140));
		
		label = new Button(INSTRUCTIONS_TITLE, null, app.getFont().deriveFont(Font.BOLD, 80));
		label.setTextColor(Color.blue);
		label.hasShadow = true;
		this.add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.setOpaque(false);
		this.add(panel, BorderLayout.CENTER);
		
		String text = INSTRUCTIONS_TEXT;
		String rtext = text.replace("\\n", "\n");
		List<String> ltext = Arrays.asList(rtext.split("\n\n\n"));
		for (int i = 0; i < ltext.size(); i++) {
			JTextPane textPane = new JTextPane();
			if (i == ltext.size() - 1) {
				StyledDocument doc = textPane.getStyledDocument();
				SimpleAttributeSet center = new SimpleAttributeSet();
//				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
			}
			textPane.setText(ltext.get(i));
			textPane.setForeground(Color.YELLOW);
			textPane.setFont(app.getFont().deriveFont(Font.BOLD, 18));
			textPane.setEditable(false);
			textPane.setOpaque(false);
			textPane.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
			panel.add(textPane);
		}
		
//		JTextArea textArea = new JTextArea(INSTRUCTIONS_TEXT);
//		textArea.setForeground(Color.YELLOW);
//		textArea.setFont(app.getFont().deriveFont(Font.BOLD, 24));
//		textArea.setWrapStyleWord(true);
//		textArea.setLineWrap(true);
//		textArea.setEditable(false);
//		textArea.setOpaque(false);
//		textArea.setBorder(BorderFactory.createEmptyBorder(100, 170, 0, 160));
//		this.add(textArea, BorderLayout.CENTER);
		
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
