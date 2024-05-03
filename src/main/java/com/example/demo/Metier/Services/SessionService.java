package com.example.demo.Metier.Services;


import com.example.demo.Metier.entities.Event;
import com.example.demo.Metier.entities.Role;
import com.example.demo.Metier.entities.Session;
import com.example.demo.Metier.entities.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Local
@Stateless
public class SessionService {

    private EntityManagerFactory emf;

    private EntityManager em;

    public SessionService()
    {
        emf= Persistence.createEntityManagerFactory("JEE") ;
        em=emf.createEntityManager();

    }

    public List<Session> getSessions(Long event )
    {
        System.out.println("***************************"+event);
        try {
            Query query = em.createQuery("SELECT ed FROM Session ed WHERE ed.event.id = :id").setParameter("id", event);
            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("false/*/****//*//*/*/");
            return null;
        }
    }
    public User getSession_Orga(Long event , Long id_orga)
    {
        try {

            return(User)em.createQuery("SELECT u FROM Session ed , User u where ed.event.id = :id and u.role= :role and u.id = :id").setParameter("id", event).setParameter("role", Role.ORGANIZER).setParameter("id", id_orga).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public boolean addSession(Session session, Long id)  {

        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            session.setEvent(em.find(Event.class, id));
            em.persist(session); // Use persist() to insert the session into the database
            tr.commit();
            System.out.println("Session added successfully.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback(); // Rollback the transaction if an exception occurs
            System.out.println("Failed to add session.");
            return false;
        }
    }
    public void deleteSession(Long id)
    {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Session session2 = em.find(Session.class, id);
            if (session2 != null) {
                em.remove(session2);
            }
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public boolean updateSession(Session session ,Long id)  {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Session existingsession = em.find(Session.class, id);
            if (existingsession == null) {
                System.out.println("--------------existingsession-------------");
                return false; // Or throw an exception or handle it as needed
            }
            existingsession.setSujetAbord(session.getSujetAbord());
            existingsession.setFin(session.getFin());
            existingsession.setHeureDebut(session.getHeureDebut());
            existingsession.setInterName(session.getInterName());
            existingsession.setLinkdAcc(session.getLinkdAcc());
            existingsession.setInstaAcc(session.getInstaAcc());

            existingsession.setOrganisateur(existingsession.getOrganisateur());
            existingsession.setEvent(existingsession.getEvent());
            em.merge(existingsession);
            System.out.println("-----------------------aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----");
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
