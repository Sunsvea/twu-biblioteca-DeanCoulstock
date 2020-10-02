package com.twu.biblioteca;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class Test_ReturnBookToLibrary {
    public static String ReturnBookToLibrary(String bookName) throws IOException {
        File inputFile = new File("test/com/twu/biblioteca/testCheckedOutBooks.txt");
        File tempFile = new File("test/com/twu/biblioteca/testBookReturnTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = bookName;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
                return("Success");
            //            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        return null;
    }

    @Test
    public void testReturnBookSuccessfully() throws IOException {
        String expectedResult = "Success";
        String actualResult = ReturnBookToLibrary("testBookReturn");
        assertEquals(expectedResult, actualResult);
    }
}
