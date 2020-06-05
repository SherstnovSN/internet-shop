package product.repository;

import product.domain.Cart;

public interface CartRepository {
	
	void save(Cart cart);
	
	void delete(Cart cart);
	
	Cart getById(int id);

}
