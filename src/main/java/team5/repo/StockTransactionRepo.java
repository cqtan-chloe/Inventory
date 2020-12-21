package team5.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.model.StockTransaction;

public interface StockTransactionRepo extends JpaRepository<StockTransaction, Long> {

}
