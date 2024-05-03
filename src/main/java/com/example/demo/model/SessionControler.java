package com.example.demo.model;

import com.example.demo.Metier.Services.EventService;
import com.example.demo.Metier.Services.SessionService;
import com.example.demo.Metier.entities.Event;
import com.example.demo.Metier.entities.Session;
import com.example.demo.Metier.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class SessionControler implements Serializable {

    @EJB
    private SessionService sessionService;

    private EventContoller eventContoller;

    private Session session;
    private Long eventId; // Property to hold eventId
    private User loggedUser; // Property to hold loggedUser



    private HttpSession sessionUt=SessionUtils.getSession();
    @PostConstruct
    public void start(){
        session = new Session();
        String IdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idevent");
        if (IdParam != null) {
            eventId = Long.parseLong(IdParam);
        }
        String eventIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        // Fetch the event details based on the event ID
        // You need to implement this method in your EventService
        if (eventIdParam != null) {
            eventId = Long.parseLong(eventIdParam);
        }


    }


    // Getter and setter methods for eventId
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    // Getter and setter methods for loggedUser
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public void AddSessions() {
        if (eventId == null){
        eventId=(Long) sessionUt.getAttribute("id_event");}
        System.out.println("-------------------"+eventId+"----------------");

        if (eventId != null ) {
            session.setOrganisateur((User)sessionUt.getAttribute("user"));
            sessionService.addSession(session, eventId);
            System.out.println("-------------------Sucess----------------");
        } else {
            // Handle case when eventId or loggedUser is null
            System.out.println("-------------------false----------------");
        }
    }

    public List<Session> getSessions()
    {
        if (eventId == null){
            eventId=(Long) sessionUt.getAttribute("id_event");}
        System.out.println("///////////////"+eventId);
        if(eventId!=null)
        {
            return sessionService.getSessions(eventId);
        }else {
            System.out.println("///////////////");
            return null;}
    }
    public List<Session> getSessionsU()
    {
        System.out.println("!!!!!!!!"+eventId);
        if(eventId!=null)
        {
            return sessionService.getSessions(eventId);
        }else {
            System.out.println("///////////////");
            return null;}
    }
    public String deleteSession(Long id)
    {
        System.out.println("--------------------------Id ==---------------"+id+"-------------------------------");
        sessionService.deleteSession(id);
        return "/Organisateur/index.xhtml";
    }
    public String UpdateSession(Session e,Long id ) {
        System.out.println("--------------------------Id ==---------------"+id+"-------------------------------");
        if (sessionService.updateSession(e, id)){
            return "/Organisateur/index.xhtml";
        }
        return "organisation_EventDet?error";
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
