package com.socialfood.app.service;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Utente;
import com.socialfood.app.repository.PostCrudRepository;
import com.socialfood.app.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Service("PostService")
@Transactional
public class PostService {
    @Autowired
    private PostCrudRepository postCrudRepository;

    public void creaPost(Post p) {
        postCrudRepository.save(p);
    }
    
    public List<Post> selectAll(Integer idUtente){
        return postCrudRepository.findAllByUtente(new Utente(idUtente));
    }

    public List<Post> selectByIntervalloData(Integer idUtente, String dataInizio, String dataFine) {
        Utente u = new Utente(idUtente);
        Timestamp dI = Timestamp.valueOf(dataInizio);
        Timestamp dF = Timestamp.valueOf(dataFine);
        return postCrudRepository.findAllByUtenteAndDataUltimoAggiornamentoBetween(u, dI, dF);
    }

    public List<Post> containsTesto(Integer idUtente, String testo) {
        Utente u = new Utente(idUtente);
        String t = "%"+testo+"%";
        return postCrudRepository.findAllByUtenteAndTitoloLikeIgnoreCaseOrTestoLikeIgnoreCase(u,t,t);
    }

    public Post getPostById(Integer id) {
        return postCrudRepository.findByIdPost(id);
    }

    public void aggiornaTitolo(Integer idPost, String titolo) {
        Post p = postCrudRepository.findByIdPost(idPost);
        p.setTitolo(titolo);
        postCrudRepository.save(p);
    }

    public void aggiornaTesto(Integer idPost, String testo) {
        Post p = postCrudRepository.findByIdPost(idPost);
        p.setTesto(testo);
        postCrudRepository.save(p);
    }

    public void aggiungiLike(Post postLiked, Utente liker) {
        postLiked.getLikes().add(liker);
        postCrudRepository.save(postLiked);
    }

    public void rimuoviLike(Post postDisliked, Utente disliker) {
        postDisliked.getLikes().remove(disliker);
        postCrudRepository.save(postDisliked);
    }

    public void aggiungiDislike(Post postDisliked, Utente disliker) {
        postDisliked.getDislikes().add(disliker);
        postCrudRepository.save(postDisliked);
    }

    public void rimuoviDislike(Post postLiked, Utente liker) {
        postLiked.getDislikes().remove(liker);
        postCrudRepository.save(postLiked);
    }

    public void rimuoviPost(Post postRemoved) {
        postRemoved.setLikes(null);
        postRemoved.setDislikes(null);
        postCrudRepository.save(postRemoved);
        postCrudRepository.delete(postRemoved);
    }
}
