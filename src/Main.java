import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("**********************************************");
        System.out.println("Type 1 to add a book to the library.");
        System.out.println("Type 2 to remove a book from the library.");
        System.out.println("Type 3 to see all the books of our library.");
        System.out.println("Type 4 to take a book from the library.");
        System.out.println("Type 5 to return a book you took to the library.");
        System.out.println("**********************************************");

        int choice = 50;
        Library library = new Library();

        while(choice != 0){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a function number: ");
            choice = scanner.nextInt();


            if (choice == 1){

                library.addBook();

            } else if (choice == 2) {

                String userInput = "";

                userInput = scanner.next();

                library.removeBook(userInput);

            } else if (choice == 3) {

                library.getBooks();

            } else if (choice == 4) {

                library.takeBook();

            } else if (choice == 5){

                library.returnBook();

            }else {
                System.out.println("->Invalid keystroke.");
            }

        }

    }
}