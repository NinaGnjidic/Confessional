package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.CHOOSE_DETAILS_TITLE;
import java.util.List;

import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ChooseDetailsView extends StatefulPanelWithButtons<Detail> {

	private static final long serialVersionUID = -2813996135449968932L;

	public ChooseDetailsView(StatefulApplication app, List<Detail> details) {
		super(app, details, 4, true, CHOOSE_DETAILS_TITLE);
	}

	@Override
	protected boolean isDataSelected(Detail data) {
		return this.app.isInSelected(data);
	}

	@Override
	protected void onDataSelected(Detail data) {
		this.app.addToSelected(data);
	}

	@Override
	protected void onDataUnSelected(Detail data) {
		this.app.removeFromSelected(data);
	}

	public void hashButtonPressed() {
		if(isLastPage())
			rightButton.animateButton(() -> this.app.show(new ChooseCategoryView(app)));
	}

	@Override
	protected void handleDone() {
		rightButton.animateButton(() -> this.app.show(new ChooseCategoryView(app)));
	}
	

}
