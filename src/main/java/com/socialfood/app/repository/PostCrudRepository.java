package com.socialfood.app.repository;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostCrudRepository extends CrudRepository<Post,Integer> {

    public List<Post> findAllByUtente(Utente utente);
}
