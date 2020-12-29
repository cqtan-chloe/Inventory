package team5.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class StockTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	// StockTransationId
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private  UsageRecord usageRecord;	// to be set as null be default 
	
	@OneToOne//(mappedBy = "stockTranx")
	private  Annotation annotation;
	
	private long qtyChange;	// absolute value of quantity change 
	
	private String type;	// a hidden field. "restock"(+) and "use"(-) only
	
	@Transient
	private long prev_val;	// absolute value of quantity change 
	
	public StockTransaction() {
		super();
	}


	// combination of parameters means that stock is withdrawn
	public StockTransaction(Product product, UsageRecord usageRecord, long qtyChange, Annotation a) {
		super();
		this.product = product;
		this.usageRecord = usageRecord;
		this.qtyChange = qtyChange;
		this.type = "use";
		this.annotation = a;
	}
	
	// combination of parameters means that stock is returned to supplier
	public StockTransaction(Product product, long qtyChange, String type, Annotation a) {
		super();
		this.product = product;
		this.qtyChange = qtyChange;
		this.type = type;
		this.annotation = a;
	}


	// combination of parameters means that stock is added
	public StockTransaction(Product product, long qtyChange, Annotation a) {
		super();
		this.product = product;
		this.qtyChange = qtyChange;
		this.type = "restock";
		this.annotation = a;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public UsageRecord getUsageRecord() {
		return usageRecord;
	}
	public void setUsageRecord(UsageRecord usageRecord) {
		this.usageRecord = usageRecord;
	}

	public long getQtyChange() {
		return qtyChange;
	}

	public void setQtyChange(long qtyChange) {
		this.qtyChange = qtyChange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Transient
	public long getPrev_val() {
		return prev_val;
	}
	
	@Transient
	public void setPrev_val(long prev_val) {
		this.prev_val = prev_val;
	}


	public Annotation getAnnotation() {
		return annotation;
	}


	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}
	
	public Date getDate() {
		return annotation.getDate();
	}

	public long getUserId(){
		return annotation.getUser().getId();
	}
	
	public String getUserName(){
		return annotation.getUser().getUserName();
	}
	
}
