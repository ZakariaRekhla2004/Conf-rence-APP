package com.example.demo.model;

import com.example.demo.Metier.Services.EventService;
import com.example.demo.Metier.entities.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class EventContoller implements Serializable{
        @EJB
        private EventService eventService ;
    private Event event;
    private Event eventmore;
    private Long id;
    private Long eventId;
    private  HttpSession session=SessionUtils.getSession();

    @PostConstruct
    public void start(){
        event = new Event();
        String idevent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idevent");
        if (idevent != null) {
            eventId = Long.parseLong(idevent);
        }
        String eventIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        // Fetch the event details based on the event ID
        // You need to implement this method in your EventService
        if (eventIdParam != null) {
            eventmore = eventService.getEvent(Long.parseLong(eventIdParam));
        }
    }

    public Event getEventmore() {
        return eventmore;
    }

    public void setEventmore(Event eventmore) {
        this.eventmore = eventmore;
    }

    public Event getEvent() {

        return event;
    }

    public Event getEventById() {
        if (eventId == null){
            eventId=(Long) session.getAttribute("id_event");}

        System.out.println("  seesion --------- get att"+this.eventId);
        return eventService.getEvent(this.eventId);
    }
    public EventContoller(){
    }
    public String addEvent(){
        if (eventService.enregistrerEvent(event)){
            return "admin";
        }
        return "admin?error";
    }
    public List<Event> getEvents(){
            return eventService.getallEvent();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String deleteEvent(Long id)
    {
            eventService.deleteEvent(id);
            return "admin";
    }
    public String UpdateEvent(Event e,Long id ) {
        System.out.println("--------------------------Id ==---------------"+id+"-------------------------------");
        if (eventService.updateEvent(e, id)){
            return "admin";
        }
        return "admin?error";
    }


    public void setEvent(Event event1) {
        event1 = this.event;
    }
    public List<EventType> getEventTypes()
    {
        return Arrays.asList(EventType.values());
    }
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
