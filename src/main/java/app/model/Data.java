package main.java.app.model;

import java.util.ArrayList;
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
	
	public List<Type> getTypes() {
		return categories.stream().map(Category::getType).distinct().collect(Collectors.toList());
	}
	
	@JsonIgnore
	public Map<Type, List<Category>> getCategoriesPerType() {
		Map<Type, List<Category>> categoriesPerType = new LinkedHashMap<>();

		for (Category category : this.getCategories()) {
			categoriesPerType.computeIfAbsent(category.getType(), (v) -> new ArrayList<Category>()).add(category);
		}
		
		return categoriesPerType;
	}
	
	@JsonIgnore
	public Map<Category, List<Detail>> getDetailsPerCategory() {
		Map<Category, List<Detail>> detailsPerCategory = new LinkedHashMap<>();

		for (Category category : this.getCategories()) {
			List<Detail> details = this.getDetails().stream().filter(d -> d.getCategoryId() == category.getId()).collect(Collectors.toList());
			detailsPerCategory.put(category, details);
		}
		
		return detailsPerCategory;
	}
}
