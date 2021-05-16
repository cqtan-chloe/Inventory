package team5.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.model.Annotation;
import team5.model.Product;
import team5.model.StockLevelStatus;
import team5.model.StockTransaction;
import team5.model.TxnType;
import team5.model.UsageRecord;
import team5.model.User;
import team5.repo.AnnotationRepo;
import team5.repo.StockTransactionRepo;
import team5.repo.UserRepo;


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
	UserRepo urepo;
    
    @Autowired 
    HttpSession session;
    
	@Autowired
	private EmailService emailService;
	
	
	public StockTransaction createNewTxn(Long usageRecord_id) {
		//User user = (User) session.getAttribute("user");
		User user = urepo.findByUserName("admin1");
		Annotation a = arepo.save(new Annotation(user));
		
		UsageRecord ur;
		if (usageRecord_id == -1) {ur = null;} else {ur = ur_svc.findById(usageRecord_id);}
		
		// StockTransaction(Product product, TxnType txntype, long qtyChange, UsageRecord usageRecord, Annotation a)
		StockTransaction out = new StockTransaction(null, null, 0, ur, a);
		out.setPrev_val((long) 0);
		
		return out;
	}
	
	public void save(StockTransaction txn) {

		txn.setProduct(product_svc.findById(txn.getProduct().getId()));
		strepo.save(txn);
		
		// saving stock transaction records always means that the product involved will be updated ...
		this.modifyProductRecord(txn);
		
		// ... and a notification will be sent if the products needs to be restocked 
		this.sendNotifications(txn);
	}
	
	private void modifyProductRecord(StockTransaction txn) {
		Product p = txn.getProduct();
		
		p = this.changeProductQty(txn, p);
		p.setStatus(p.detStockLevelStatus());
		product_svc.save(p);
	}
	
	private void sendNotifications(StockTransaction txn) {
		Product p = txn.getProduct();
		
		if(p.getStatus().equals(StockLevelStatus.DEPLETED) | p.getStatus().equals(StockLevelStatus.INSUFFICIENT))
			this.notifyLowStock(p);
	}
	
	private Product changeProductQty(StockTransaction txn, Product p) {
		
		TxnType txntype = txn.getType();
		
		long prev_val = txn.getPrev_val();
		long qtyChange = txn.getQtyChange();
		
		if (txntype == TxnType.USE | txntype == TxnType.RETURN | txntype == TxnType.CORRECTION_SUBTRACT) {
			prev_val *= -1;	// convert values to negative
			qtyChange *= -1;
		}
		
		p.setQty(p.getQty() - prev_val + qtyChange);	
		
		return p;
	}
	
	private void notifyLowStock(Product p) {
		String s = "Product: " + p.getName() + "\n" +  
					"Qty: " + p.getQty() + "\n" + 
					"MinReorderLevel: " +  p.getMinReorderLevel() + "\n";
		
		if (p.getQty() < p.getMinReorderLevel())
			emailService.sendMail("c.q.tan94@gmail.com", "Notification to restock", s);
	}

	@Override
	public StockTransaction findById(Long id) {
		StockTransaction txn = strepo.findById(id).get();
		txn.setPrev_val(txn.getQtyChange());
		return txn;
	}

	@Override
	public List<StockTransaction> findAll() {
		return strepo.findAll(); 
	}
	
	@Override
	public void deleteById(Long id) {
		StockTransaction txn = strepo.findById(id).get();
		
		// deleting stock transaction records always means that changes made to product will be reversed ...
		this.modifyProductRecord(txn);
		
		// ... and a notification will be sent if the products needs to be restocked 
		this.sendNotifications(txn);
		
		// stock transaction record can be safely deleted 
		strepo.deleteById(id);
	}

}
