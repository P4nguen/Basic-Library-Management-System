import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    ArrayList<Book> allLibraryBooks = new ArrayList<Book>();
    ArrayList<BorrowedBook> borrowedBooks = new ArrayList<BorrowedBook>();
    Map<String, User> users = new HashMap<String, User>();

    void addBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name of the Book: ");
        String title = scanner.nextLine();

        System.out.println("Name of the Author: ");
        String author = scanner.nextLine();

        System.out.println("Enter the ISBN value: ");
        String ISBN = scanner.nextLine();

        System.out.println("Is the book available(true or false): ");
        boolean available = scanner.hasNextBoolean();

        Book book = new Book(title, author, ISBN, available);
        allLibraryBooks.add(book);

        System.out.printf("->%s (s) with ISBN %s added successfully.%n", book.getTitle(), book.getAuthor(), book.getISBN());
    }

    void removeBook(String ISBN){
        for (int  i=0; i < allLibraryBooks.size(); i++){
            if (allLibraryBooks.get(i).getISBN().equals(ISBN)){
                allLibraryBooks.remove(allLibraryBooks.get(i));
                System.out.printf("->The book %s (%s) was deleted successfully.%n",allLibraryBooks.get(i).getTitle(), allLibraryBooks.get(i).getISBN());
            }
        }
    }

    void getBooks(){
        for (Book book : allLibraryBooks){

            System.out.println("*****************************");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getISBN());
            System.out.println("Availability: " + book.isAvailable());
            System.out.println("*****************************");
            System.out.println(" ");

        }
    }

    void borrowBook(Map<String, User> users) {
        int duration = 0;
        String selection = "";
        String userId = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which user are you? Please enter your ID: ");
        userId = scanner.nextLine();

        // Check if the user exists
        if (!users.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Which book do you want to take from our library? (Enter the ISBN Value)");
        selection = scanner.nextLine();

        // Check if the book exists
        Book selectedBook = findBookByISBN(selection);
        if (selectedBook == null || !selectedBook.isAvailable()) {
            System.out.println("Book not available or does not exist.");
            return;
        }

        System.out.printf("For how long will you be taking the book: %s (%s)? (14 days max)%n", selection, selectedBook.getTitle());
        duration = scanner.nextInt();

        if (duration <= 14) {
            System.out.printf("You have taken the book '%s' successfully for %d days.%n", selectedBook.getTitle(), duration);
            // Use the setAvailable method to update availability status
            selectedBook.setAvailable(false);

            // Add the borrowed book information
            BorrowedBook borrowedBook = new BorrowedBook(selection, userId);
            borrowedBooks.add(borrowedBook);
        } else {
            System.out.println("You can't take the book for more than 14 days.");
        }
    }



    void returnBook(Map<String, User> users){

        int duration = 0;
        String selection = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the ISBN Value of the book you want to return: ");
        selection = scanner.next();
        System.out.println("For how many days you've had the book?");
        duration = scanner.nextInt();

        for (Book book : allLibraryBooks) {
            if (book.getISBN().equals(selection)){
                book.setAvailable(true);
                if (duration > 14){
                    float dailyFee = 0.25F;
                    float result;

                    System.out.println("You've had the book for more than 14 days so you will have a fee.");

                    result = dailyFee*(duration-14);

                    if (result < 0){
                        System.out.println("Invalid duration time.");
                    }else {
                        System.out.printf("Your fee is %.2f dollars%n",result);
                    }


                }else {
                    System.out.printf("You have successfully returned the book %s after %d days%n", selection, duration);
                }
            }
        }
    }

    void addUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the user: ");
        String userId = scanner.nextLine();

        System.out.println("Enter the NAME of the user: ");
        String name = scanner.nextLine();

        System.out.println("Enter the ROLE of the user: ");
        String role = scanner.nextLine();

        User user = new User(userId, name, role);
        users.put(userId, user);

        System.out.printf("%s %s (%s) added successfully!%n",users.get(userId).getRole() ,users.get(userId).getName(), userId);
    }

    void removeUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user ID to remove: ");
        String userId = scanner.nextLine();

        if(users.containsKey(userId)){
            users.remove(userId);
            System.out.println("User removed successfully!(" + userId + ")");
        }else{
            System.out.println("No user found with ID: " + userId);
        }

    }

    void printBorrowedBooks() {
        System.out.println("Borrowed Books:");

        for (BorrowedBook borrowedBook : borrowedBooks) {
            String userID = borrowedBook.userId;
            String bookTitle = findBookByISBN(borrowedBook.ISBN).getTitle();
            System.out.printf("User %s has borrowed the book '%s'%n", userID, bookTitle);
        }
    }

    void printUsers() {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            String userID = entry.getKey();
            User user = entry.getValue();
            System.out.printf("User ID: %s, Name: %s, Role: %s%n", userID, user.getName(), user.getRole());

            for (BorrowedBook borrowedBook : borrowedBooks) {
                if (borrowedBook.userId.equals(userID)) {
                    Book book = findBookByISBN(borrowedBook.ISBN);
                    System.out.printf("->Books Borrowed: %s (%s)%n", book.getTitle(), book.getAuthor());
                    System.out.println("*****************************");
                }
            }
        }
    }

    private Book findBookByISBN(String ISBN) {
        for (Book book : allLibraryBooks) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}

