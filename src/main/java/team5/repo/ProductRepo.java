package team5.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team5.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product>{
	
	@Query("Select s from Product s where s.name = :nm")
	List<Product> findByName(@Param("nm")String name);
	
	@Query("Select p from Product as p where p.name LIKE %?1%" + " OR p.description LIKE %?1%" 
			+ " OR p.originalPrice LIKE %?1%" + " OR p.category LIKE %?1%"
			+ " OR p.priceFRetail LIKE %?1%" 		
			+ " OR p.qty LIKE %?1%" + " OR p.reorderLevel LIKE %?1%"
			+ " OR p.minReoderLevel LIKE %?1%")
	public List<Product> search(String keyword);
}
