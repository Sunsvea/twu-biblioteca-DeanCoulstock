package com.twu.biblioteca;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BibliotecaApp {

// %     Phased out due to implementation of main menu. This function may return.    %
//    private static void pressEnterKeyToContinue()
//    {
//        System.out.println("Press Enter key to view available books...");
//        try
//        {
//            System.in.read();
//        }
//        catch(Exception e)
//        {}
//    }

    public static void displayWelcomeMessage() throws InterruptedException {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println();
//        pressEnterKeyToContinue(); Phased out due to implementation of main menu
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
    public static void displayMainMenu() throws IOException, InterruptedException {
        String options = "1. List of books";
        String quitQuery = "2. Exit this application";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose an option : ");
        System.out.println(options);
        System.out.println(quitQuery);
        int choice = keyboard.nextInt();
        do{
            Thread.sleep(1000);

            if (choice == 1) {
                displayListOfBooks();
                break;
            }
            else if (choice == 2) {
                terminateApplication();
            }
            else {
                System.out.println("Invalid option entered. Please select a valid option!");
                displayMainMenu();
            }
        }while(!(choice < 1));
    }

    public static void terminateApplication() throws InterruptedException {
        System.out.println("Thank you for browsing the Bibliotecha Library. Have a wonderful day.");
        Thread.sleep(2000);
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("-----------PROGRAM ENTRY POINT-----------"); //for debugging and thread timing
        displayWelcomeMessage();
        displayMainMenu();
    }
}

/*
 * Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"
 * */