package main.java.app.swing.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.java.app.model.Category;
import main.java.app.model.Detail;
import main.java.app.state.StatefulApplication;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.AIService;
import main.java.app.util.PrinterService;

public class EndView extends StatefulPanel{

	private static final long serialVersionUID = 5201383428151921653L;

	public EndView(StatefulApplication app) {
		super(app);
	}

	@Override
	public void handleDisplay() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Good job! AI God will not determine how naughty you were... Press any key!", SwingConstants.CENTER);
		this.add(label);
	}

	@Override
	public void handleInput() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String content = createDetailsPerCategoryString();
				System.out.println(content);
				try {
					String aiResponse = AIService.confessional(content);
					content = content + "\n" + aiResponse;
				} catch (IOException | InterruptedException e1) {
					System.out.println("woops! something went wrong while talking with AI");
					e1.printStackTrace();
				}
				try {
					PrinterService.print(content);
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("woops! something went wrong while printing");
				}
				
				update();
			}
		});
		
		
	}
	
	private String createDetailsPerCategoryString() {
		Map<Category, List<Detail>> detailsPerCategory = app.getSelectedDeatilsPerCategory();
		
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Category, List<Detail>> e : detailsPerCategory.entrySet()) {
			Category category = e.getKey();
			sb.append(category.getName()).append("\n");
			for(Detail detail : e.getValue()) {
				sb.append("\t").append(detail.getName()).append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public void update() {
		this.app.show(new InsertCoinView(app));
	}

}
