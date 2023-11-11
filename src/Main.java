import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Type 1 to add a book to the library.");
        System.out.println("Type 2 to remove a book from the library.");
        System.out.println("Type 3 to see all the books of our library.");

        int choice = 50;
        Library library = new Library();


        while(choice != 0){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to our library, please enter the function's number that you want to do: ");
            choice = scanner.nextInt();

            if (choice == 1){

                library.addBook();

            } else if (choice == 2) {

                String userInput = "";

                userInput = scanner.next();

                library.removeBook(userInput);

            } else if (choice == 3) {

                library.getBooks();

            }else {

                System.out.println("Error");

            }

        }

    }
}