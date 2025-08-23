package main.java.app.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.SwingUtilities;

import main.java.app.model.Category;
import main.java.app.model.Data;
import main.java.app.model.Detail;
import main.java.app.swing.frame.ApplicationFrame;
import main.java.app.swing.frame.StatefulPanel;
import main.java.app.util.DataReader;

public abstract class StatefulApplication {
	
	private ApplicationFrame frame;

	private Data data;
	
	Map<Category, List<Detail>> deatilsPerCategory;
	protected Map<Category, List<Detail>> selectedDeatilsPerCategory;

	protected StatefulApplication(String title) {
		readData();
		
		this.frame = new ApplicationFrame(title);
	}

	public void start(StatefulPanel firstPanel) {
		SwingUtilities.invokeLater(() -> this.frame.init(firstPanel));
	}

	// TODO
	private void readData() {
		Optional<Data> data = DataReader.readData();
		this.deatilsPerCategory = data.isPresent() ? data.get().getDetailsPerCategory() : new HashMap<>();
		this.data = data.isPresent() ? data.get() : null;
		this.selectedDeatilsPerCategory = new HashMap<>();
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
	
	public void show(StatefulPanel panel) {
		this.frame.show(panel);
	}
	
	public void addToSelected(Detail detail) {
		Category category = this.data.getCategories().stream().filter(c -> c.getId() == detail.getCategoryId()).findFirst().get();
		this.selectedDeatilsPerCategory.computeIfAbsent(category, v -> new ArrayList<>()).add(detail);
	}

	public void removeFromSelected(Detail detail) {
		Category category = this.data.getCategories().stream().filter(c -> c.getId() == detail.getCategoryId()).findFirst().get();
		this.selectedDeatilsPerCategory.get(category).removeIf(d -> d.getId() == detail.getId());
	}
	
	public boolean isInSelected(Detail detail) {
		Optional<Category> category = this.data.getCategories().stream().filter(c -> c.getId() == detail.getCategoryId()).findFirst();
		if(category.isPresent()) {
			List<Detail> detailsInCategory = this.selectedDeatilsPerCategory.get(category.get());
			return detailsInCategory != null && detailsInCategory.contains(detail);
		}
		
		return false;
	}

	public void clearSelected() {
		this.selectedDeatilsPerCategory.clear();
	}
}
