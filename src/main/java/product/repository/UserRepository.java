package product.repository;

import product.domain.User;

public interface UserRepository {
	
	void save(User user);
	
	User getUser(String login);
	
}
