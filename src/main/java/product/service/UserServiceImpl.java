package product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import product.domain.Cart;
import product.domain.User;
import product.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(String login, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Cart cart = new Cart();
        User user = new User(login, encodedPassword, cart);
        cart.setUser(user);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getUserCart(Object user) {
        if (user.equals("anonymousUser")) return new Cart();
        User currentUser = getUser(((UserDetails) user).getUsername());
        return currentUser.getCart();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    @Transactional
    public void editRole(String login, String role) {
        getUser(login).setRole(role);
    }

}