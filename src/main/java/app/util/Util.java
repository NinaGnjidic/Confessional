package main.java.app.util;

public class Util {

	
	public static Long parseId(String input) {
		Long categoryId = null;
		try {
			categoryId = Long.parseLong(input.trim());
		} catch (NumberFormatException e) {
			categoryId = Long.MIN_VALUE;
		}
		return categoryId;
	}
}
