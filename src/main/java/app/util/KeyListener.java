package main.java.app.util;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface KeyListener {
    final Scanner scanner = new Scanner(System.in);
    
    public default String readLineCharacter() {
        return scanner.next();
    }

     public default String readLineCharacterInTime(long time) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> {
            if (scanner.hasNext()) {
                return scanner.next();
            }
            return null;
        });

        try {
            // Wait up to 10 seconds for input
            return future.get(time, TimeUnit.SECONDS);
        } catch (Exception e) {
            return null;
        } finally {
            executor.shutdownNow();
        }
    }


    
}
