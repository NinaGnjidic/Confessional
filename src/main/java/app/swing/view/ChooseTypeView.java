package main.java.app.swing.view;

import main.java.app.model.Type;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ChooseTypeView extends StatefulPanelWithButtons<Type>{

	private static final long serialVersionUID = 1125776317717562001L;

	protected ChooseTypeView(StatefulApplication app) {
		super(app, app.getData().getTypes(), 1, false);
	}

	@Override
	protected boolean isDataSelected(Type data) {
		return false;
	}
	
	@Override
	protected void onDataUnSelected(Type data) {
		onDataSelected(data);
	}

	@Override
	protected void onDataSelected(Type data) {
		this.app.setType(data);
		this.app.show(new ChooseCategoryView(app));
	}

	@Override
	public void bigRedButtonPressed() {
		this.app.show(new EndView(app));
	}
	
}
