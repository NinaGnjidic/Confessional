package main.java.app.util;

import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.app.EnvConfig;
import main.java.app.Main;
import main.java.app.model.Data;

public class DataReader {
	
	private DataReader() {};
	
	public static Optional<Data> readData() {
		Optional<Data> data = Optional.empty();
		
		try {
			InputStream is = Main.class.getClassLoader().getResourceAsStream(EnvConfig.DATA_JSON);
			ObjectMapper mapper = new ObjectMapper();
			data = Optional.of(mapper.readValue(is, Data.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}