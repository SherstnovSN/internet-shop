package product.repository;

import java.util.List;

import product.domain.Product;

public interface ProductRepository {
	
	void save(Product product);
	   
	void delete(Product product);
    
	void edit(Product product);
   
	List<Product> getAll();
   
	Product getById(int id);
    
}
