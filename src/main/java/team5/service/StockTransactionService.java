package team5.service;

import team5.model.StockTransaction;

public interface StockTransactionService extends IService<StockTransaction> {

	StockTransaction create(Long usagerecord_id);
}
