package main.java.app.swing.view;


import static main.java.app.EnvironmentVariables.END_TITLE;
import static main.java.app.EnvironmentVariables.RANK_CONTENT_PREFIX;
import static main.java.app.EnvironmentVariables.SCORE_CONTENT_PREFIX;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.border.EmptyBorder;

import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.PrinterService;
import main.java.app.util.RankingService;

public class EndView extends StatefulPanel {

	private static final long serialVersionUID = 5201383428151921653L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_end.jpg";
	
	private Button label;
	
	String printContent;
	int score;
	int rank;

	public EndView(StatefulApplication app, String printContent) {
		super(app, BACKGROUND_IMAGE_PATH, null, null);
		this.printContent = printContent;
		app.playSound("/sounds/exit.mp3");
	}

	@Override
	public void processData() {
		List<Detail> selecedDetails = app.getSelectedDeatilsPerCategory().values().stream().flatMap(List::stream).collect(Collectors.toList());
		this.score = selecedDetails.stream().mapToInt(Detail::getPoints).sum();
		this.rank = RankingService.addScore(score);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(120, 120, 100, 140));
		label = new Button(END_TITLE, null, app.getFont().deriveFont(Font.BOLD, 40));
		label.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200));
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		label.setTextColor(Color.WHITE);
		this.add(label, BorderLayout.NORTH);
	}

	@Override
	public void bigRedButtonPressed() {
		String content = createContent();
		if (content != null && !content.trim().isEmpty()) {
			List<Detail> sins = app.getSelectedDeatilsPerCategory().values().stream().flatMap(List::stream).collect(Collectors.toList());			
			try {
				PrinterService.printConfessional(sins, printContent, this.score, this.rank, app.getInsertedCoins());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.app.clearSelected();
		 app.show(new InsertCoinView(app));
	}
	
	private String createContent() {
		StringBuilder sb = new StringBuilder();

		//TODO: work in progress
		sb.append("Total: ").append(app.getInsertedCoins()).append("\n");
		
		String scoreContent = createScoreContent();
		if(scoreContent != null && !scoreContent.isEmpty())
			sb.append(scoreContent).append("\n");
		
		String rankingContent = createRankingContent();
		if(rankingContent != null && !rankingContent.isEmpty())
			sb.append(rankingContent).append("\n");
		
		if(this.printContent != null && !this.printContent.isEmpty())
			sb.append(this.printContent);
		
		return sb.toString();
	}
	
	private String createScoreContent() {
		return SCORE_CONTENT_PREFIX + this.score;
	}
	
	private String createRankingContent() {
		if(this.rank < 0)
			return "";
		int rankPosition = this.rank + 1;
		return RANK_CONTENT_PREFIX + rankPosition;
	}
}
