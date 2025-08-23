package main.java.app.state;

import main.java.app.util.KeyListener;

public interface State extends KeyListener {

    default void showState() {
    	preprocessData();
        handleDisplay();
        handleInput();
    }
    
    default void preprocessData() {}
    
    void handleDisplay();
    void handleInput();
    void update();
}
