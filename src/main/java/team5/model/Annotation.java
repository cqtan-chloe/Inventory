package team5.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;


// annotate changes to qty with user and datetime 
@Entity
public class Annotation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat (pattern="dd-MM-yyyy")
	protected Date date;

	@ManyToOne
	protected User user;
	
	@OneToOne
	private StockTransaction stockTranx;
	
	@Autowired
	@Transient
	HttpSession session;
	
	public Annotation() {
		super();
	}
	
	public Annotation(int i) { // fake params 
		super();
		this.date = new Date();
		this.user = (User) session.getAttribute("user");
	}
	
	public Annotation(User user) {
		super();
		this.date = new Date();
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

