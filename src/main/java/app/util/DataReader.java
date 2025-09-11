package main.java.app.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.app.model.Data;

public class DataReader {
	
	private static final String DATA_PATH = System.getProperty("data.path", ".");
	
	private DataReader() {}
	
	public static Optional<Data> readData() {
		Optional<Data> data = Optional.empty();
		
		try {
			InputStream is = new FileInputStream(DATA_PATH);
			ObjectMapper mapper = new ObjectMapper();
			data = Optional.of(mapper.readValue(is, Data.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}