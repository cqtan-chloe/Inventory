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
    
	@Autowired
	private EmailService emailService;
	
	public StockTransaction makeNewTxn(String type, long id) {
		
		User user = (User) session.getAttribute("user");
		Annotation a = arepo.save(new Annotation(user));
		
		UsageRecord ur;
		if (id == -1) {ur = null;} else {ur = ur_svc.findById(id);}
		
		StockTransaction out = new StockTransaction(null, 1, a);	   // "restock" by default. type == "restock" for this params combo.
		
		if (type == "use") out = new StockTransaction(null, ur, 1, a); // type == "use" for this params combo
		if (type == "return") out = new StockTransaction(null, 1, "return", a);
		
		return out;
	}
	
	public void save(StockTransaction txn) {

		if (txn.getType().equals("use")) {
			if (txn.getUsageRecord().getId() == 0) {txn.setUsageRecord(null);}
		}
		
		strepo.save(txn);
		
		Product p = product_svc.findById(txn.getProduct().getId());
		
		this.changeProductQty(txn, p);
		this.notifyLowStock(p);
	}
	
	public void changeProductQty(StockTransaction txn, Product p) {
		// type of stock transaction is defined by developer 
		// user cannot define new types of transactions 
		if (txn.getType().equals("use")| txn.getType().equals("return"))
			p.setQty(p.getQty() + txn.getPrev_val() - txn.getQtyChange());
		else 
			p.setQty(p.getQty() - txn.getPrev_val() + txn.getQtyChange());	
		
		product_svc.save(p);
	}
	
	public void notifyLowStock(Product p) {
		String s = "Product: " + p.getName() + "\n" +  
					"Qty: " + p.getQty() + "\n" + 
					"MinReorderLevel: " +  p.getMinReoderLevel() + "\n";
		
		if (p.getQty() < p.getMinReoderLevel())
			emailService.sendMail("c.q.tan94@gmail.com", "Notification to restock", s);
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
