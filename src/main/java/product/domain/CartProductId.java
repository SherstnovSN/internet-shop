package product.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartProductId implements Serializable {

    private static final long serialVersionUID = 8415040186608735618L;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_id")
    private int productId;

    public CartProductId() {
    }

    public CartProductId(int cartId, int productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public int getCart() {
        return cartId;
    }

    public void setCart(int cartId) {
        this.cartId = cartId;
    }

    public int getProduct() {
        return productId;
    }

    public void setProduct(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductId other = (CartProductId) o;
        return cartId == other.cartId && productId == other.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }

}
