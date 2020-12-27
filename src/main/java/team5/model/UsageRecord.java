package team5.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


// in addition to user and datetime
// annotate decreases in qty due to usage with other info 
@Entity
public class UsageRecord extends Annotation {

	private String carPlate;
	private String comments;
	private String customerName;
	private String status;
	
	@OneToMany(mappedBy = "usageRecord")
	private List<StockTransaction> stockTranxList;

	public UsageRecord() {
		super();
	}

	public UsageRecord(String customerName, String carPlate, String status) {

		super();
		this.carPlate = carPlate;
		this.customerName = customerName;
		this.status = status;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}


	public List<StockTransaction> getStocktranx() {
		return stockTranxList;
	}

	public void setStocktranx(List<StockTransaction> stocktranx) {
		this.stockTranxList = stocktranx;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

	
