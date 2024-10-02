package rent;

import user.UserManager;
import java.util.Scanner;
import java.sql.Date;

public class Rent {

    private Long id;
    private String userId;
    private String bookId;
    private Date startDate;
    private Date endDate;

    public Rent(Long id, String bookId, String userId, Date startDate, Date endDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}