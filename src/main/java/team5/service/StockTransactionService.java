package team5.service;

import team5.model.Product;
import team5.model.StockTransaction;

public interface StockTransactionService extends IService<StockTransaction> {
	public StockTransaction makeNewTxn(String type, long id);

	public void changeProductQty(StockTransaction txn, Product p);
	public void notifyLowStock(Product p);
}
