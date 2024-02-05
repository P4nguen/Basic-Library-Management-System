import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        Library library = new Library();
        Map<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while(choice != 0){
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
    }
}