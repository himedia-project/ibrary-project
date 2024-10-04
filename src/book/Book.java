package book;

public class Book {

    private String id;
    private int cateogryId;

    private String category;

    private String title;

    private boolean rented = false;
    private String writer;
    private String publisher;

    private String description;

    public Book(String id, int cateogryId, String title, String writer, String publisher, String description) {
        this.id = id;
        this.cateogryId = cateogryId;
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.description = description;
    }

    public Book(String id, String category, String title, String writer, String publisher) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
    }

    public Book(String id, String category, String title, String writer, String publisher, String description, int rented) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.description = description;
        this.rented = rented == 1 ? true : false;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCateogryId() {
        return cateogryId;
    }

    public void setCateogryId(int cateogryId) {
        this.cateogryId = cateogryId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getWriter() {
        return writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", cateogryId=" + cateogryId +
                ", title='" + title + '\'' +
                ", rented=" + rented +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
