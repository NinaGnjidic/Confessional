package main.java.app.util;

import static main.java.app.EnvironmentVariables.AI_API_KEY;
import static main.java.app.EnvironmentVariables.AI_MODEL;
import static main.java.app.EnvironmentVariables.AI_PROMPT;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class AIService {

	private static final OpenAIClient OPEN_AI_CLIENT = OpenAIOkHttpClient.builder().apiKey(AI_API_KEY).build();

	public static String confessional(String content) {
		String userMessage = AI_PROMPT + content;
		String model = AI_MODEL;

		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder().addUserMessage(userMessage).model(model).build();

		ChatCompletion completion = OPEN_AI_CLIENT.chat().completions().create(params);
		String output = completion.choices().get(0).message().content().orElse("");

		System.out.println(output);

		return output;
	}
}