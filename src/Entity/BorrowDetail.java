package Entity;

import java.time.LocalDateTime;
// Bảng để show thời gian mượn sách
public class BorrowDetail {
    private int borrowId;
    private int customerId;
    private int bookId;
    private LocalDateTime   borrowDate;
    private LocalDateTime   returnDate;
    private String details;

    public BorrowDetail() {
    }

    public BorrowDetail(String details, LocalDateTime returnDate, LocalDateTime borrowDate, int bookId, int customerId, int borrowId) {
        this.details = details;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
        this.bookId = bookId;
        this.customerId = customerId;
        this.borrowId = borrowId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
