package team5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import team5.model.Product;

public interface ProductService extends IService<Product> {
	
	 public List<Product> searchByKeyword(String keyword);
	Product findByName(String name);
	Optional<Product> OptionalFindById(Long id);
	 
	//public Page<Product> listProducts(String keywords, int page,int size);
}
