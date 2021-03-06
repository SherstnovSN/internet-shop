package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import product.domain.Cart;
import product.service.CartService;
import product.service.UserService;

@Controller
public class CartController {
		
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/buy", method=RequestMethod.GET)
	public @ResponseBody int buyProduct(@AuthenticationPrincipal Object user, 
					    @RequestParam String id) {
		cartService.addProduct(user, Integer.parseInt(id));
		return userService.getUserCart(user).getTotalProducts();
	}

	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public String cartPage(@AuthenticationPrincipal Object user, Model model) {
		Cart cart = userService.getUserCart(user);
		model.addAttribute("cart", cart);
		return "cart";
	}
	
	@RequestMapping(value="/increase-product-quantity/{id}", method=RequestMethod.GET)
	public String increaseProductQuantity(@AuthenticationPrincipal Object user, 
					      @PathVariable int id) {
		cartService.increaseQuantity(user, id);
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/reduce-product-quantity/{id}", method=RequestMethod.GET)
	public String reduceProductQuantity(@AuthenticationPrincipal Object user, 
					    @PathVariable int id) {
		cartService.reduceQuantity(user, id);
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/delete-product-from-cart/{id}", method=RequestMethod.GET)
	public String deleteProductFromCart(@AuthenticationPrincipal Object user,
					    @PathVariable int id) {		
		cartService.deleteProduct(user, id);
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/clear", method=RequestMethod.GET)
	public String clearCart(@AuthenticationPrincipal Object user) {
		cartService.clearCart(user);
		return "redirect:/cart";
	}
}
