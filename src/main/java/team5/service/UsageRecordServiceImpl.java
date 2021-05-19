package team5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team5.model.UsageRecord;
import team5.repo.UsageRecordRepo;

@Service
public class UsageRecordServiceImpl implements UsageRecordService {
	
	@Autowired
	UsageRecordRepo urrepo;
	
	
	@Override
	public UsageRecord create() {
		UsageRecord ur = new UsageRecord();
		return ur;
	}
	
	public void save(UsageRecord ur) {
		urrepo.save(ur);
	}
	
	@Transactional
	public UsageRecord findById(Long id) {
		return urrepo.findById(id).get();
	}
	
	@Transactional
	public List<UsageRecord> find(){
		return urrepo.findAll();
	}
	
	@Override
	public void deleteById(Long id) {
		urrepo.deleteById(id);
	}

}
