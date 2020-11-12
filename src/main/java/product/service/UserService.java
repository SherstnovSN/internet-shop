package product.service;

import product.domain.Cart;
import product.domain.User;

import java.util.List;

public interface UserService {
	
	void save(String login, String password);
	
	User getUser(String login);
	
	Cart getUserCart(Object user);

	List<User> getAll();

	void editRole(String login, String role);

}
