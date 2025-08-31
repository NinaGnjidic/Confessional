package main.java.app.swing.view;

import java.awt.Component;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PrivacyPolicyView extends StatefulPanel {

	private static final long serialVersionUID = -7773204528442122200L;
	
	private static final String BACKGROUND_IMAGE_PATH = "/images/background_privacy.png";
	private static final String TITLE = "PRIHVAĆANJE ZAŠTITE PRIVATNOSTI SAKRAMENTALNIH PODATAKA";
	private static final String TEXT = "Dobrodošli u automatsku ispovjedaonicu ''On the go'' najbrži i najefikasniji način da se ispovjediš u pokretu, mjesto gdje su podatci vas i vaše duše sigurni i povjerljivi.\r\n"
			+ "\r\n"
			+ "Ovaj sustav koristi ''najviši stupanj'' zaštite osobnih i duhovnih podataka, u skladu sa ''Zakonikom kanonskog prava'' i Tajnim ispovjedi (Sigillum confessionis), posebno Kan. 983 §1.\r\n"
			+ "\r\n"
			+ "Vaša prava i naša obveza:\r\n"
			+ "\r\n"
			+ "Nepovredivost podataka (Kan. 983 §1): Ni riječ, ni šapat, ni pogled Vaše ispovijedi neće biti digitalno prenesen, reproduciran ili dijeljen. (Osim u svrhu poboljšanja kvalitete usluge i generalne analitike)\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "Nereproducibilnost (Kan. 984 §1): Ne bilježimo, ne arhiviramo niti dijelimo Vaše sakramentalne podatke. (Osim u svrhu poboljšanja kvalitete usluge i korisničkog iskustva ispovjedi.)\r\n"
			+ "\r\n"
			+ "Kaznena zaštita (Kan. 1386 §1): Svako kršenje povjerenja rezultira automatskim resetiranjem softvera, bez iznimki.\r\n"
			+ "\r\n"
			+ "Vaša privatnost je sveta. Ovdje niste samo korisnik. Ovdje ste duša u digitalnom svijetu. (left tekst/8pt/ white- #ffffff)\r\n"
			+ "Važno:\r\n"
			+ "Iako se sadržaj Vaše ispovijedi koliko je moguće strogo čuva i ne reproducira, sustav može koristiti anonimne tehničke podatke o načinu korištenja (poput vremena interakcije, tehničkih parametara i funkcionalnosti) isključivo u svrhu poboljšanja kvalitete usluge i korisničkog iskustva. Ovi podaci ne sadrže nikakve informacije o sadržaju Vaše ispovijedi i obrađuju se u skladu s važećim zakonima o zaštiti podataka. Podatci automatizirane ispovjedi su sigurni gotovo podjednako kao i kod pravog svećenika. (centre/8pt/white-#ffffff)\r\n"
			+ "\r\n"
			+ "Molimo vas da crvenim gumbom potvrdite da ste pročitali i prihvaćate uvjete zaštite privatnosti. (centre/8pt/white- #ffffff)\r\n"
			+ "";

	public PrivacyPolicyView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE_PATH, TITLE, TEXT);
	}

	@Override
	protected Component displayBottom() {
		return null;
	}

	@Override
	public void bigRedButtonPressed() {
		label.animateButton(() -> this.app.show(new ChooseTypeView(app)));
	}

}
