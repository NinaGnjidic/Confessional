package main.java.app.util;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class CoinListener implements SerialPortMessageListener {
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public byte[] getMessageDelimiter() {
		return new byte[] { (byte) '\r', (byte) '\n' };
	}

	@Override
	public boolean delimiterIndicatesEndOfMessage() {
		return true;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		byte[] data = event.getReceivedData();
		String message = new String(data).strip();
		System.out.println(message);
	}
}
