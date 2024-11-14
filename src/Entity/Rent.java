package Entity;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Rent {
    private int ID;
    private User user;
    private Book book;
    private LocalDateTime borrowTime;
    private LocalDateTime endTime;
    private int totalDays;
    private boolean status;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

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

    public String getEndTime() {
        return formatter.format(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalDateTime.parse(endTime, formatter);
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

}
