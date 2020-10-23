package product.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "total_products")
    private int totalProducts = 0;

    @Column(name = "total_cost")
    private double totalCost = 0;

    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cart")
    private Set<CartProduct> products = new HashSet<>();

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<CartProduct> products) {
        this.products = products;
    }

    public void addProduct(CartProduct newProduct) {
        products.add(newProduct);
    }

    public CartProduct findProduct(int productId) {
        for (CartProduct cartProduct : products) {
            if (cartProduct.getProduct().getId() == productId) return cartProduct;
        }
        return null;
    }

    public void deleteProduct(CartProduct currentProduct) {
        products.remove(currentProduct);
    }

    public void clear() {
        products.clear();
        totalProducts = 0;
        totalCost = 0;
    }
}
