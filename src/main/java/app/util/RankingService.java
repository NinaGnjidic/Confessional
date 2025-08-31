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

import main.java.app.EnvironmentVariables;

public class RankingService {
    private static final String FILE_NAME = "ranking.txt";
    
    private RankingService() {}

    public static void addScore(int newScore) throws IOException {
        List<Integer> scores = loadScores();
        scores.add(newScore);
        scores.sort(Collections.reverseOrder());
        saveScores(scores);
    }
    
    public static List<Integer> loadScores() {
        File file = new File(FILE_NAME);
        List<Integer> scores = new ArrayList<>();
        if (!file.exists())
            return scores;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    scores.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void saveScores(List<Integer> scores) throws IOException {
        File file = new File(FILE_NAME);
        int limit = Math.min(scores.size(), Integer.parseInt(EnvironmentVariables.MAX_SCORES));
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < limit; i++) {
                bw.write(String.valueOf(scores.get(i)));
                bw.newLine();
            }
        }
    }
}
