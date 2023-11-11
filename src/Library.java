import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Book> allLibraryBooks = new ArrayList<Book>();
    void addBook(){

        Scanner scanner = new Scanner(System.in);

        String title = scanner.nextLine();
        String author = scanner.nextLine();
        String ISBN = scanner.nextLine();
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

    public void getBooks(){

        for (int i=0; i < allLibraryBooks.size(); i++){

            System.out.println("*****************************");
            System.out.println(allLibraryBooks.get(i).getTitle());
            System.out.println(allLibraryBooks.get(i).getISBN());
            System.out.println("*****************************");

        }

    }

}



