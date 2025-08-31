package main.java.app.swing.view;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class InstructionsView extends StatefulPanel {

	private static final long serialVersionUID = 2661132117794606000L;

	private static final String TITLE = "Upute";
	private static final String TEXT = "Prije korištenja, duboko udahnite i uvjerite se da ste spremni za avanturu. Ne gurajte sadržaj u uši, nos ili frižider – nije predviđeno za to. Korištenje bez osmijeha može smanjiti učinkovitost do 73%. Ako se sadržaj počne smijati sam od sebe, nemojte paničariti – to je normalno. Ne odgovaramo za slučajeve pretjeranog oduševljenja, plesanja ili naglog shvaćanja genijalnosti. Zadržavamo pravo izmjene uputa u bilo kojem trenutku, posebno ako se pojavi vanzemaljac.";

	protected InstructionsView(StatefulApplication app) {
		super(app, TITLE, TEXT);
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}

}
