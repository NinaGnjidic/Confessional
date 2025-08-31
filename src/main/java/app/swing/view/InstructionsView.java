package main.java.app.swing.view;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class InstructionsView extends StatefulPanel {

	private static final long serialVersionUID = 2661132117794606000L;

	private static final String BACKGROUND_IMAGE_PATH = "/images/UPUTE s upitnicima.png";
	private static final String TITLE = "UPUTE";
	private static final String TEXT = "Dobrodošli u automatsku ispovjedaonicu.\r\n"
			+ "Nakon što ubacite kovanicu kao milodar u željenom iznosu i prihvatite obrazac o privatnosti ispovijedi, dolazite do sučelja koje vam nudi odabir grijeha prema njihovim odgovarajućim kategorijama.\r\n"
			+ "Odaberite grijehe koji vam imaju smisla – možete odabrati više grijeha. Nakon odabira svakog grijeha, pritiskom na tipku # na tipkovnici možete se vratiti natrag i odabrati grijehe iz drugih kategorija.\r\n"
			+ "\r\n"
			+ "Grijesi se bilježe prema bodovnom sustavu. Na kraju ispovijedi dobit ćete listić \"AI svećenika\" s analizom i komentarom na vaše grijehe te ukupnim brojem \"griješnih bodova\", koji se prevode u broj molitvi koje je potrebno izmoliti kako bi vam grijesi bili oprošteni.\r\n"
			+ "\r\n"
			+ "Također, na početnom ekranu, pritiskom na # možete pogledati rang-listu najvećih grješnika.   ";

	protected InstructionsView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, TITLE, TEXT);
	}

	@Override
	public void hashButtonPressed() {
		rightButton.animateButton(() -> this.app.show(new InsertCoinView(app)));
	}

}
