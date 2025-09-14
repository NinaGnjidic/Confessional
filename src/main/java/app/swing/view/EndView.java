package main.java.app.swing.view;


import static main.java.app.EnvironmentVariables.END_TITLE;
import static main.java.app.EnvironmentVariables.SCORE_CONTENT_PREFIX;
import static main.java.app.EnvironmentVariables.RANK_CONTENT_PREFIX;

import java.awt.Component;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.AIService;
import main.java.app.util.PrinterService;
import main.java.app.util.RankingService;

public class EndView extends StatefulPanel {

	private static final long serialVersionUID = 5201383428151921653L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_end.jpg";
	
	String AIResponse;
	int score;
	int rank;

	public EndView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, END_TITLE, null);

		app.playSound("/sounds/exit.mp3");
	}

	@Override
	public void processData() {
		String content = app.createDetailsPerCategoryString();
		this.AIResponse = AIService.confessional(content);

		List<Detail> selecedDetails = app.getSelectedDeatilsPerCategory().values().stream().flatMap(List::stream).collect(Collectors.toList());
		this.score = selecedDetails.stream().mapToInt(Detail::getPoints).sum();
		this.rank = RankingService.addScore(score);
	}

	@Override
	protected Component displayCenter(String text) {
		return super.displayCenter(createContent());
	}

	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		String content = createContent();
		if (content != null && !content.trim().isEmpty()) {
			List<Detail> sins = app.getSelectedDeatilsPerCategory().values().stream().flatMap(List::stream).collect(Collectors.toList());			
			try {
				PrinterService.print(sins, this.AIResponse, this.score, this.rank, app.getInsertedCoins());
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
		
		if(this.AIResponse != null && !this.AIResponse.isEmpty())
			sb.append(this.AIResponse);
		
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
