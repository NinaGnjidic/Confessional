package main.java.app.model;

public class Category implements Displayable{

	private long id;
	private String name;
	private String type;

	public Category() {}

	public Category(long id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
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
