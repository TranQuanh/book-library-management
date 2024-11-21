package Model;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Rent {
    private int ID;
    private User user;
    private Book book;
    private LocalDateTime borrowTime;
    private int totalDays;
    private int status;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

    public LocalDateTime getLocalBorrowTime() {
        return borrowTime;
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

    public int getStatus() {
        return status;
    }

    public String getStatusToString() {
        long passedDays = ChronoUnit.DAYS.between( borrowTime, LocalDateTime.now());
        String status = "";
        if (getStatus() != 1 && passedDays<getTotalDays()) {
            status = "Estimated";
        } else if (getStatus() != 1 && passedDays>getTotalDays()) {
            status = "Delayed";
        } else if (getStatus() == 1) {
            status = "Rented";
        }
        return status;
    }
    public int getDelayedDays(){
        long passedDays = ChronoUnit.DAYS.between( borrowTime, LocalDateTime.now());
        return (int) (passedDays-getTotalDays());
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
