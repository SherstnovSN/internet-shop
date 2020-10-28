package product.service;

import product.domain.Cart;
import product.domain.User;

public interface UserService {
	
	void save(String login, String password);
	
	User getUser(String login);
	
	Cart getUserCart(Object user);

}
