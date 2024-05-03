package com.example.demo.Metier.entities;

import jakarta.persistence.*;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date  endDate;

    @Column(nullable = false)
    private int  heurs;




    @Enumerated(EnumType.STRING)
    private EventType type;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Session> sessions = new ArrayList<>();
    public int getHeurs() {
        return heurs;
    }
    public void setHeurs(int heurs) {
        this.heurs = heurs;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Event() {
    }


    public Event(String title, String description, Date sdateTime,Date dateTime, int heurs, EventType type) {
        this.title = title;
        this.description = description;
        this.startDate = sdateTime;
        this.endDate = dateTime;
        this.heurs = heurs;

        this.type = type;
    }

    public Event(String title, String description, Date sdateTime,Date dateTime, int heurs, EventType type, List<Session> sessions) {
        this.title = title;
        this.description = description;
        this.startDate = sdateTime;
        this.endDate = dateTime;
        this.heurs = heurs;
        this.type = type;
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
