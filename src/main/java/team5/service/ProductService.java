package team5.service;

import java.util.List;

import team5.model.Product;

public interface ProductService extends IService<Product> {
	
	public List<Product> find_withfilter(String keyword);
}
