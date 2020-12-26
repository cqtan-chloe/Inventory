package team5.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import team5.model.Product;
import team5.model.RoleType;
import team5.model.StockTransaction;
import team5.model.UsageRecord;
import team5.model.User;
import team5.repo.ProductRepo;
import team5.repo.StockTransactionRepo;
import team5.repo.UsageRecordRepo;
import team5.repo.UserRepo;

@SpringBootTest
public class ProductTest {
	
	@Autowired
	public ProductRepo productRepo;
	@Autowired
	public UserRepo urepo;
	@Autowired
	public UsageRecordRepo usageRepo;
	@Autowired
	public StockTransactionRepo st_repo;
	
	@Test
	public void adduser() {
		urepo.save(new User("admin1","admin1",RoleType.ADMIN));
		urepo.save(new User("admin2","admin2",RoleType.ADMIN));
		urepo.save(new User("mec1","mec1",RoleType.MECHANIC));
		urepo.save(new User("mec2","mec2",RoleType.MECHANIC));
	}
	
	@Test
	public void saveProduct() {
		productRepo.save(new Product("Collision Cart","The original high quality, low friction dynamics carts made from durable machined aluminum.", 200,"Category A", 2000, 2, 1, 1));
		productRepo.save(new Product("Large Table Clamp","Can attach to tables, shelves, or other boards up to 10 cm thick.", 200,"Category B", 2500 , 3, 1, 1));
		productRepo.save(new Product(5432,"Tension Protractor","ME-6855", 500,"Category C", 600, 14, 1, 4));
		productRepo.save(new Product(3452,"Jeep wheel","The best wheel for jeep.", 200,"Category A", 2000, 19, 5, 2));
		productRepo.save(new Product(1000,"Tyre","ME-6855", 500,"Category C", 8000, 18, 10, 4));
		productRepo.save(new Product(1001,"Suspension","ME-6855", 500,"Category B", 5000, 15, 10, 4));
		productRepo.save(new Product(1002,"Pedal","ME-6855", 500,"Category B", 5000, 6, 10, 4));
		productRepo.save(new Product(1003,"Engine","ME-6855", 500,"Category B", 5000, 10, 10, 4));
		
	}
	

	
	@Test
	public void addtxns() {
		st_repo.save(new StockTransaction(productRepo.findById((long) 3).get(), 3));
		st_repo.save(new StockTransaction(productRepo.findById((long) 5).get(), 5));
		st_repo.save(new StockTransaction(productRepo.findById((long) 7).get(), 7));
	}
	
	@Test
	public void saveUsageRecord() throws ParseException {
		Product first = productRepo.findById((long)4).get();
		Product second = productRepo.findById((long)2).get();
		Product third = productRepo.findById((long)3).get();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date fDate = formatter.parse("01-12-2020");
		Date sDate = formatter.parse("03-12-2020");
		Date tDate = formatter.parse("05-12-2020");
		User admin1 = urepo.findById((long)1).get();
		UsageRecord oneR = usageRepo.save(new UsageRecord("john","SAA1235", fDate, admin1));
		UsageRecord secondR = usageRepo.save(new UsageRecord("Mary","SAB2345", sDate, admin1));
		usageRepo.save(new UsageRecord("Peter","SAC3456", tDate, admin1));
		st_repo.save(new StockTransaction(first,oneR,3));
		st_repo.save(new StockTransaction(second,oneR,3));
		st_repo.save(new StockTransaction(third,secondR,3));
		st_repo.save(new StockTransaction(first,secondR,3));
		st_repo.save(new StockTransaction(third,oneR,3));
	}
	
}