package main.java.app;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
	private static final Dotenv ENV = Dotenv.load();
	
	public static final String PRINTER = ENV.get("PRINTER");
	public static final String PRINTER_ENCODING = ENV.get("PRINTER_ENCODING");
	public static final String CONFESSIONAL_API_KEY = ENV.get("CONFESSIONAL_API_KEY");
	public static final String DATA_JSON = ENV.get("DATA_JSON");

	public static final String MAX_SCORES = ENV.get("MAX_SCORES");
	
	public static final String NEXT_BUTTON_LABEL = ENV.get("NEXT_BUTTON_LABEL");
	public static final String PREVIOUS_BUTTON_LABEL = ENV.get("PREVIOUS_BUTTON_LABEL");
	public static final String DONE_BUTTON_LABEL = ENV.get("DONE_BUTTON_LABEL");
	
	private EnvConfig() {}
}
