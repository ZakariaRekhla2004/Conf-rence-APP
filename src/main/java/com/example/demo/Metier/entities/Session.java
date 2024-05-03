package com.example.demo.Metier.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String sujetAbord;
    @Column(nullable = false)
    private String heureDebut;
    @Column(nullable = false)
    private String fin;

    @Column(nullable = false)
    private String interName;

    @Column(nullable = false)
    private String instaAcc;



    @Column(nullable = false)
    private String LinkdAcc;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private User organisateur;

    public Session() {
    }
    public Session(String sujet_abord, String heureDebut, String fin, String interName, String instaAcc, String linkdAcc, Event event) {
        this.sujetAbord = sujet_abord;
        this.heureDebut = heureDebut;
        this.fin = fin;
        this.interName = interName;
        this.instaAcc = instaAcc;
        this.LinkdAcc = linkdAcc;
        this.event = event;
    }

    public Session(String sujet_abord, String heureDebut, String fin, String interName, String instaAcc, String linkdAcc) {
        this.sujetAbord = sujet_abord;
        this.heureDebut = heureDebut;
        this.fin = fin;
        this.interName = interName;
        this.instaAcc = instaAcc;
        LinkdAcc = linkdAcc;
    }

    public String getSujetAbord() {
        return sujetAbord;
    }

    public void setSujetAbord(String sujetAbord) {
        this.sujetAbord = sujetAbord;
    }

    public String getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getInstaAcc() {
        return instaAcc;
    }

    public void setInstaAcc(String instaAcc) {
        this.instaAcc = instaAcc;
    }

    public String getLinkdAcc() {
        return LinkdAcc;
    }

    public void setLinkdAcc(String linkdAcc) {
        LinkdAcc = linkdAcc;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(User organisateur) {
        this.organisateur = organisateur;
    }
}
