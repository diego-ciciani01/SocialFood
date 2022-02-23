package com.socialfood.app.service;

import com.socialfood.app.model.Utente;
import com.socialfood.app.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UService")
@Transactional
public class UtenteService {
    @Autowired
    private UtenteCrudRepository utenteCrudRepository;

    public Utente findByUsername(String username) {
        return utenteCrudRepository.findByUsername(username);
    }

    public Utente creaUtente(Utente u) {
        return utenteCrudRepository.save(u);
    }
}
