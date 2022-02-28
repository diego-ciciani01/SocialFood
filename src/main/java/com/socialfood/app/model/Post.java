package com.socialfood.app.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;
    private String titolo;
    private String testo;
    private java.sql.Timestamp dataCreazione;
    private java.sql.Timestamp dataUltimoAggiornamento;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente = null;

    @ManyToMany
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "idUtente"))
    private List<Utente> likes = null;

    @ManyToMany
    @JoinTable(name = "dislikes",
            joinColumns = @JoinColumn(name = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "idUtente"))
    private List<Utente> dislikes = null;

    public Post() {}

    public Post(String titolo, String testo) {
        this.titolo = titolo;
        this.testo = testo;
    }

    public Post(String testo, String titolo, Timestamp dataCreazione, Timestamp dataUltimoAggiornamento, Utente u) {
        this.testo = testo;
        this.titolo = titolo;
        this.dataCreazione = dataCreazione;
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
        this.utente = u;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Timestamp getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Timestamp dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Timestamp getDataUltimoAggiornamento() {
        return dataUltimoAggiornamento;
    }

    public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente u) {
        this.utente = u;
    }

    public List<Utente> getLikes() {
        return likes;
    }

    public void setLikes(List<Utente> likes) {
        this.likes = likes;
    }

    public List<Utente> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Utente> dislikes) {
        this.dislikes = dislikes;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!idPost.equals(post.idPost)) return false;
        return dataCreazione.equals(post.dataCreazione);
    }


}
