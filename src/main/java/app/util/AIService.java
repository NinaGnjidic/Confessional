package main.java.app.util;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import main.java.app.EnvironmentVariables;

public class AIService {

	private static final OpenAIClient OPEN_AI_CLIENT = OpenAIOkHttpClient.builder().apiKey(EnvironmentVariables.AI_API_KEY).build();

	public static String confessional(String content) {
		String userMessage = EnvironmentVariables.AI_PROMPT + ":" + content;
		String model = "gpt-5-nano";
		
		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
				.addUserMessage(userMessage).model(model)
				.build();

		ChatCompletion completion = OPEN_AI_CLIENT.chat().completions().create(params);
		String output = completion.choices().get(0).message().content().orElse("");

		System.out.println(output);

		return output;
	}
}