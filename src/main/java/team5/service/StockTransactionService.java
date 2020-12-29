package team5.service;

import team5.model.StockTransaction;

public interface StockTransactionService extends IService<StockTransaction> {
	public StockTransaction makeNewTxn(String type, long id);

	public void changeProductQty(StockTransaction txn);
}
