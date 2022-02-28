package com.socialfood.app.model;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class PostDTO {
    Integer idPost;
    String testo;
    String titolo;
    Timestamp dataCreazione;
    Timestamp dataUltimoAggiornamento;

    public PostDTO() {}

    public PostDTO(Post dto) {
        this.idPost = dto.getIdPost();
        this.testo = dto.getTesto();
        this.titolo = dto.getTitolo();
        this.dataCreazione = dto.getDataCreazione();
        this.dataUltimoAggiornamento = dto.getDataUltimoAggiornamento();
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

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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
}
