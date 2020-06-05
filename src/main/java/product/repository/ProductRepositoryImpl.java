package product.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import product.domain.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(product);
	}
	
	@Override
        public void delete(Product product) {
    		Session session = sessionFactory.getCurrentSession();
    		session.delete(product);
        }
	
	@Override
        public void edit(Product product) {
            	Session session = sessionFactory.getCurrentSession();
            	session.update(product);
        }
	
	@Override
	@SuppressWarnings("unchecked")
        public List<Product> getAll() {
    		Session session = sessionFactory.getCurrentSession();
    		return session.createQuery("from Product").list();
        }
	
	@Override
        public Product getById(int id) {
    		Session session = sessionFactory.getCurrentSession();
    		return session.get(Product.class, id);
        }

}
