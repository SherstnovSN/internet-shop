package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.domain.Cart;
import product.domain.CartProduct;
import product.domain.Product;
import product.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	@Transactional
	public void save(Cart cart) {
		cartRepository.save(cart);
	}
	
	@Override
	@Transactional
	public void delete(Cart cart) {
		cartRepository.delete(cart);
	}

	@Override
	@Transactional
	public Cart getById(int id) {
		return cartRepository.getById(id);
	}
	
	@Override
	@Transactional
	public void addProduct(Object user, int productId) {
		
		Cart cart = userService.getUserCart(user);
		Product product = productService.getById(productId);
		CartProduct currentProduct = cart.findProduct(productId);
		
		if (currentProduct != null) {
			currentProduct.setQuantity(currentProduct.getQuantity() + 1);
		}
		else {
			CartProduct newProduct = new CartProduct(cart, product, 1);
			cart.addProduct(newProduct);
		}
		
		cart.setTotalProducts(cart.getTotalProducts() + 1);
		cart.setTotalCost(cart.getTotalCost() + product.getPrice());
	}
	
	@Override
	@Transactional
	public void increaseQuantity(Object user, int productId) {
		
		Cart cart = userService.getUserCart(user);
		Product product = productService.getById(productId);
		CartProduct currentProduct = cart.findProduct(productId);
		
		cart.setTotalProducts(cart.getTotalProducts() + 1);
		currentProduct.setQuantity(currentProduct.getQuantity() + 1);
		cart.setTotalCost(cart.getTotalCost() + product.getPrice());
	}
	
	@Override
	@Transactional
	public void reduceQuantity(Object user, int productId) {
		
		Cart cart = userService.getUserCart(user);
		Product product = productService.getById(productId);
		CartProduct currentProduct = cart.findProduct(productId);
		
		if (currentProduct.getQuantity() > 1) {
			currentProduct.setQuantity(currentProduct.getQuantity() - 1);
			cart.setTotalProducts(cart.getTotalProducts() - 1);
			cart.setTotalCost(cart.getTotalCost() - product.getPrice());
		}
		else {
			cart.deleteProduct(currentProduct);
			cart.setTotalProducts(cart.getTotalProducts() - 1);
			cart.setTotalCost(cart.getTotalCost() - product.getPrice());
		}
	}
	
	@Override
	@Transactional
	public void deleteProduct(Object user, int productId) {
		
		Cart cart = userService.getUserCart(user);
		CartProduct currentProduct = cart.findProduct(productId);
		
		int currentQuantity = currentProduct.getQuantity();
		
		int currentTotalProducts = currentProduct.getCart().getTotalProducts();
		int newTotalProducts = currentTotalProducts - currentQuantity;
		currentProduct.getCart().setTotalProducts(newTotalProducts);
		
		double currentProductCost = currentProduct.calculateCost();
		double currentTotalCost = currentProduct.getCart().getTotalCost();
		double newTotalCost = currentTotalCost - currentProductCost;
		currentProduct.getCart().setTotalCost(newTotalCost);
				
		cart.deleteProduct(currentProduct);
	}
	
	@Override
	@Transactional
	public void clearCart(Object user) {
		Cart cart = userService.getUserCart(user);
		cart.clear();
	}
}
