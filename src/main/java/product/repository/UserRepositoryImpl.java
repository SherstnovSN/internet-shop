package product.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import product.domain.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User getUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, login);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

}
