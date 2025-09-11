package main.java.app.swing.listener;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public interface CoinListener extends SerialPortMessageListener {
	@Override
	public default int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public default byte[] getMessageDelimiter() {
		return new byte[] { '\r', '\n' };
	}

	@Override
	public default boolean delimiterIndicatesEndOfMessage() {
		return true;
	}

	@Override
	public default void serialEvent(SerialPortEvent event) {
		byte[] data = event.getReceivedData();
		String message = new String(data).strip();
		float coinValue = Float.parseFloat(message);
		onCoinInsert(coinValue);
	}
	
	public void onCoinInsert(float coinValue);
	
	public default SerialPort getSerialPort() {
		SerialPort[] serialPorts = SerialPort.getCommPorts();
		if (serialPorts.length == 0)
			return null;
		SerialPort serialPort = serialPorts[0];
		serialPort.setBaudRate(9600);
		if (serialPort.openPort()) {
			System.out.println("Port opened successfully.");
		} else {
			System.out.println("Failed to open port.");
		}
		return serialPort;
	}
	
}
