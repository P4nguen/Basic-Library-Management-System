import java.io.Serializable;

public class BorrowedBook implements Serializable {
    private static final long serialVersionUID = 1L;
    String ISBN;
    String userId;

    public BorrowedBook(String ISBN, String userId){
        this.ISBN = ISBN;
        this.userId = userId;
    }
}
