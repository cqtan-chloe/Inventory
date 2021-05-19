package team5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;
	private String description;
	@Digits(integer = 8, fraction = 2)
	private long originalPrice;
	private String category;
	@Digits(integer = 8, fraction = 2)
	private long priceFRetail;
	@Min(0)
	private long qty;

	@OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
	private List<StockTransaction> stockTranxList;

	private long reorderLevel;

	private StockLevelStatus status;
	
	public StockLevelStatus detStockLevelStatus() {
		if (this.qty == 0) 
			status = StockLevelStatus.DEPLETED;
		else if (this.qty > 0 && this.qty < this.reorderLevel)
			status = StockLevelStatus.INSUFFICIENT;
		else 
			status = StockLevelStatus.SUFFICIENT;
		
		return status;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(long id, String name, String description, long originalPrice, String category,
			long priceFRetail, long qty, long reorderLevel) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.originalPrice = originalPrice;
		this.category = category;
		this.priceFRetail = priceFRetail;
		this.qty = qty;
		this.reorderLevel = reorderLevel;
		this.status = detStockLevelStatus();
	}
	
	public Product(String name, String description, long originalPrice, String category,
			long priceFRetail, long qty, long reorderLevel) {
		super();
		this.name = name;
		this.description = description;
		this.originalPrice = originalPrice;
		this.category = category;
		this.priceFRetail = priceFRetail;
		this.qty = qty;
		this.reorderLevel = reorderLevel;
		this.status = detStockLevelStatus();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getPriceFRetail() {
		return priceFRetail;
	}

	public void setPriceFRetail(long priceFRetail) {
		this.priceFRetail = priceFRetail;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public List<StockTransaction> getStockTranxList() {
		return stockTranxList;
	}

	public void setStockTranxList(List<StockTransaction> stockTranxList) {
		this.stockTranxList = stockTranxList;
	}

	public long getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(long reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public StockLevelStatus getStatus() {
		status = detStockLevelStatus();	// re-evaluate
		return status;
	}

	public void setStatus(StockLevelStatus status) {
		this.status = status;
	}
}
