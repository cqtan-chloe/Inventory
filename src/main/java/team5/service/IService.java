package team5.service;

import java.util.List;

// this is a generic interface for creating CRUD service implementations 
public interface IService<T> {
	
	// Create, Update 
	public T create();
	public void save(T x);
	
	// Read 
	public T findById(Long id);
	public List<T> find();
	
	// Delete 
	public void deleteById(Long id);
}
