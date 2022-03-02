package com.socialfood.app.repository;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostCrudRepository extends CrudRepository<Post,Integer> {

    Post findByIdPost(Integer idPost);

    List<Post> findAllByUtente(Utente utente);

    List<Post> findAllByUtenteAndDataUltimoAggiornamentoBetween(Utente utente, Timestamp dataInizio, Timestamp dataFine);

    List<Post> findAllByUtenteAndTitoloLikeIgnoreCaseOrTestoLikeIgnoreCase(Utente u, String titolo, String testo);
}
