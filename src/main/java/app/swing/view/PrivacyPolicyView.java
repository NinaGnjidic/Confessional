package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;

public class PrivacyPolicyView extends StatefulPanel {

	private static final long serialVersionUID = -7773204528442122200L;
	private static final String TITLE = "Suglasnost privatnosti";
	private static final String TEXT = "Korištenjem ovog sadržaja prihvaćate uvjete i dajete suglasnost za njegovo korištenje u skladu s navedenom svrhom. Neovlašteno umnožavanje, dijeljenje ili zlouporaba nije dopuštena. Organizator/autor zadržava pravo izmjene ili povlačenja suglasnosti u bilo kojem trenutku.";

	public PrivacyPolicyView(StatefulApplication app) {
		super(app);
	}

	@Override
    public void handleDisplay() {
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(TITLE, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(app.getFontName(), Font.BOLD, 35));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        this.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(TEXT);
//        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(app.getFontName(), Font.PLAIN, 20));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(100, 100, 0, 60));
        this.add(textArea, BorderLayout.CENTER);
        
		this.setFocusable(true);
	    this.requestFocusInWindow();
    }

	@Override
	public void onRedButton() {
		this.app.show(new ChooseTypeView(app));
	}

}
