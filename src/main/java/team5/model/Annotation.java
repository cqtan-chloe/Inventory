package team5.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


// annotate changes to qty with user and datetime 
public class Annotation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat (pattern="dd-MM-yyyy")
	protected Date date;

	@ManyToOne
	protected User user;
	
	
	
	public Annotation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Annotation(long id, Date date, User user) {
		super();
		this.id = id;
		this.date = date;
		this.user = user;
	}
	
	public Annotation(Date date, User user) {
		super();
		this.date = date;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
