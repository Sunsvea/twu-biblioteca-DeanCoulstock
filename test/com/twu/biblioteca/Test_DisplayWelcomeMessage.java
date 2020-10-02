package com.twu.biblioteca;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Test_DisplayWelcomeMessage {
    public static String displayWelcomeMessage() throws InterruptedException {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }


    @Test
    public void testWelcomeMessageShows() throws InterruptedException {
        String Result = displayWelcomeMessage();
        String ExpectedResult = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertEquals(ExpectedResult, Result);
    }
}
