package com.twu.biblioteca;
import java.io.*;
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
        nameList.close();
        Thread.sleep(2000);
        displayMainMenu();
    }
    public static void RemoveBookFromLibrary(String bookName) throws IOException {
        File inputFile = new File("BookNames.txt");
        File tempFile = new File("BookNameTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = bookName;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);

    }
    public static void SearchBookTitle(String inputBook) throws IOException, InterruptedException {
        File f1 = new File("BookNames.txt"); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String input = inputBook;   // Input word to be searched
        String currBookName;
        Boolean noBreak = true;
        while ((currBookName = br.readLine()) != null)   //Reading Content from the file
        {
            if (input.equals(currBookName))   //Search for the given word
            {
                RemoveBookFromLibrary(currBookName);
                try (PrintWriter out = new PrintWriter("CheckedOutBooks.txt")) {
                    out.println(currBookName);
                }
                System.out.println("Thank you, enjoy the book! Returning to main menu.\n");
                Thread.sleep(3000);
                displayMainMenu();
                noBreak = false;
            }
        }
        fr.close();
        if (noBreak) {
            System.out.println("Sorry, that book is not available!\n");
            Thread.sleep(3000);
            displayMainMenu();
        }
    }
    public static void displayMainMenu() throws IOException, InterruptedException {
        String showListOfBooks = "1. Show list of books";
        String quitQuery = "2. Exit this application";
        String checkoutBook = "3. Check out a book";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose an option : ");
        System.out.println(showListOfBooks);
        System.out.println(quitQuery);
        System.out.println(checkoutBook);
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
            else if (choice == 3) {
                System.out.println("Please input the title of your book (as accurately as possible) : ");
                Scanner scanner = new Scanner( System. in);
                String title = scanner.nextLine();
                SearchBookTitle(title);
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