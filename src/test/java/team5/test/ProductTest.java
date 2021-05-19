package team5.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import team5.model.Annotation;
import team5.model.Product;
import team5.model.RoleType;
import team5.model.StockTransaction;
import team5.model.TxnType;
import team5.model.UsageRecord;
import team5.model.User;
import team5.repo.AnnotationRepo;
import team5.repo.ProductRepo;
import team5.repo.StockTransactionRepo;
import team5.repo.UsageRecordRepo;
import team5.repo.UserRepo;

@SpringBootTest
public class ProductTest {
	
	@Autowired
	public ProductRepo prepo;
	@Autowired
	public UserRepo urepo;
	@Autowired
	public UsageRecordRepo usageRepo;
	@Autowired
	public StockTransactionRepo st_repo;
	@Autowired
	public AnnotationRepo arepo;
	
	@Test
	public void adduser() {
		urepo.save(new User("admin1","admin1",RoleType.ADMIN));
		urepo.save(new User("admin2","admin2",RoleType.ADMIN));
		urepo.save(new User("mec1","mec1",RoleType.MECHANIC));
		urepo.save(new User("mec2","mec2",RoleType.MECHANIC));
	}
	
	@Test
	public void saveProduct() {
		prepo.save(new Product("Collision Cart","The original high quality, low friction dynamics carts made from durable machined aluminum.", 20,"Category A", 100, 2, 1));
		prepo.save(new Product("Large Table Clamp","Can attach to tables, shelves, or other boards up to 10 cm thick.", 20,"Category B", 150 , 3, 1));
		prepo.save(new Product("Tension Protractor","ME-6855", 50,"Category C", 60, 14, 4));
		prepo.save(new Product("Jeep wheel","The best wheel for jeep.", 20,"Category A", 200, 19, 2));
		prepo.save(new Product("Tyre","ME-6855", 50,"Category C", 60, 18, 4));
		prepo.save(new Product("Suspension","ME-6855", 50,"Category B", 70, 15, 4));
		prepo.save(new Product("Pedal","ME-6855", 50,"Category B", 80, 6, 4));
		prepo.save(new Product("Engine","ME-6855", 50,"Category B", 90, 10, 4));
		
	}
	

	
	@Test
	public void addtxns() {
		Annotation a1 = arepo.save(new Annotation(urepo.findById((long)1).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 3).get(), 3, a1));
		st_repo.save(new StockTransaction(prepo.findById((long) 5).get(), 5, a1));
		st_repo.save(new StockTransaction(prepo.findById((long) 7).get(), 7, a1));
		
		Annotation a2 = arepo.save(new Annotation(urepo.findById((long)4).get()));
		UsageRecord oneR = usageRepo.save(new UsageRecord("john","SAA1235", urepo.findById((long)4).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 4).get(), oneR, 2, a2));
		st_repo.save(new StockTransaction(prepo.findById((long) 6).get(), oneR, 1, a2));
		
		Annotation a3 = arepo.save(new Annotation(urepo.findById((long)3).get()));
		UsageRecord twoR = usageRepo.save(new UsageRecord("mary","SMTH789", urepo.findById((long)3).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 3).get(), twoR, 2, a3));
		st_repo.save(new StockTransaction(prepo.findById((long) 5).get(), twoR, 4, a3));
		
		Annotation a4 = arepo.save(new Annotation(urepo.findById((long)2).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 3).get(), 1, TxnType.RETURN, a4));
		st_repo.save(new StockTransaction(prepo.findById((long) 5).get(), 1, TxnType.RETURN, a4));
		
		Annotation a5 = arepo.save(new Annotation(urepo.findById((long)2).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 4).get(), 6, a5));
		st_repo.save(new StockTransaction(prepo.findById((long) 6).get(), 4, a5));
		st_repo.save(new StockTransaction(prepo.findById((long) 8).get(), 6, a5));
		st_repo.save(new StockTransaction(prepo.findById((long) 3).get(), 2, a5));
		st_repo.save(new StockTransaction(prepo.findById((long) 5).get(), 2, a5));
		st_repo.save(new StockTransaction(prepo.findById((long) 7).get(), 2, a5));
		
		Annotation a6 = arepo.save(new Annotation(urepo.findById((long)2).get()));
		st_repo.save(new StockTransaction(prepo.findById((long) 4).get(), 1, TxnType.RETURN, a6));
		st_repo.save(new StockTransaction(prepo.findById((long) 7).get(), 1, TxnType.RETURN, a6));
	}
	
}