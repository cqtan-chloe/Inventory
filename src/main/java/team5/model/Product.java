package team5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;

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
	private long qty;

	@OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
	private List<StockTransaction> stockTranxList;

	private long reorderLevel;
	private long minReoderLevel;

	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(long id, String name, String description, long originalPrice, String category,
			long priceFRetail, long qty, long reorderLevel, long minReoderLevel) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.originalPrice = originalPrice;
		this.category = category;
		this.priceFRetail = priceFRetail;
		this.qty = qty;
		this.reorderLevel = reorderLevel;
		this.minReoderLevel = minReoderLevel;
	}
	
	public Product(String name, String description, long originalPrice, String category,
			long priceFRetail, long qty, long reorderLevel, long minReoderLevel) {
		super();
		this.name = name;
		this.description = description;
		this.originalPrice = originalPrice;
		this.category = category;
		this.priceFRetail = priceFRetail;
		this.qty = qty;
		this.reorderLevel = reorderLevel;
		this.minReoderLevel = minReoderLevel;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description
				+ ", originalPrice=" + originalPrice + ", category=" + category
				+ ", priceFRetail=" + priceFRetail + ", reorderLevel=" 
				+ reorderLevel + ", minReoderLevel=" + minReoderLevel + "]";
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

	public long getMinReoderLevel() {
		return minReoderLevel;
	}

	public void setMinReoderLevel(long minReoderLevel) {
		this.minReoderLevel = minReoderLevel;
	}


}
