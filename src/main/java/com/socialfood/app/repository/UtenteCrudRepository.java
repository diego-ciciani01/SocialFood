package com.socialfood.app.repository;

import com.socialfood.app.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteCrudRepository extends CrudRepository<Utente,Integer> {

    Utente findByUsername(String username);
}
