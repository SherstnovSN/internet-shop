package product.service;

import product.domain.Cart;

public interface CartService {

	void save(Cart cart);
	
	void delete(Cart cart);
	
	Cart getById(int id);
			
	void addProduct(Object User, int productId);
	
	void increaseQuantity(Object user, int productId);
	
	void reduceQuantity(Object user, int productId);
	
	void deleteProduct(Object user, int productId);
	
	void clearCart(Object user);
	
}
