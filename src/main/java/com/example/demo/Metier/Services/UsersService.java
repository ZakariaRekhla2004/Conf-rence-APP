package com.example.demo.Metier.Services;

import com.example.demo.Metier.entities.Event;
import com.example.demo.Metier.entities.Role;
import com.example.demo.Metier.entities.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Local
@Stateless
public class UsersService {

    private EntityManagerFactory emf;

    private EntityManager em;

    public UsersService()
    {
        emf= Persistence.createEntityManagerFactory("JEE") ;
        em=emf.createEntityManager();

    }
    public void registerUser(User user1)  {
        EntityTransaction tr = em.getTransaction();
        try {
        tr.begin();
        em.persist(user1);
        tr.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getallUser()
    {
        try {
            Query query = em.createQuery("SELECT e FROM User e");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void updateRole(Long id) {

        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
                User user = em.find(User.class, id);
        if (user != null) {
            user.setRole(Role.ORGANIZER);
            em.merge(user);
        }
        tr.commit();
        } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public User  getuser(Long id)
    {
        return  em.find(User.class, id);
    }
    public User login(String email, String password) {
        try {
            User query = em.createQuery("SELECT u FROM User u WHERE u.email = :email and u.password = :password", User.class).setParameter("password",password).setParameter("email", email).getSingleResult();

            if (query != null) {
                    return query;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
