package team5.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import team5.model.RoleType;
import team5.model.StockTransaction;
import team5.model.User;
import team5.repo.ProductRepo;
import team5.repo.StockTransactionRepo;
import team5.repo.UserRepo;

@SpringBootTest
public class txns {
	
	@Autowired
	StockTransactionRepo st_repo;
	
	@Autowired
	public ProductRepo productRepo;
	
	@Test
	public void addtxns() {
		st_repo.save(new StockTransaction(productRepo.findById(3), 3));
		st_repo.save(new StockTransaction(productRepo.findById(5), 5));
		st_repo.save(new StockTransaction(productRepo.findById(7), 7));
	}

	
}
