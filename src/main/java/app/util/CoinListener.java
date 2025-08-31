package main.java.app.util;

import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class CoinListener implements SerialPortDataListener {
	@Override
	public int getListeningEvents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		// TODO Auto-generated method stub
		
	}
}
