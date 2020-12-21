package team5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FixsetDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Product product;
	
	private int quantity;
	
	@ManyToOne
	private Fixset fixset;

	public FixsetDetails() {
		super();
	}

	
	public FixsetDetails(Product product, int quantity) {
	super();
	this.product = product;
	this.quantity = quantity;
	}


	public FixsetDetails(Product product, int quantity, Fixset fixset) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.fixset = fixset;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Fixset getFixset() {
		return fixset;
	}

	public void setFixset(Fixset fixset) {
		this.fixset = fixset;
	}

	@Override
	public String toString() {
		return "FixsetDetails [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}
	
	
	

}
