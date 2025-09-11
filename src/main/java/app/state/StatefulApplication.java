package main.java.app.state;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javazoom.jl.player.Player;
import main.java.app.model.Category;
import main.java.app.model.Data;
import main.java.app.model.Detail;
import main.java.app.model.Type;
import main.java.app.swing.frame.ApplicationFrame;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.DataReader;

public class StatefulApplication {

	private ApplicationFrame frame;
	private Font font;
	private Player soundPlayer;

	private Data data;
	private Type type;

	private float insertedCoins = 0.0f;

	Map<Category, List<Detail>> deatilsPerCategory;
	protected Map<Category, List<Detail>> selectedDeatilsPerCategory;
	private Map<Type, List<Category>> categoriesPerType;

	protected StatefulApplication(String title) throws FontFormatException, IOException {
		readData();
		this.frame = new ApplicationFrame(title);
		this.font = Font
				.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"))
				.deriveFont(Font.BOLD, 16);
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(this.font);
	}

	public Font getFont() {
		return this.font;
	}

	public String getFontName() {
		return this.font.getFontName();
	}

	public void start(StatefulPanel firstPanel) {
		this.frame.init(firstPanel);
	}

	// TODO
	private void readData() {
		Optional<Data> data = DataReader.readData();
		this.deatilsPerCategory = data.isPresent() ? data.get().getDetailsPerCategory() : new HashMap<>();
		this.categoriesPerType = data.isPresent() ? data.get().getCategoriesPerType() : new HashMap<>();
		this.data = data.isPresent() ? data.get() : null;
		this.selectedDeatilsPerCategory = new HashMap<>();
	}

	public List<Category> getCategoriesPerType() {
		return categoriesPerType.get(this.type);
	}

	public Map<Category, List<Detail>> getDeatilsPerCategory() {
		return deatilsPerCategory;
	}

	public Map<Category, List<Detail>> getSelectedDeatilsPerCategory() {
		return selectedDeatilsPerCategory;
	}

	public Data getData() {
		return this.data;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void show(StatefulPanel panel) {
		this.frame.show(panel);
	}

	public void addToSelected(Detail detail) {
		Category category = this.data.getCategories().stream().filter(c -> c.getId() == detail.getCategoryId())
				.findFirst().get();
		this.selectedDeatilsPerCategory.computeIfAbsent(category, v -> new ArrayList<>()).add(detail);
	}

	public void removeFromSelected(Detail detail) {
		Category category = this.data.getCategories().stream().filter(c -> c.getId() == detail.getCategoryId())
				.findFirst().get();
		this.selectedDeatilsPerCategory.get(category).removeIf(d -> d.getId() == detail.getId());
	}

	public boolean isInSelected(Detail detail) {
		Optional<Category> category = this.data.getCategories().stream()
				.filter(c -> c.getId() == detail.getCategoryId()).findFirst();
		if (category.isPresent()) {
			List<Detail> detailsInCategory = this.selectedDeatilsPerCategory.get(category.get());
			return detailsInCategory != null && detailsInCategory.contains(detail);
		}

		return false;
	}

	public String createDetailsPerCategoryString() {
		Map<Category, List<Detail>> detailsPerCategory = getSelectedDeatilsPerCategory();

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Category, List<Detail>> e : detailsPerCategory.entrySet()) {
			Category category = e.getKey();
			sb.append(category.getName()).append("\n");
			for (Detail detail : e.getValue()) {
				sb.append("\t").append(detail.getName()).append("\n");
			}
		}
		return sb.toString();
	}

	public void clearSelected() {
		this.type = null;
		this.selectedDeatilsPerCategory.clear();
		this.insertedCoins = 0.0f;
	}

	public void incrementInsertedCoins(float coinValue) {
		insertedCoins += coinValue;
	}

	public float getInsertedCoins() {
		return insertedCoins;
	}

	public void playSound(String soundResourcePath) {
		stopSound();
		try {
			soundPlayer = new Player(getClass().getResourceAsStream(soundResourcePath));
			new Thread(() -> {
				try {
					soundPlayer.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopSound() {
		if (soundPlayer != null) {
			soundPlayer.close();
		}
	}

}
