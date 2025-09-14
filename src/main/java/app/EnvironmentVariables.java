package main.java.app;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentVariables {
	private static final String ENV_PATH = System.getProperty("env.path", ".");

	private static final Dotenv ENV = Dotenv.configure().directory(ENV_PATH).filename(".env").load();

	public static final String PRINTER = ENV.get("PRINTER");
	public static final String AI_API_KEY = ENV.get("AI_API_KEY");
	public static final String AI_PROMPT = ENV.get("AI_PROMPT");
	public static final String AI_PROMPT_ID = ENV.get("AI_PROMPT_ID");
	
	public static final String PRINT_TITLE = ENV.get("PRINT_TITLE");
	public static final String PRINT_NO_SINS = ENV.get("PRINT_NO_SINS");
	public static final String PRINT_NO_SINS_MESSAGES = ENV.get("PRINT_NO_SINS_MESSAGES");
	public static final String PRINT_PRAYERS = ENV.get("PRINT_PRAYERS");
	public static final String PRINT_RANKING = ENV.get("PRINT_RANKING");
	public static final String PRINT_COINS = ENV.get("PRINT_COINS");
	
	public static final String NEXT_BUTTON_LABEL = ENV.get("NEXT_BUTTON_LABEL");
	public static final String PREVIOUS_BUTTON_LABEL = ENV.get("PREVIOUS_BUTTON_LABEL");
	public static final String DONE_BUTTON_LABEL = ENV.get("DONE_BUTTON_LABEL");
	public static final String MAX_SCORES = ENV.get("MAX_SCORES");

	public static final String INSERT_COIN_TITLE = ENV.get("INSERT_COIN_TITLE");
	public static final String INSERT_COIN_TEXT = ENV.get("INSERT_COIN_TEXT");
	public static final String INSERT_COIN_LEFT_BUTTON_TEXT = ENV.get("INSERT_COIN_LEFT_BUTTON_TEXT");
	public static final String INSERT_COIN_RIGHT_BUTTON_TEXT = ENV.get("INSERT_COIN_RIGHT_BUTTON_TEXT");
	public static final String INSTRUCTIONS_TITLE = ENV.get("INSTRUCTIONS_TITLE");
	public static final String INSTRUCTIONS_TEXT = ENV.get("INSTRUCTIONS_TEXT");
	public static final String PRIVACY_TITLE = ENV.get("PRIVACY_TITLE");
	public static final String PRIVACY_TEXT = ENV.get("PRIVACY_TEXT");
	public static final String CHOOSE_TYPE_TITLE = ENV.get("CHOOSE_TYPE_TITLE");
	public static final String CHOOSE_CATEGORY_TITLE = ENV.get("CHOOSE_CATEGORY_TITLE");
	public static final String CHOOSE_DETAILS_TITLE = ENV.get("CHOOSE_DETAILS_TITLE");
	public static final String LOADING_TITLE = ENV.get("LOADING_TITLE");
	public static final String END_TITLE = ENV.get("END_TITLE");

	public static final String SCORE_CONTENT_PREFIX = ENV.get("SCORE_CONTENT_PREFIX");
	public static final String RANK_CONTENT_PREFIX = ENV.get("RANK_CONTENT_PREFIX");
}
