package team5.service;

import team5.model.StockTransaction;

public interface StockTransactionService extends IService<StockTransaction> {

	StockTransaction createNewTxn(Long usagerecord_id);
}
