package main.java.app.swing.listener;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public interface CoinListener extends SerialPortMessageListener {
	static SerialPort serialPort = getSerialPort();
	
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
		final float coinValue = Float.parseFloat(message);

		SwingUtilities.invokeLater(()->onCoinInsert(coinValue));
	}

	public void onCoinInsert(float coinValue);

	public static SerialPort getSerialPort() {
		SerialPort[] serialPorts = SerialPort.getCommPorts();
		if (serialPorts.length == 0)
			return null;
		if (serialPort != null && serialPort.isOpen())
			return serialPort;
		SerialPort serialPort = SerialPort.getCommPort("COM8");
		serialPort.setBaudRate(9600);
		if (!serialPort.isOpen()) {
			if (serialPort.openPort()) {
				System.out.println("Port opened successfully.");
			} else {
				System.out.println("Failed to open port.");
			}
		}
		return serialPort;
	}
}
