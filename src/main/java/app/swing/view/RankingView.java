package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.java.app.EnvironmentVariables;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.button.HashButton;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.RankingService;

public class RankingView extends StatefulPanel {

	private static final long serialVersionUID = -5340989876577627372L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_ranking.jpg";
	private static final Image BACKGROUND_IMAGE = new ImageIcon(RankingView.class.getResource(BACKGROUND_IMAGE_PATH)).getImage();
	
	private Button rightButton;

	protected RankingView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(120, 120, 100, 140));

		this.setFocusable(true);
		this.requestFocusInWindow();
		JTextArea textArea = new JTextArea(rankingText());
		textArea.setFont(app.getFont().deriveFont(Font.BOLD, 50));
		textArea.setForeground(Color.black);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setColumns(10);
		textArea.setBorder(BorderFactory.createEmptyBorder(250, 300, 0, 50));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setOpaque(false);
		centerPanel.add(textArea);
		this.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		rightButton = new HashButton(EnvironmentVariables.PREVIOUS_BUTTON_LABEL, app.getFont());
		bottomPanel.add(rightButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	private static String rankingText() {
		List<Integer> scores = RankingService.loadScores();
		StringBuilder rankText = new StringBuilder();
		for (int i = 0; i < scores.size(); i++) {
			rankText.append((i + 1)).append(". ").append(scores.get(i)).append("\n");
		}
		return rankText.toString();
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}

}
