package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import main.java.app.EnvironmentVariables;
import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.button.Button;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.AIService;

public class LoadingView extends StatefulPanel {

	private static final long serialVersionUID = 5931481642815855714L;
	private static final Random RANDOM = new Random();
	private static final String BACKGROUND_IMAGE_PATH = "/images/bg_default.jpg";
	private static final Image BACKGROUND_IMAGE = new ImageIcon(LoadingView.class.getResource(BACKGROUND_IMAGE_PATH))
			.getImage();
	private static final String LOADING_ICON_PATH = "/images/icons/loading.gif";

	private Button label;

	public LoadingView(StatefulApplication app) {
		super(app, BACKGROUND_IMAGE);
	}

	@Override
	public void processData() {
		new Thread(() -> {
			boolean hasSelected = app.getSelectedDeatilsPerCategory().size() > 0;
			String printContent = "";
			if (hasSelected) {
				printContent = createAIPrintContent();
			} else {
				try {
					Thread.sleep(4000);// simulate delay
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				printContent = createNoneSelectedPrintContent();
			}

			final String content = printContent;
			SwingUtilities.invokeLater(() -> label.animateButton(() -> app.show(new EndView(app, content))));
		}).start();
	}

	private String createNoneSelectedPrintContent() {
		String rtext = EnvironmentVariables.PRINT_NO_SINS_MESSAGES.replace("\\n", "\n");
		List<String> messages = Arrays.asList(rtext.split("\n\n"));
		String randomMessage = messages.get(RANDOM.nextInt(messages.size()));
		System.out.println(randomMessage);
		return randomMessage;
	}

	private String createAIPrintContent() {
		try {
			String content = app.getSelectedDetails().stream().map(d -> d.getName()).collect(Collectors.joining(","));
			return AIService.confessional(content);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(100, 120, 100, 140));

		label = new Button(EnvironmentVariables.LOADING_TITLE, null, app.getFont().deriveFont(Font.BOLD, 50));
		label.setTextColor(Color.YELLOW);
		label.hasShadow = true;
		this.add(label, BorderLayout.NORTH);

		List<Detail> selectedDetails = app.getSelectedDetails();

		JPanel detailsContent = new JPanel();
		detailsContent.setOpaque(false);
		detailsContent.setLayout(new BoxLayout(detailsContent, BoxLayout.Y_AXIS));

		List<Button> detailButtons = new ArrayList<>();
		for (Detail detail : selectedDetails) {
			Button detailButton = new Button(detail.getName(), null, app.getFont());
			detailButton.setTextColor(Color.WHITE);
			detailButton.setVisible(false);
			detailButtons.add(detailButton);
			detailsContent.add(detailButton);
		}

		this.add(detailsContent, BorderLayout.SOUTH);

		ImageIcon img = new ImageIcon(getClass().getResource(LOADING_ICON_PATH));
		JLabel loadingIcon = new JLabel(img);
		this.add(loadingIcon, BorderLayout.CENTER);

		if (!detailButtons.isEmpty()) {
			Timer timer = new Timer(150, new ActionListener() {
				int index = 0; // keep track of which detail to show

				@Override
				public void actionPerformed(ActionEvent e) {
					for (Button b : detailButtons) {
						b.setVisible(false);
					}
					detailButtons.get(index).setVisible(true);
					index = (index + 1) % detailButtons.size();
					detailsContent.revalidate();
					detailsContent.repaint();
				}
			});

			timer.start();
		}
	}

}
