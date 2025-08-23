package main.java.app.model;

public class Category implements Displayable{

	private long id;
	private String name;

	public Category() {}

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
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
		if(obj != null && obj instanceof Category) {
			return ((Category)obj).getId() == this.getId();
		}
		return false;
	}
	
	
}
