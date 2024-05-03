package com.example.demo.Metier.Services;

import com.example.demo.Metier.entities.Event;
//import com.example.demo.Metier.entities.EventDetails;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Local
@Stateless
public class EventService {
    private EntityManagerFactory emf;

    private EntityManager em;

    public EventService()
    {
        emf= Persistence.createEntityManagerFactory("JEE") ;
        em=emf.createEntityManager();

    }
    public boolean enregistrerEvent(Event event)  {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(event);
            tr.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Event> getallEvent()
    {
        try {
            Query query = em.createQuery("SELECT e FROM Event e");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void deleteEvent(Long id)
    {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Event event = em.find(Event.class, id);
            if (event != null) {
                em.remove(event);
            }
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Event  getEvent(Long id)
    {
            return  em.find(Event.class, id);
    }

    public boolean updateEvent(Event event ,Long id)  {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Event existingEvent = em.find(Event.class, id);
            if (existingEvent == null) {
                System.out.println("--------------existingEvent-------------");
                return false; // Or throw an exception or handle it as needed
            }
            existingEvent.setTitle(event.getTitle());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setStartDate(event.getStartDate());
            existingEvent.setEndDate(event.getEndDate());
            existingEvent.setHeurs(event.getHeurs());
            existingEvent.setType(event.getType());
            em.merge(existingEvent);
            System.out.println("-----------------------aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----");
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Object[]> findAllEvent()
    {
        try {
            Query query = em.createQuery("SELECT e.id AS id,e.title AS title ,DATE_FORMAT(e.startDate,'%Y-%m-%d') AS start ,DATE_FORMAT(e.endDate,'%Y-%m-%d') AS end FROM Event e");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }




}
