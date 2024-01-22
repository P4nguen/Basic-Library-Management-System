import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Book> allLibraryBooks = new ArrayList<Book>();

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

    }

    void removeBook(String ISBN){

    /*    allLibraryBooks.stream().map(i->{
            if (i.getISBN().equals(ISBN)){
                System.out.println("running");
                allLibraryBooks.remove(i);
            }
            return i;});
    */

        for (int  i=0; i < allLibraryBooks.size(); i++){

            if (allLibraryBooks.get(i).getISBN().equals(ISBN)){
                allLibraryBooks.remove(allLibraryBooks.get(i));
                System.out.println("The book was deleted successfully");
            }

        }
    }

    void getBooks(){

        for (int i=0; i < allLibraryBooks.size(); i++){

            System.out.println("*****************************");
            System.out.println("Title: " + allLibraryBooks.get(i).getTitle());
            System.out.println("Author: " + allLibraryBooks.get(i).getAuthor());
            System.out.println("ISBN: " + allLibraryBooks.get(i).getISBN());
            System.out.println("Availability: " + allLibraryBooks.get(i).isAvailable());
            System.out.println("*****************************");
            System.out.println(" ");

        }

    }

    void takeBook(){

        int duration = 0;
        String selection = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which book do you want to take from our library?");
        selection = scanner.next();


        for (int i=0; i < allLibraryBooks.size(); i++){

            if(allLibraryBooks.get(i).getTitle().equals(selection)){

                System.out.printf("For how long will you be taking the book: %s (%s)?(14 days max)\n", selection, allLibraryBooks.get(i).getTitle());
                duration = scanner.nextInt();

                if (duration <= 14) {
                    System.out.printf("You have took the book %s (%s) successfully for %d days.\n", selection, allLibraryBooks.get(i).getTitle(), duration);
                    // Use the setAvailable method to update availability status
                    allLibraryBooks.get(i).setAvailable(false);

                }else {
                    System.out.println("You can't take the book for more than 14 days.");
                }

            }


        }

    }

    void returnBook(){

        int duration = 0;
        String selection = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the ISBN value of the book you want to return: ");
        selection = scanner.next();
        System.out.println("For how many days you've had the book?");
        duration = scanner.nextInt();

        for (int i=0; i < allLibraryBooks.size(); i++) {

            if (allLibraryBooks.get(i).getISBN().equals(selection)){

                allLibraryBooks.get(i).setAvailable(true);

                if (duration > 14){
                    float dailyFee = 0.25F;
                    float result;

                    System.out.println("You've had the book for more than 14 days so you will have a fee.");

                    result = dailyFee*(duration-14);

                    if (result < 0){
                        System.out.println("Invalid duration time.");
                    }else {
                        System.out.printf("Your fee is %.2f dollars\n",result);
                    }


                }else {
                    System.out.printf("You have successfully returned the book %s after %d days\n", selection, duration);
                }

            }

        }


    }

}



