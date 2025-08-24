package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.BackButton;
import main.java.app.swing.button.ImageTextPanel;
import main.java.app.swing.frame.StatefulPanel;

public class InstructionsView extends StatefulPanel {

	private static final long serialVersionUID = 2661132117794606000L;

	private static final String INSTRUCTIONS_BACKGROUND_IMAGE_PATH = "/images/background_instructions.jpg";
	private static final String TITLE = "Upute";
	private static final String TEXT = "Prije korištenja, duboko udahnite i uvjerite se da ste spremni za avanturu. Ne gurajte sadržaj u uši, nos ili frižider – nije predviđeno za to. Korištenje bez osmijeha može smanjiti učinkovitost do 73%. Ako se sadržaj počne smijati sam od sebe, nemojte paničariti – to je normalno. Ne odgovaramo za slučajeve pretjeranog oduševljenja, plesanja ili naglog shvaćanja genijalnosti. Zadržavamo pravo izmjene uputa u bilo kojem trenutku, posebno ako se pojavi vanzemaljac.";

	protected InstructionsView(StatefulApplication app) {
		super(app, INSTRUCTIONS_BACKGROUND_IMAGE_PATH);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel(TITLE, SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font(app.getFontName(), Font.BOLD, 35));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		this.add(titleLabel, BorderLayout.NORTH);

		JTextArea textArea = new JTextArea(TEXT);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font(app.getFontName(), Font.PLAIN, 20));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBorder(BorderFactory.createEmptyBorder(100, 170, 0, 160));
		this.add(textArea, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		ImageTextPanel backButton = new BackButton(new Font(app.getFontName(), Font.PLAIN, 10));
		bottomPanel.add(backButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	@Override
	public void handleInput() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public void onHash() {
		this.update();
	}

	@Override
	public void update() {
		this.app.show(new InsertCoinView(app));
	}

}
