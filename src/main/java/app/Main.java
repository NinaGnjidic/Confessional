package main.java.app;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.swing.view.InsertCoinView;

public class Main {

	public static void main(String[] args) throws FontFormatException, IOException {
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf")).deriveFont(Font.BOLD, 16);

		StatefulApplication app = new StatefulApplication("Ispovjedaonica", customFont) {};
		StatefulPanel firstPanel = new InsertCoinView(app);

		app.start(firstPanel);
	}

}
