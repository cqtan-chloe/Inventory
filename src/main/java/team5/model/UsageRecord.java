package team5.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;


// in addition to user and datetime
// annotate decreases in qty due to usage with other info 
@Entity
public class UsageRecord { // extends Annotation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat (pattern="dd-MM-yyyy")
	protected Date date;

	@ManyToOne
	protected User user;
	
	@NotNull
	private String carPlate;
	private String comments;
	private String customerName;
	
	@OneToMany(mappedBy = "usageRecord")
	private List<StockTransaction> stockTranxList;


	public UsageRecord() {
		super();
	}
	
	@Autowired
	@Transient
	HttpSession session;

	public UsageRecord(String customerName, String carPlate, String comments) {
		super();
		this.customerName = customerName;
		this.carPlate = carPlate;
		this.comments = comments;
		
		this.date = new Date();
		this.user = (User) session.getAttribute("user");
	}
	
	public UsageRecord(String customerName, String carPlate) {
		super();
		this.customerName = customerName;
		this.carPlate = carPlate;
		
		this.date = new Date();
		this.user = (User) session.getAttribute("user");
	}
	
	public UsageRecord(String customerName, String carPlate, User user) {
		super();
		this.customerName = customerName;
		this.carPlate = carPlate;
		
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

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public List<StockTransaction> getStockTranxList() {
		return stockTranxList;
	}

	public void setStockTranxList(List<StockTransaction> stockTranxList) {
		this.stockTranxList = stockTranxList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



}

	
