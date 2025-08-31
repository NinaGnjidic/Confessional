package main.java.app.swing.view;

import main.java.app.model.Category;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ChooseCategoryView extends StatefulPanelWithButtons<Category> {

	private static final long serialVersionUID = 5417688993812029835L;

	protected ChooseCategoryView(StatefulApplication app) {
		super(app, app.getCategoriesPerType(), 4);
	}

	@Override
	protected boolean isDataSelected(Category data) {
		return false;
	}

	@Override
	protected void onDataSelected(Category data) {
		this.app.show(new ChooseDetailsView(app, app.getDeatilsPerCategory().get(data)));
	}

	@Override
	protected void onDataUnSelected(Category data) {
		onDataSelected(data);
	}
	
	@Override
	public void hashButtonPressed() {
		handleDone();
	}

	@Override
	protected void handleDone() {
		this.app.show(new ChooseTypeView(app));
	}
	
}
