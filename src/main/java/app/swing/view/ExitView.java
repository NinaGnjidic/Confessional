package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.END_TEXT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ExitView extends StatefulPanel {

	private static final long serialVersionUID = -6705770994443136416L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_default.jpg";
	private static final Image BACKGROUND_IMAGE = new ImageIcon(StatefulPanelWithButtons.class.getResource(BACKGROUND_IMAGE_PATH)).getImage();

	private Timer timer;

	public ExitView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE);
		startTimer(20000, e -> exit());
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(300, 170, 100, 170));
		
		JTextPane textPane = new JTextPane();
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		textPane.setText(END_TEXT.replace("\\n", "\n"));
		textPane.setForeground(Color.YELLOW);
		textPane.setFont(app.getFont().deriveFont(Font.BOLD, 28));
		textPane.setEditable(false);
		textPane.setOpaque(false);
		textPane.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		this.add(textPane, BorderLayout.CENTER);
	}

	@Override
	public void bigRedButtonPressed() {
		stopTimer();
		exit();
	}
	
	private void exit() {
		app.clearSelected();
		app.stopSound();
		app.show(new InsertCoinView(app));
	}
	
	private void startTimer(int delay, ActionListener listener) {
		timer = new Timer(delay, listener);
		timer.setRepeats(false);
		timer.start();
	}

	private void stopTimer() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}
	}

}
