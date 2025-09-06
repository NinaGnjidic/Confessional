package main.java.app.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.app.EnvironmentVariables;

public class AIService {

	public static String confessional(String content){
		String input = EnvironmentVariables.AI_PROMPT + "/n" + content;

		// Escape characters that break JSON
		String escapedInput = input.replace("\\", "\\\\") // backslashes
				.replace("\"", "\\\"") // double quotes
				.replace("\n", "\\n") // newlines
				.replace("\r", "\\r") // carriage returns
				.replace("\t", "\\t"); // tabs

		String jsonInput = "{\n" + "  \"model\": \"openai/gpt-oss-20b\",\n" + "  \"input\": \"" + escapedInput + "\"\n"
				+ "}";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(EnvironmentVariables.AI_RESPONSE_URL))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + EnvironmentVariables.AI_API_KEY)
				.POST(BodyPublishers.ofString(jsonInput, StandardCharsets.UTF_8)).build();

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "";
		}

		String responseContent = response.body();
		System.out.println("Response JSON: " + responseContent);

		Response res;
		try {
			res = new ObjectMapper().readValue(responseContent, Response.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}

		return res.getOutput().stream().filter(o -> "message".equalsIgnoreCase(o.getType())).findFirst().get()
				.getContent().get(0).getText();
				
	}

	public static class Response {
		public String id;
		public String object;
		public String status;
		public String store;
		public long created_at;
		public List<Output> output;
		public String previous_response_id;
		public String model;
		public Reasoning reasoning;
		public Object max_output_tokens;
		public Text text;
		public List<Object> tools;
		public String tool_choice;
		public String truncation;
		public Metadata metadata;
		public Double temperature;
		public Double top_p;
		public Object user;
		public String service_tier;
		public boolean background;
		public Object error;
		public Object incomplete_details;
		public Usage usage;
		public boolean parallel_tool_calls;
		public int top_logprobs;
		public Object max_tool_calls;

		public Response() {
		};
		
		

		public List<Output> getOutput() {
			return output;
		}

		public void setOutput(List<Output> output) {
			this.output = output;
		}

	}

	public static class Output {
		public String type;
		public String id;
		public String status;
		public String role;
		public List<Content> content;
		public List<Object> summary;

		public Output() {
		};

		public List<Content> getContent() {
			return content;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setContent(List<Content> content) {
			this.content = content;
		}

	}

	public static class Content {
		public String type;
		public String text;
		public List<Object> annotations;
		public Object logprobs;

		public Content() {
		};

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	public static class Reasoning {

		public String effort;

		public Reasoning() {
		};
	}

	public static class Text {
		public Format format;

		public Text() {
		};
	}

	public static class Format {
		public String type;

		public Format() {
		};
	}

	public static class Metadata {
		// Empty object

		public Metadata() {
		};
	}

	public static class Usage {
		public int input_tokens;
		public InputTokensDetails input_tokens_details;
		public int output_tokens;
		public OutputTokensDetails output_tokens_details;
		public int total_tokens;
		
		public Usage() {
		};
	}

	public static class InputTokensDetails {
		public int cached_tokens;
		public int reasoning_tokens;
		
		public InputTokensDetails() {
		};
	}

	public static class OutputTokensDetails {
		public int cached_tokens;
		public int reasoning_tokens;
		
		public OutputTokensDetails() {
		};
	}

}
