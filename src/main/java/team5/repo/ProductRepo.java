package team5.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import team5.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product>{
	
	@Query("Select p from Product as p where p.name LIKE %?1%" + " OR p.description LIKE %?1%" 
			+ " OR p.originalPrice LIKE %?1%" + " OR p.category LIKE %?1%"
			+ " OR p.priceFRetail LIKE %?1%" 		
			+ " OR p.qty LIKE %?1%" + " OR p.reorderLevel LIKE %?1%")
	public List<Product> find_withfilter1(String keyword);
}
