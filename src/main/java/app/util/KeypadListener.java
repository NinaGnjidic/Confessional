package main.java.app.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface KeypadListener extends KeyListener {

	@Override
	default void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		char ch = e.getKeyChar();
		
		if (ch == '0') {
		    onButton0();
		} else if (ch == '1') {
		    onButton1();
		} else if (ch == '2') {
		    onButton2();
		} else if (ch == '3') {
		    onButton3();
		} else if (ch == '4') {
		    onButton4();
		} else if (ch == '5') {
		    onButton5();
		} else if (ch == '6') {
		    onButton6();
		} else if (ch == '7') {
		    onButton7();
		} else if (ch == '8') {
		    onButton8();
		} else if (ch == '9') {
		    onButton9();
		} else if (ch == '*' || code == 56) {
		    onStar();
		} else if (ch == '#' || code == 51) {
		    onHash();
		} else if (code == 10) {
		    onRedButton();
		}
	}

	@Override
	public default void keyReleased(KeyEvent e) {}

	@Override
	public default void keyTyped(KeyEvent e) {}

	default void onButton0() {}
	default void onButton1() {}
	default void onButton2() {}
	default void onButton3() {}
	default void onButton4() {}
	default void onButton5() {}
	default void onButton6() {}
	default void onButton7() {}
	default void onButton8() {}
	default void onButton9() {}
	default void onStar() {}
	default void onHash() {}
	default void onRedButton() {}
}
