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
    private UtenteCrudRepository utenteCrudRepository;
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
}
