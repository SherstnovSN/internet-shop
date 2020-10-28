package product.controller;

import java.util.List;

import product.domain.Cart;
import product.domain.Product;
import product.service.ProductService;
import product.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalogPage(@AuthenticationPrincipal Object user, Model model) {
        List<Product> products = productService.getAll();
        Cart cart = userService.getUserCart(user);
        model.addAttribute("cart", cart);
        model.addAttribute("productList", products);
        return "catalog";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String adminPage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("productList", products);
        return "product";
    }

    @RequestMapping(value = "/add-new-product", method = RequestMethod.GET)
    public String addNewProductPage() {
        return "addNewProduct";
    }

    @RequestMapping(value = "/add-new-product", method = RequestMethod.POST)
    public String addNewProduct(@RequestParam(value = "title") String title,
                                @RequestParam(value = "price") double price,
                                @RequestParam(value = "image") MultipartFile imageFile) {
        productService.save(title, price, imageFile);
        return "redirect:/product";
    }

    @RequestMapping(value = "/edit-product/{id}", method = RequestMethod.GET)
    public String editProductPage(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @RequestMapping(value = "/edit-product", method = RequestMethod.POST)
    public String editProduct(@RequestParam(value = "id") int id,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "price") double price,
                              @RequestParam(value = "image") MultipartFile imageFile) {
        productService.edit(id, title, price, imageFile);
        return "redirect:/product";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/product";
    }

}
