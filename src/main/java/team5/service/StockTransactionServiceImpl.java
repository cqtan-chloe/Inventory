package team5.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.Annotation;
import team5.model.Product;
import team5.model.StockTransaction;
import team5.model.UsageRecord;
import team5.model.User;
import team5.repo.AnnotationRepo;
import team5.repo.StockTransactionRepo;


@Service
public class StockTransactionServiceImpl implements StockTransactionService {
	
	@Autowired
	StockTransactionRepo strepo;
	
    @Autowired
    AnnotationRepo arepo;
    
    @Autowired
    UsageRecordService ur_svc;
    
	@Autowired
	private ProductService product_svc;
    
    @Autowired 
    HttpSession session;
	
	public StockTransaction makeNewTxn(String type, long id) {
		
		User user = (User) session.getAttribute("user");
		Annotation a = arepo.save(new Annotation(user));
		
		UsageRecord ur;
		if (id == -1) {ur = null;} else {ur = ur_svc.findById(id);}
		
		StockTransaction out = new StockTransaction(null, 0, a);	   // "restock" by default. type == "restock" for this params combo.
		
		if (type == "use") out = new StockTransaction(null, ur, 0, a); // type == "use" for this params combo
		if (type == "return") out = new StockTransaction(null, 0, "return", a);
		
		return out;
	}
	
	public void changeProductQty(StockTransaction txn) {
		Product p = product_svc.findById(txn.getProduct().getId());
		p.setQty(p.getQty() - txn.getPrev_val() + txn.getQtyChange());
		
		product_svc.save(p);
	}
	
	public void save(StockTransaction st) {
		strepo.save(st);
	}

	@Override
	public StockTransaction findById(Long id) {
		return strepo.findById(id).get();
	}

	@Override
	public List<StockTransaction> findAll() {
		return strepo.findAll(); 
	}
	
	public void delete(StockTransaction st) {
		strepo.delete(st);
	}
}
