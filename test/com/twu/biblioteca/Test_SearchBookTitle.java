package com.twu.biblioteca;


import org.junit.Test;

import java.io.*;

import static com.twu.biblioteca.BibliotecaApp.displayMainMenu;
import static org.junit.Assert.assertEquals;


public class Test_SearchBookTitle {
    public static String SearchBookTitle(String searchTerm, String bookType) throws IOException, InterruptedException {
        String selectedBook = bookType;
        File f1 = new File(selectedBook); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String input = searchTerm;   // Input word to be searched
        String currBookName;
        Boolean noBreak = true;
        while ((currBookName = br.readLine()) != null)   //Reading Content from the file
        {
            if (input.equals(currBookName))   //Search for the given word
            {
                noBreak = false;
                return input;
//                displayMainMenu();
            }
        }
        fr.close();
        if (noBreak) {
            System.out.println("Sorry, that book name is invalid! Returning to main menu.\n");
            Thread.sleep(3000);
//            displayMainMenu();
        }
        return "Invalid";
    }

    @Test
    public void testForInvalidBookEntered() throws IOException, InterruptedException {
        String bookName = "incorrectBookTitle";
        String result = SearchBookTitle(bookName, "test/com/twu/biblioteca/testBookNames.txt");
        String expectedResult = "Invalid";
        assertEquals(expectedResult, result);
    }
    @Test
    public void testForValidBookEntered() throws IOException, InterruptedException {
        String bookName = "Testbook3";
        String result = SearchBookTitle(bookName, "test/com/twu/biblioteca/testBookNames.txt");
        String expectedResult = "Testbook3";
        assertEquals(expectedResult, result);
    }
}
