package Model;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Rent {
    private int ID;
    private User user;
    private Book book;
    private LocalDateTime borrowTime;
    private int totalDays;
    private int status;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    // Status 0 ==> borrowing
    // Status 1 ==> returned
    public Rent(){
        borrowTime = LocalDateTime.now();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowTime() {
        return formatter.format(borrowTime);
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = LocalDateTime.parse(borrowTime, formatter);
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

}
