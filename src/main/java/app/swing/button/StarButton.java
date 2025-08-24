package main.java.app.swing.button;

import java.awt.Font;

public class StarButton extends ImageTextPanel {

	private static final long serialVersionUID = -7754726524244795676L;
	
	private static final String STAR_BUTTON_IMAGE = "/images/button_star.png";

	public StarButton(String text, Font font) {
		super(text, STAR_BUTTON_IMAGE, font);
	}

}
