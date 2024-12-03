package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateBook {
    private int ID;
    private Book book;
    private Admin admin;
    private LocalDateTime createDate;
    private DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public CreateBook(){
        createDate = LocalDateTime.now();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getCreateDate() {
        return formatter.format(createDate);
    }

    public LocalDateTime getLocalcreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = LocalDateTime.parse(createDate, formatter);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
