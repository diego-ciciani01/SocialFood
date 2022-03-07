package com.socialfood.app.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Transactional
@Table(name = "Utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtente;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "utente")
    private List<Post> posts = null;

    @ManyToMany(mappedBy = "likes")
    private List<Post> likedPost = null;

    @ManyToMany(mappedBy = "dislikes")
    private List<Post> dislikedPost = null;

    @ManyToOne
    @JoinColumn(name = "ruoli")
    private Ruolo ruolo = null;

    public Utente() {}

    public Utente(Integer id) {
        this.idUtente = id;
    }

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utente(String nome, String cognome, String username, String email, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(List<Post> likedPost) {
        this.likedPost = likedPost;
    }

    public List<Post> getDislikedPost() {
        return dislikedPost;
    }

    public void setDislikedPost(List<Post> dislikedPost) {
        this.dislikedPost = dislikedPost;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        if (!idUtente.equals(utente.idUtente)) return false;
        return username.equals(utente.username);
    }

    @Override
    public int hashCode() {
        int result = idUtente.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
