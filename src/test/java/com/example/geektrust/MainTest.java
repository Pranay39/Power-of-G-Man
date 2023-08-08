package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;;
import org.junit.jupiter.api.Test;
import java.io.*;

public class MainTest {
    @Test
    public void testMainWithInputFile() {
        String input = "SOURCE 0 0 N\nDESTINATION 3 0\nPRINT_POWER\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String[] args = {"sample_input/input1.txt"};
        Main.main(args);

        String expectedOutput = "POWER 145";
        assertEquals(expectedOutput, outputStream.toString().trim());

        // Clean up by restoring the standard input/output streams
        System.setIn(System.in);
        System.setOut(System.out);
    }
}