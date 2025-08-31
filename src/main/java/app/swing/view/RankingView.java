package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.BackButton;
import main.java.app.swing.button.ImageTextPanel;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.RankingService;

public class RankingView extends StatefulPanel {

    private static final long serialVersionUID = -5340989876577627372L;

	private static final String RANKING_BACKGROUND_IMAGE_PATH = "/images/background_ranking.png";

    protected RankingView(StatefulApplication app) {
        super(app, RANKING_BACKGROUND_IMAGE_PATH);
    }

    @Override
    public void handleDisplay() {
        this.setLayout(new BorderLayout());

        List<Integer> scores = RankingService.loadScores();
        StringBuilder rankText = new StringBuilder();
        for (int i = 0; i < scores.size(); i++) {
            rankText.append((i + 1)).append(". ").append(scores.get(i)).append("\n");
        }

        JTextArea textArea = new JTextArea(rankText.toString());
        textArea.setFont(new Font(app.getFontName(), Font.PLAIN, 20));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setColumns(10);
        textArea.setBorder(BorderFactory.createEmptyBorder(250, 50, 0, 50));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(textArea);
        this.add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		ImageTextPanel backButton = new BackButton(new Font(app.getFontName(), Font.PLAIN, 10));
	    bottomPanel.add(backButton, BorderLayout.EAST);
	    this.add(bottomPanel, BorderLayout.SOUTH);
	    
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void onHash() {
        this.app.show(new InsertCoinView(app));
    }

}
