package com.socialfood.app.repository;

import com.socialfood.app.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCrudRepository extends CrudRepository<Post,Integer> {

    List<Post> findAllByUtente(Integer utente);

}
