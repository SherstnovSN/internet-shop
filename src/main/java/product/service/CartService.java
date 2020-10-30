package product.service;

public interface CartService {

	void addProduct(Object User, int productId);
	
	void increaseQuantity(Object user, int productId);
	
	void reduceQuantity(Object user, int productId);
	
	void deleteProduct(Object user, int productId);
	
	void clearCart(Object user);
	
}
