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
}
