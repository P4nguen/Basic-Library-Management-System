import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.loadLibrary;

public class Main {
    public static void main(String[] args) {

        System.out.println("**********************************************");
        System.out.println("Type 1 to add a book to the library.");
        System.out.println("Type 2 to remove a book from the library.");
        System.out.println("Type 3 to print all the books of our library.");
        System.out.println("Type 4 to borrow a book from the library.");
        System.out.println("Type 5 to return a book you took to the library.");
        System.out.println("Type 6 to add a user");
        System.out.println("Type 7 to remove a user");
        System.out.println("Type 8 to print borrowed books");
        System.out.println("Type 9 to print users");
        System.out.println("Type 0 to exit");
        System.out.println("**********************************************");

        int choice = 50;
        Library library = loadLibrary();
        Map<String, User> users = library.users;
        Scanner scanner = new Scanner(System.in);

        while (choice != 0) {
            System.out.println("Please enter a function number: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    String userInput = scanner.next();
                    library.removeBook(userInput);
                    break;
                case 3:
                    library.getBooks();
                    break;
                case 4:
                    library.borrowBook(users);
                    break;
                case 5:
                    library.returnBook(users);
                    break;
                case 6:
                    library.addUser();
                    break;
                case 7:
                    library.removeUser();
                    break;
                case 8:
                    library.printBorrowedBooks();
                    break;
                case 9:
                    library.printUsers();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a valid function number.");
                    break;
            }
        }
        saveLibrary(library);
    }
    // Load library method
    private static Library loadLibrary() {
        Library library = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library.ser"))) {
            library = (Library) ois.readObject();
            System.out.println("Library state loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing library state found. Starting with an empty library.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library state: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for detailed error information
        }
        if (library == null) {
            library = new Library();
        }
        return library;
    }


    // Save library method
    private static void saveLibrary(Library library) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library.ser"))) {
            oos.writeObject(library);
            System.out.println("Library state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving library state: " + e.getMessage());
        }
    }
}

