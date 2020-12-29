package team5.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import team5.model.UsageRecord;

public interface UsageRecordRepo extends JpaRepository<UsageRecord, Long> {
	
}
