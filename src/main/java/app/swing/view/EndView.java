package main.java.app.swing.view;

import java.awt.Component;
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

	private static final String TITLE = "ISPOVIJEDI GRIJEHE";
	private static final String BACKGROUND_IMAGE_PATH = "/images/ISP CRVENI GUMB KRAJ ekran.png";
	
	private static final String SCORE_CONTENT_PREFIX = "You scored: ";
	private static final String RANK_CONTENT_PREFIX = "Congratulations! You’ve made it into the top 5 sinners! Your position in the top five is: ";

	String AIResponse;
	int score;
	int rank;

	public EndView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, TITLE, null);
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
		if (content != null && !content.trim().isEmpty())
			PrinterService.print(content);

		label.animateButton(() -> app.show(new InsertCoinView(app)));
	}
	
	private String createContent() {
		StringBuilder sb = new StringBuilder();

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
