package product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import product.domain.Product;

public interface ProductService {
	
	void save(String title, double price, MultipartFile imageFile);
    
	void edit(int id, String title, double price, MultipartFile imageFile);
    
	void delete(int id);
   
	List<Product> getAll();
   
	Product getById(int id);
	
}
