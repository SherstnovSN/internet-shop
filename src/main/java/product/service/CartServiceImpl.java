package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.domain.Cart;
import product.domain.CartProduct;
import product.domain.Product;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public void addProduct(Object user, int productId) {
        Cart cart = userService.getUserCart(user);
        Product product = productService.getById(productId);
        CartProduct currentProduct = cart.findProduct(productId);
        if (currentProduct.getQuantity() == 0) {
            currentProduct.setCart(cart);
            currentProduct.setProduct(product);
            cart.addProduct(currentProduct);
        }
        changeQuantity(cart, product, currentProduct, 1);
    }

    @Override
    @Transactional
    public void increaseQuantity(Object user, int productId) {
        Cart cart = userService.getUserCart(user);
        changeQuantity(cart, productService.getById(productId), cart.findProduct(productId), 1);
    }

    @Override
    @Transactional
    public void reduceQuantity(Object user, int productId) {
        Cart cart = userService.getUserCart(user);
        changeQuantity(cart, productService.getById(productId), cart.findProduct(productId), -1);
    }

    @Override
    @Transactional
    public void deleteProduct(Object user, int productId) {
        Cart cart = userService.getUserCart(user);
        CartProduct currentProduct = cart.findProduct(productId);
        changeQuantity(cart, productService.getById(productId), currentProduct, -currentProduct.getQuantity());
    }

    @Override
    @Transactional
    public void clearCart(Object user) {
        Cart cart = userService.getUserCart(user);
        cart.clear();
    }

    private void changeQuantity(Cart cart, Product product, CartProduct currentProduct, int change) {
        int currentQuantity = currentProduct.getQuantity();
        if (change < 0 && (currentQuantity + change) < 1) {
            cart.deleteProduct(currentProduct);
            cart.setTotalProducts(cart.getTotalProducts() - currentQuantity);
        } else {
            currentProduct.setQuantity(currentQuantity + change);
            cart.setTotalProducts(cart.getTotalProducts() + change);
        }
        cart.setTotalCost(cart.getTotalCost() + product.getPrice() * change);
    }

}