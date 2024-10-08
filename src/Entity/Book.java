package Entity;

import java.time.LocalDateTime;
import java.util.List;

public class Book {
    private int bookID;
    private String title;
    private String overview;
    private String author;
    private String NXB;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List <Type> type;
    private int amount; 

    public Book(int bookID, String title, String overview, String author, String nXB, LocalDateTime createDate,
            LocalDateTime updateDate, List<Type> type, int amount) {
        this.bookID = bookID;
        this.title = title;
        this.overview = overview;
        this.author = author;
        this.NXB = nXB;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.type = type;
        this.amount = amount;
    }

    public int getBookId(){
        return bookID;
    }

    public void setBookId(int bookID){
        this.bookID = bookID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getNXB(){
        return NXB;
    }

    public void setNXB(String NXB){
        this.NXB = NXB;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate){
        this.updateDate = updateDate;
    }    
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public List<Type> getTypes() {
        return type;
    }
    public void setTypes(List<Type> type) {
        this.type = type;
    }
}
