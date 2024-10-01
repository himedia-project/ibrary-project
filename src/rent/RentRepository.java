package rent;

import java.sql.Connection;
import java.sql.Date;

public class RentRepository {

	private String id;
	private String book_id;
	private String user_id;
	private Date start_date;
	private Date end_date;
	
	public RentRepository() {
		
	}
	
	public RentRepository(String pid,String pbook_id,String puser_id, Date pstart_date, Date pend_date) {
		this.id = pid;
		this.book_id = pbook_id;
		this.user_id = puser_id;
		this.start_date = pstart_date;
		this.end_date = pend_date;
	}

	public String getId() {
		return id;
	}

	public String getBook_id() {
		return book_id;
	}
	public String getUser_id() {
		return user_id;
	}

	public Date getStart_date() {
		return start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}

}
