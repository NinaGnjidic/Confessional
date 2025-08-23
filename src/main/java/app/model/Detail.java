package main.java.app.model;

public class Detail implements Displayable {
	private long id;
	private long categoryId;
	private String name;

	public Detail() {
	}

	public Detail(long id, long categoryId, String name) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Detail) {
			return ((Detail) obj).getId() == this.getId();
		}
		return false;
	}
}
