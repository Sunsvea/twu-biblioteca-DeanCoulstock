package com.twu.biblioteca;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibliotecaApp {
    protected static String loggedInAs = "(Not currently logged in)";
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

        while (((bookPublishDate = PublishDateList.readLine()) != null) &
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

    public static void displayListOfMovies() throws IOException, InterruptedException {
        System.out.println("Here is a list of movies available to hire (ordered by worst movie rating):");
        System.out.println("-----------------------------------------------------------------------------");
        Thread.sleep(1500);
        BufferedReader movieList = new BufferedReader(new FileReader("MovieList.txt"));
        String movieTitle;

        while ((movieTitle = movieList.readLine()) != null) {
            System.out.println(movieTitle);
            Thread.sleep(500);
        }
        movieList.close();
        System.out.println();
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

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
    }

    public static void emailDisplayer() throws IOException {
        File CredentialFile = new File("LoginCredentials.txt");
        BufferedReader CredentialReader = new BufferedReader(new FileReader(CredentialFile));
        String currIterUser; //initialise
        String libraryId = BibliotecaApp.loggedInAs;
        while ((currIterUser = CredentialReader.readLine()) != null) //Reading Content from the file
        {
            if (currIterUser.contains(libraryId)) //Search for line with library ID
            {
                Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(currIterUser);
                while (m.find()) {
                    System.out.println("Your email : " + (m.group()));
                }
            }
        }
    }
    public static void libraryIdDisplayer() throws IOException {
        File CredentialFile = new File("LoginCredentials.txt");
        BufferedReader CredentialReader = new BufferedReader(new FileReader(CredentialFile));
        String currIterUser; //initialise
        String libraryId = BibliotecaApp.loggedInAs;
        while ((currIterUser = CredentialReader.readLine()) != null) //Reading Content from the file
        {
            if (currIterUser.contains(libraryId)) //Search for line with library ID
            {
                Matcher m = Pattern.compile("(?<=ID:)([a-zA-Z0-9-]+)").matcher(currIterUser);
                while (m.find()) {
                    System.out.println("Your LibraryID : " + (m.group()));
                }
            }
        }
    }
    public static void nameDisplayer() throws IOException {
        File CredentialFile = new File("LoginCredentials.txt");
        BufferedReader CredentialReader = new BufferedReader(new FileReader(CredentialFile));
        String currIterUser; //initialise
        String libraryId = BibliotecaApp.loggedInAs;
        while ((currIterUser = CredentialReader.readLine()) != null) //Reading Content from the file
        {
            if (currIterUser.contains(libraryId)) //Search for line with library ID
            {
                Matcher m = Pattern.compile("(?<=name:)([a-zA-Z0-9-]+)").matcher(currIterUser);
                while (m.find()) {
                    System.out.println("Your name: " + (m.group()));
                }
            }
        }
    }


    public static void CurrentUserInformation() throws IOException {
        nameDisplayer();
        emailDisplayer();
        libraryIdDisplayer();

    }

    public static void RemoveMovieFromLibrary(String movieName) throws IOException {
        File inputFile = new File("MovieList.txt");
        File tempFile = new File("MovieNameTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = movieName;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if (trimmedLine.contains(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
    }

    public static void ReturnBookToLibrary(String bookName) throws IOException {
        File inputFile = new File("CheckedOutBooks.txt");
        File tempFile = new File("BookReturnTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = bookName;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);

    }

    public static int ValidateCredential(String libraryID, String password) throws IOException, InterruptedException {
        String selectedLibraryId = libraryID;
        File f1 = new File("LoginCredentials.txt"); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1); //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String input = selectedLibraryId; // Input word to be searched
        String currItemName;
        Boolean noBreak = true;
        while ((currItemName = br.readLine()) != null) //Reading Content from the file
        {
            if (currItemName.contains(libraryID)) //Search for the given word
            {
                if (currItemName.contains(password)) {
                    noBreak = false;
                    return 1; //successful login
                }
            }
            noBreak = false;
        }
        fr.close();
        if (noBreak) {
            System.out.println("Sorry, that libraryID and Password is invalid! Returning to main menu.\n");
            Thread.sleep(3000);
            displayMainMenu();
        }
        return 0; //unsuccessful login
    }

    public static String SearchTitle(String searchTerm, String catalog) throws IOException, InterruptedException {
        String selectedCatalog = catalog;
        File f1 = new File(selectedCatalog); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1); //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String input = searchTerm; // Input word to be searched
        String currItemName;
        Boolean noBreak = true;
        while ((currItemName = br.readLine()) != null) //Reading Content from the file
        {
            if (currItemName.contains(input)) //Search for the given word
            {
                noBreak = false;
                return currItemName;
            }
        }
        fr.close();
        if (noBreak) {
            System.out.println("Sorry, that book name is invalid! Returning to main menu.\n");
            Thread.sleep(3000);
            displayMainMenu();
        }
        return "Invalid";
    }



    private static void printMenuOptions(List < String > menuOptions) throws IOException {
        System.out.println("Currently logged in as:  " + loggedInAs);
        for (String value: menuOptions) {
            System.out.println(value);
        }
        if (BibliotecaApp.loggedInAs != "(Not currently logged in)") {
            System.out.println("8. Show my user information");
        }
    }

    public static void displayMainMenu() throws IOException, InterruptedException {
        Scanner keyboard = new Scanner(System.in); //Initialise scanner for user's choice
        System.out.println("Please choose an option : ");
        List < String > menuOptions = Arrays.asList("1. Show list of books",
                "2. Exit this application", "3. Check out a book", "4. Return a book", "5. Movie List", "6. Check out a movie", "7. Log in using Library ID");
        printMenuOptions(menuOptions);
        int choice = keyboard.nextInt();
        do {
            Thread.sleep(1000);
            if (choice == 1) {
                displayListOfBooks();
                break;
            } else if (choice == 2) {
                terminateApplication();
            } else if (choice == 3) {
                if (BibliotecaApp.loggedInAs == "(Not currently logged in)") {
                    System.out.println("You must be logged in to check out a book! Returning to main menu.\n");
                    Thread.sleep(2500);
                    displayMainMenu();
                }
                System.out.println("Please input the title of your book : ");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                RemoveBookFromLibrary(SearchTitle(title, "BookNames.txt")); // Check out selected book
                try (PrintWriter out = new PrintWriter(new FileWriter("CheckedOutBooks.txt", true))) {
                    out.println(title + "    -    Checked out by " + BibliotecaApp.loggedInAs); // Write checked-out book to CheckedOutBooks
                }
                System.out.println("Thank you, enjoy the book! Returning to main menu.\n");
                Thread.sleep(3000);
                displayMainMenu();
            } else if (choice == 4) {
                if (BibliotecaApp.loggedInAs == "(Not currently logged in)") {
                    System.out.println("You must be logged in to return a book! Returning to main menu.\n");
                    Thread.sleep(2500);
                    displayMainMenu();
                }
                System.out.println("Please input the title of the book you wish to return : ");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                ReturnBookToLibrary(SearchTitle(title, "CheckedOutBooks.txt"));
                try (PrintWriter out = new PrintWriter(new FileWriter("BookNames.txt", true))) {
                    out.println(title); // Write checked-out book to CheckedOutBooks
                }
                System.out.println("Thank you for returning the book! Returning to the main menu.\n");
                Thread.sleep(3000);
                displayMainMenu();
            } else if (choice == 5) {
                displayListOfMovies();
            } else if (choice == 6) {
                System.out.println("Please input the title of the movie you would like to check out : ");
                Scanner scanner = new Scanner(System.in);
                String movieTitle = scanner.nextLine();
                String movieFullTitle = SearchTitle(movieTitle, "MovieList.txt");
                RemoveMovieFromLibrary(SearchTitle(movieTitle, "MovieList.txt"));
                try (PrintWriter out = new PrintWriter(new FileWriter("CheckedOutMovies.txt", true))) {
                    out.println(movieFullTitle); // Write checked-out book to CheckedOutBooks
                }
                System.out.println("You have successfully checked out the movie.");
            } else if (choice == 7) {
                String loggedInAs; //initialise variable
                System.out.println("Please enter your library card number");
                Scanner idScanner = new Scanner(System.in);
                String enteredLibraryID = idScanner.nextLine(); // assign entered ID
                Thread.sleep(1500);
                System.out.println("Please enter your password");
                Scanner pwdScanner = new Scanner(System.in);
                String enteredPassword = pwdScanner.nextLine(); // assign entered password
                int loginOutcome = ValidateCredential(enteredLibraryID, enteredPassword);
                int attemptCount = 0;
                if (loginOutcome == 1) {
                    BibliotecaApp.loggedInAs = enteredLibraryID; //assign successful LibraryID to global variable loggedInAs
                    System.out.println("Successful login! You are now being logged in . . . \n");
                    Thread.sleep(1500);
                    displayMainMenu();
                } else {
                    attemptCount++;
                    System.out.println("Unsuccessful login attempt #" + attemptCount);
                    System.out.println("Please check if your information is correct.");
                }

            } else if (choice == 8) {
                System.out.println("Here is your information: \n");
                CurrentUserInformation();
                System.out.println();
                Thread.sleep(3000);
                displayMainMenu();
            }
            else {
                System.out.println("Invalid option entered. Please select a valid option!");
                displayMainMenu();
            }
        } while (!(choice < 1));
    }


    public static void terminateApplication() throws InterruptedException {
        System.out.println("Thank you for browsing the Biblioteca Library. Have a wonderful day.");
        Thread.sleep(2000);
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        displayWelcomeMessage();
        displayMainMenu();
    }
}