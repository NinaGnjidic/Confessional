package main.java.app.swing.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.RankingService;

public class RankingView extends StatefulPanel {

	private static final long serialVersionUID = -5340989876577627372L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/background_ranking.png";

	protected RankingView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, null, null);
	}

	@Override
	public Component displayCenter(String text) {
		JTextArea textArea = new JTextArea(rankingText());
		textArea.setFont(app.getFont());
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setColumns(10);
		textArea.setBorder(BorderFactory.createEmptyBorder(250, 50, 0, 50));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setOpaque(false);
		centerPanel.add(textArea);
		return centerPanel;
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
