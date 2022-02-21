package com.socialfood.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ruolo")
public class Ruolo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRuolo;
    private String nome;
    private Boolean admin = null;

    @OneToMany(mappedBy = "ruolo")
    private List<Utente> ruoli = null;

    public Ruolo() {}

    public Ruolo(String nome, Boolean admin) {
        this.nome = nome;
        this.admin = admin;
    }

    public Integer getIdRuolo() {
        return idRuolo;
    }

    public void setIdRuolo(Integer idRuolo) {
        this.idRuolo = idRuolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<Utente> getRuoli() {
        return ruoli;
    }

    public void setRuoli(List<Utente> ruoli) {
        this.ruoli = ruoli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ruolo ruolo = (Ruolo) o;

        if (!idRuolo.equals(ruolo.idRuolo)) return false;
        return nome.equals(ruolo.nome);
    }

}
