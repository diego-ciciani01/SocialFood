package com.socialfood.app.service;

import com.socialfood.app.model.Utente;
import com.socialfood.app.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = { "/rest/utente" }, produces = { "application/json" })
@CrossOrigin(origins = { "*" })
public class UtenteRest {
    @Autowired
    UtenteCrudRepository utenteCrudRepository;

    @PostMapping(path = {"/registrazione"}, consumes = {"application/json"})
    public ResponseEntity<Utente> registrazione(@RequestBody Utente nuovoUtente) {
        Utente utente = utenteCrudRepository.findByUsername(nuovoUtente.getUsername());

        if(utente == null) {
            utente = utenteCrudRepository.save(nuovoUtente);
            return new ResponseEntity<>(utente, HttpStatus.CREATED);
        }
        else{
            try {
                return new ResponseEntity<>(utente, HttpStatus.BAD_REQUEST);
            }
            catch (Exception e){
                return new ResponseEntity<>(utente, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
