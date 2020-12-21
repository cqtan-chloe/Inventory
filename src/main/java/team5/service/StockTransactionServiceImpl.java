package team5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.StockTransaction;
import team5.repo.StockTransactionRepo;


@Service
public class StockTransactionServiceImpl implements StockTransactionService {
	
	
	@Autowired
	StockTransactionRepo strepo;
	
	public void save(StockTransaction st) {
		strepo.save(st);
	}
	
	public void delete(StockTransaction st) {
		strepo.delete(st);
	}

	@Override
	public StockTransaction findById(Long id) {
		return strepo.findById(id).get();
	}

	@Override
	public List<StockTransaction> findAll() {
		return strepo.findAll(); 
	}
	

}
