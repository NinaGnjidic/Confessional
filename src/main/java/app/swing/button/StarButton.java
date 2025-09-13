package main.java.app.swing.button;

import java.awt.Font;

public class StarButton extends Button {

	private static final long serialVersionUID = -7754726524244795676L;

	private static final String ICON_IMAGE_PATH = "/images/icons/star.png";

	public StarButton(String text, Font font) {
		super(text, font.deriveFont(Font.BOLD, 18), ICON_IMAGE_PATH);
	}

}
