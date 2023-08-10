package com.example.geektrust.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class FileReaderUtility {
    // Private constructor to prevent instantiation
    private FileReaderUtility() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    // Read the content of a file and returns the lines of text as a list of strings
    public static List<String> readInputFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
