package main.java.app.model;

import java.util.Objects;

public class Type implements Displayable {

	public String name;
	
	public Type() {}

	public Type(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Type)
			return Objects.equals(this.name, ((Type)other).name);
		return false;
	}
	
	

}
