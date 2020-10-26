package product.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import product.domain.Cart;

@Repository
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(cart);
    }

    @Override
    public void delete(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cart);
    }

    @Override
    public Cart getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cart.class, id);
    }

}
