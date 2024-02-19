import java.io.*;
import java.util.*;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
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

        System.out.printf("->%s (%s) with ISBN %s added successfully.%n", book.getTitle(), book.getAuthor(), book.getISBN());
    }

    void removeBook() {

        for(Book book : allLibraryBooks){
            System.out.println("->Title: " + book.getTitle());
            System.out.println("->ISBN: " + book.getISBN());
            System.out.println("***************");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("You pressed 2, Do you really want to remove a Book? (Y/N)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("y")) {
            System.out.println("Enter the ISBN of the book you want to remove:");
            String ISBNselection = scanner.nextLine();

            boolean bookRemoved = false;
            for (Book book : allLibraryBooks) {
                if (book.getISBN().equals(ISBNselection)) {
                    allLibraryBooks.remove(book);
                    System.out.printf("The book %s (%s) was deleted successfully.%n", book.getTitle(), book.getISBN());
                    bookRemoved = true;
                    break; // Exit the loop once a match is found and removed
                }
            }

            if (!bookRemoved) {
                System.out.println("No book found with ISBN: " + choice);
            }
        }else if(choice.equalsIgnoreCase("n")){
            System.out.println("Returning to the menu...");
            return;
        }
    }

    void getBooks(){
        System.out.println("*************************");
        for (Book book : allLibraryBooks){
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getISBN());
            System.out.println("Availability: " + book.isAvailable());
            System.out.println("*************************");
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
        scanner.nextLine();

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

    void returnBook(Map<String, User> users) {
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

        boolean hasBorrowedBooks = false;
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.userId.equals(userId)) {
                Book book = findBookByISBN(borrowedBook.ISBN);
                System.out.printf("->Borrowed: %s (%s), ISBN: %s%n", book.getTitle(), book.getAuthor(), book.getISBN());
                System.out.println("*****************************");
                hasBorrowedBooks = true;
            }
        }

        if (!hasBorrowedBooks) {
            System.out.printf("User %s has no books to return!%n", userId);
            return;
        }

        System.out.println("Please enter the ISBN Value of the book you want to return: ");
        selection = scanner.next();

        // Find the borrowed book and remove it
        BorrowedBook bookToRemove = null;
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.userId.equals(userId) && borrowedBook.ISBN.equals(selection)) {
                bookToRemove = borrowedBook;
                break;
            }
        }

        if (bookToRemove == null) {
            System.out.println("Book not found.");
            return;
        }

        borrowedBooks.remove(bookToRemove);

        // Set the availability of the book to true
        Book bookReturned = findBookByISBN(selection);
        if (bookReturned != null) {
            bookReturned.setAvailable(true);
            System.out.printf("You have successfully returned the book %s (%s)%n", bookReturned.getTitle(), bookReturned.getISBN());
        } else {
            System.out.println("Book not found.");
        }

        System.out.println("For how many days you've had the book?");
        int duration = scanner.nextInt();

        if (duration > 14) {
            float dailyFee = 0.25F;
            float result = dailyFee * (duration - 14);
            if (result < 0) {
                System.out.println("Invalid duration time.");
            } else {
                System.out.printf("Your fee is %.2f dollars%n", result);
            }
        } else {
            System.out.printf("You have successfully returned the book %s after %d days%n", selection, duration);
        }
    }


    void addUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the user: ");
        String userId = scanner.nextLine();

        if(users.containsKey(userId)){
            System.out.println("You can't add the same user twice!");
            return;
        }

        System.out.println("Enter the NAME of the user: ");
        String name = scanner.nextLine();

        System.out.println("Enter the ROLE of the user: ");
        String role = scanner.nextLine();

        User user = new User(userId, name, role);
        users.put(userId, user);

        System.out.printf("->%s %s (%s) added successfully!%n",users.get(userId).getRole(), users.get(userId).getName(), userId);
    }

    void removeUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You've pressed 7, Do you really want to remove a User? (Y/N)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {

            System.out.println("*************************************************");
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String userId = entry.getKey();
                User user = entry.getValue();
                System.out.printf("User ID: %s, Name: %s, Role: %s%n", userId, user.getName(), user.getRole());
                System.out.println("*************************************************");
            }

            System.out.println("Enter user ID to remove: ");
            String userId = scanner.nextLine();

            if (users.containsKey(userId)) {
                System.out.printf("User %s (%s) removed successfully!%n", userId, users.get(userId).getName());
                users.remove(userId);
            } else {
                System.out.println("No user found with ID: " + userId);
            }
        }else if(choice.equalsIgnoreCase("n")){
            System.out.println("Returning to the menu...");
            return;
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
        System.out.println("*************************************************");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            String userId = entry.getKey();
            User user = entry.getValue();
            System.out.printf("User ID: %s, Name: %s, Role: %s%n", userId, user.getName(), user.getRole());
            for (BorrowedBook borrowedBook : borrowedBooks) {
                if (borrowedBook.userId.equals(userId)) {
                    Book book = findBookByISBN(borrowedBook.ISBN);
                    System.out.printf("->Borrowed: %s (%s) ->ISBN: %s%n", book.getTitle(), book.getAuthor(), book.getISBN());
                }
            }
            System.out.println("*************************************************");
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

