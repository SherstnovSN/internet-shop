package product.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import product.domain.User;

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

}
