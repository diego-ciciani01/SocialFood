package com.socialfood.app.service;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Ruolo;
import com.socialfood.app.model.Utente;
import com.socialfood.app.repository.PostCrudRepository;
import com.socialfood.app.repository.RuoloCrudRepository;
import com.socialfood.app.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UtenteService {
    @Autowired
    private UtenteCrudRepository utenteCrudRepository;
    @Autowired
    private RuoloCrudRepository ruoloCrudRepository;

    public Utente findByUsername(String username) {
        return utenteCrudRepository.findByUsername(username);
    }

    public Utente creaUtente(Utente u) {
        return utenteCrudRepository.save(u);
    }

    public String doLogin(Utente u) throws Exception {
        try {
            Utente userExist = this.checkUsername(u);

            if(userExist != null){
                boolean loggato = this.checkCredenziali(userExist, u);
                if(loggato)
                    return "OK";
                else
                    return "passwordError"; //password errata
            }
            else
                return "usernameError"; //username non esiste
        }
        catch (Exception e) {
            throw new Exception();
        }
    }

    private Utente checkUsername(Utente u) {
        Utente utente = this.findByUsername(u.getUsername());
        if(utente != null)
            return utente;
        else
            return null;
    }

    private boolean checkCredenziali(Utente usernameOk, Utente u) {
        if(usernameOk.getPassword().equals(u.getPassword()))
            return true;
        else
            return false;
    }

    public Integer getId(Utente u){
        return this.getUtenteByUsername(u.getUsername()).getIdUtente();
    }

    public Utente getUtenteByUsername(String username){
        return utenteCrudRepository.findByUsername(username);
    }

    public void setRuolo(Utente user, Ruolo ruolo) {
        Ruolo esistente = ruoloCrudRepository.findByNome(ruolo.getNome());

        if(esistente == null) {
            ruoloCrudRepository.save(ruolo);
            user.setRuolo(ruolo);
        }
        else
            user.setRuolo(esistente);

        utenteCrudRepository.save(user);
    }
}
