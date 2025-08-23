package main.java.app.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Data {
	private final List<Category> categories;
	private final List<Detail> details;
	
	public Data() {
		this.categories = Collections.emptyList();
		this.details = Collections.emptyList();
	}
	
	public Data(List<Category> categories, List<Detail> details) {
		this.categories = categories;
		this.details = details;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Detail> getDetails() {
		return details;
	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}
//
//	public void setDetails(List<Detail> details) {
//		this.details = details;
//	}

	@JsonIgnore
	public Map<Category, List<Detail>> getDetailsPerCategory() {
		Map<Category, List<Detail>> detailsPerCategory = new LinkedHashMap<>();

		// TODO: details without existing categories will be excluded
		for (Category category : this.getCategories()) {
			List<Detail> details = this.getDetails().stream().filter(d -> d.getCategoryId() == category.getId()).collect(Collectors.toList());
			detailsPerCategory.put(category, details);
		}
		
		return detailsPerCategory;
	}
}
