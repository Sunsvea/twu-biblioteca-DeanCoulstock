package com.twu.biblioteca;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BibliotecaApp {

    private static void pressEnterKeyToContinue()
    {
        System.out.println("Press Enter key to view available books...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    public static void displayWelcomeMessage() throws InterruptedException {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println();
        pressEnterKeyToContinue();
//        Thread.sleep(1500); //Provides a more natural experience to the user. The welcome message
//                                // will otherwise not be seen due to the long list of books
    }
    public static void displayListOfBooks() throws IOException, InterruptedException {
        System.out.println("Here is a list of books available to hire (alphabetical order by book title):");
        System.out.println("-----------------------------------------------------------------------------");
        Thread.sleep(1500);
        BufferedReader nameList = new BufferedReader(new FileReader("BookNames.txt"));
        BufferedReader authorList = new BufferedReader(new FileReader("BookAuthors.txt"));
        BufferedReader PublishDateList = new BufferedReader(new FileReader("BookListPublishDates.txt"));
        String bookName;
        String bookAuthor;
        String bookPublishDate;

//        while((bookName = nameList.readLine()) != null) {
//            while((bookAuthor = authorList.readLine()) != null) {
                while(((bookPublishDate = PublishDateList.readLine()) != null) &
                        ((bookAuthor = authorList.readLine()) != null) &
                        ((bookName = nameList.readLine()) != null)) {
                    String formattedBookAuthor = "by " + bookAuthor;
                    System.out.printf("%-35s %-35s %-35s\n", bookName, formattedBookAuthor, bookPublishDate);
                    Thread.sleep(250);
                }
                PublishDateList.close();
                authorList.close();
                nameList.close(); }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("-----------PROGRAM ENTRY POINT-----------"); //for debugging and thread timing
        displayWelcomeMessage();
        BibliotecaApp.displayListOfBooks();
    }
}

/*
* Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"
* */