package product.repository;

import product.domain.User;

import java.util.List;

public interface UserRepository {
	
	void save(User user);
	
	User getUser(String login);

	List<User> getAll();
	
}
