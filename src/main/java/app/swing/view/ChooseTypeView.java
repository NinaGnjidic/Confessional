package main.java.app.swing.view;

import static main.java.app.EnvironmentVariables.CHOOSE_TYPE_TITLE;

import java.awt.Dimension;
import java.awt.Font;

import main.java.app.model.Type;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.ToggleButton;
import main.java.app.swing.frame.StatefulPanelWithButtons;

public class ChooseTypeView extends StatefulPanelWithButtons<Type>{

	private static final long serialVersionUID = 1125776317717562001L;
	
	public ChooseTypeView(StatefulApplication app) {
		super(app, app.getData().getTypes(), 1, CHOOSE_TYPE_TITLE);
	}
	
	@Override
	protected ToggleButton createDataButton(Type item, int index) {
		ToggleButton toggle = new ToggleButton((index + 1) + ". " + item.getName(), app.getFont().deriveFont(Font.BOLD, 20));
		toggle.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200));
		toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        boolean isSelected = isDataSelected(item);
        toggle.setSelected(isSelected);
        toggle.bindSelectionHandlers(() -> onDataSelected(item), () -> onDataUnSelected(item));
        return toggle;
	}
	
	@Override
	protected void displayControlsPanel() {}

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
//		label.animateButton(() -> this.app.show(new EndView(app)));

		label.animateButton(() -> this.app.show(new LoadingView(app)));
	}
	
}
