package main.java.app.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface ButtonListener extends KeyListener {
	@Override
	default void keyPressed(KeyEvent event) {
		char character = event.getKeyChar();
		switch (character) {
		case '0':
			button0Pressed();
			break;
		case '1':
			button1Pressed();
			break;
		case '2':
			button2Pressed();
			break;
		case '3':
			button3Pressed();
			break;
		case '4':
			button4Pressed();
			break;
		case '5':
			button5Pressed();
			break;
		case '6':
			button6Pressed();
			break;
		case '7':
			button7Pressed();
			break;
		case '8':
			button8Pressed();
			break;
		case '9':
			button9Pressed();
			break;
		case '*':
			starButtonPressed();
			break;
		case '#':
			hashButtonPressed();
			break;
		}

		int code = event.getKeyCode();
		int bigRedButtonCode = 10;
		if (code == bigRedButtonCode) {
			bigRedButtonPressed();
		}
	}

	default void button0Pressed() {
	}

	default void button1Pressed() {
	}

	default void button2Pressed() {
	}

	default void button3Pressed() {
	}

	default void button4Pressed() {
	}

	default void button5Pressed() {
	}

	default void button6Pressed() {
	}

	default void button7Pressed() {
	}

	default void button8Pressed() {
	}

	default void button9Pressed() {
	}

	default void starButtonPressed() {
	}

	default void hashButtonPressed() {
	}

	default void bigRedButtonPressed() {
	}

	@Override
	public default void keyReleased(KeyEvent e) {
	}

	@Override
	public default void keyTyped(KeyEvent e) {
	}
}
