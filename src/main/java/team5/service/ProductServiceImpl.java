package team5.service;

import java.util.List;

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
	public Product create() {
		//Product p = new Product();
		
		// existing record is replaced. no isExist error. 
		Product p = new Product(2, "dfgdffg", "vbcvbvc", 50, "ggfhfgh", 100, 0, 5, 5);
		return p;
	}
	
	@Override
	public void save(Product p) {
		boolean exists = prepo.existsById(p.getId());
		
		if (exists) {
			System.out.println("item with id " + p.getId() + " exists");
		} else {
			prepo.save(p);
		}
	}
	
	@Override
	public Product findById(Long id) {
		return prepo.findById(id).get();
	}
	
	public List<Product> find(String keyword) {       
		if (keyword == null) keyword = "";
		
		return prepo.find_withfilter1(keyword);
    }
	
	@Override
	public void deleteById(Long id) {
		prepo.deleteById(id);
	}

	@Override
	public List<Product> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
