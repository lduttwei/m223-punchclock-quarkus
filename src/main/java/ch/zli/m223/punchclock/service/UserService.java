package ch.zli.m223.punchclock.service;


import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    public UserService() {
    }


    @Transactional
    public User creatUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void remove(Long id) {
        entityManager.persist(getUser(id));
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    public boolean validateUser(User user){
        return entityManager.contains(user);
    }

    public User getUser(String username){
        var query = entityManager.createQuery("FROM User where username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
    public User getUser(Long id){
        return entityManager.find(User.class, id);
    }
}
