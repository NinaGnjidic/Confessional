package main.java.app.swing.button;

import java.awt.Font;

public class HashButton extends Button {

	private static final long serialVersionUID = 6522338955043211588L;

	private static final String ICON_IMAGE_PATH = "/images/icons/hashtag.png";

	public HashButton(String text, Font font) {
		super(text, font.deriveFont(Font.BOLD, 18), ICON_IMAGE_PATH);
	}

}
