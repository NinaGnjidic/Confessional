package main.java.app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingService {

	private static final String FILE_NAME = "ranking.txt";
	private static final int MAX_RANKINGS = 5;

	private RankingService() {}

	/**
	 * Adds a score, updates the ranking, saves the top {@code MAX_SCORES} to file,
	 * and returns the 0-based rank of the score. Equal scores share the same rank.
	 * If the score is not in the top {@code MAX_SCORES} scores, -1 is returned.
	 *
	 * @param score
	 * @return rank
	 */
	public static int addScore(int score) {
		List<Integer> scores = loadScores();
		scores.add(score);
		scores.sort(Collections.reverseOrder());
		scores = scores.subList(0, MAX_RANKINGS);
		saveScores(scores);
		
		return scores.indexOf(score);
	}

	public static List<Integer> loadScores() {
		File file = new File(FILE_NAME);
		if (!file.exists()) return Collections.emptyList();

		List<Integer> scores = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null)
				scores.add(Integer.parseInt(line.trim()));
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		
		return scores;
	}

	private static void saveScores(List<Integer> scores) {
		File file = new File(FILE_NAME);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (int score : scores) {
				writer.write(String.valueOf(score));
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
