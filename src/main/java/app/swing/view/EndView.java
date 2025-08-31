package main.java.app.swing.view;

import java.awt.Component;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.app.model.Category;
import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.AIService;
import main.java.app.util.PrinterService;

public class EndView extends StatefulPanel {

	private static final long serialVersionUID = 5201383428151921653L;

	private static final String TITLE = "Calculating...";

	String printContent;

	public EndView(StatefulApplication app) {
		super(app, TITLE, null);
	}

	@Override
	public void processData() {
		String content = createDetailsPerCategoryString();
		printContent = AIService.confessional(content);
	}

	private String createDetailsPerCategoryString() {
		Map<Category, List<Detail>> detailsPerCategory = app.getSelectedDeatilsPerCategory();

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Category, List<Detail>> e : detailsPerCategory.entrySet()) {
			Category category = e.getKey();
			sb.append(category.getName()).append("\n");
			for (Detail detail : e.getValue()) {
				sb.append("\t").append(detail.getName()).append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	protected Component displayCenter() {
		JLabel responseLabel = new JLabel(
				"<html><div style='text-align: center;'>" + printContent.replace("\n", "<br>") + "</div></html>",
				SwingConstants.CENTER);
		responseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		responseLabel.setVerticalAlignment(SwingConstants.CENTER);

		return responseLabel;
	}

	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		if (printContent != null && !printContent.trim().isEmpty())
			PrinterService.print(printContent);

		label.animateButton(() -> app.show(new InsertCoinView(app)));
	}

}
