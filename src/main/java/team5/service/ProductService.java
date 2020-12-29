package team5.service;

import java.util.List;

import team5.model.Product;

public interface ProductService extends IService<Product> {
	
	public List<Product> searchByKeyword(String keyword);
	Product findByName(String name);
}
