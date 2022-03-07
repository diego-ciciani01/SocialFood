package com.socialfood.app.repository;

import com.socialfood.app.model.Ruolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloCrudRepository extends CrudRepository<Ruolo,Integer> {

    Ruolo findByNome(String nome);
}
