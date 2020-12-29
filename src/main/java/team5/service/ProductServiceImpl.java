package team5.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.Product;
import team5.repo.ProductRepo;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo prepo;
	
	@Override
	public void save(Product product) {
		prepo.save(product);
	}
	
	@Override
	public Product findById(Long id) {
		return prepo.findById(id).get();
	}
	
	@Override
	public Product findByName(String name) {
		return prepo.findByName(name).get(0);
	}
	
	@Override
	public List<Product> findAll(){
		return prepo.findAll(); 
	}
	
	@Override
    public List<Product> searchByKeyword(String keyword) {
        if (keyword != null & keyword != "") {
            return prepo.search(keyword);
        }
        return prepo.findAll();
    }
	
	@Override
	public void delete(Product product) {
		prepo.delete(product);
	}

}
