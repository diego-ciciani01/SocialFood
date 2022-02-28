package com.socialfood.app.rest;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Utente;
import com.socialfood.app.service.PostService;
import com.socialfood.app.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = { "/rest/post" }, produces = { "application/json" }, consumes = { "application/json" })
@CrossOrigin(origins = { "*" })
public class PostRest {
    @Autowired
    UtenteService utenteService;
    @Autowired
    PostService postService;

    @PostMapping(path = {"/creaPost"})
    public ResponseEntity<String> creaPost(@RequestBody Map<String,String> body) {
        try {
            String login = utenteService.doLogin(new Utente(body.get("username"), body.get("password")));

            switch (login) {
                case "OK":
                    Utente u = utenteService.getUtenteByUsername(body.get("username"));
                    Post daInserire = new Post( body.get("testo"),
                                                body.get("titolo"),
                                                Timestamp.valueOf(LocalDateTime.now()),
                                                Timestamp.valueOf(LocalDateTime.now()),
                                                u);
                    postService.creaPost(daInserire);
                    return new ResponseEntity<>(HttpStatus.CREATED); //post creato

                case "usernameError":
                    return new ResponseEntity<>("usernameError", HttpStatus.BAD_REQUEST); //username non esiste

                case "passwordError":
                    return new ResponseEntity<>("passwordError",HttpStatus.BAD_REQUEST); //password errata

                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }

    @GetMapping(path = {"/visualizzaPosts"})
    public ResponseEntity<Object> visualizzaPosts(@RequestBody Utente u) {
        try {
            String login = utenteService.doLogin(u);

            switch (login) {
                case "OK":
                    Utente current = utenteService.getUtenteByUsername(u.getUsername());
                    return new ResponseEntity<>(current.getPosts(),HttpStatus.CREATED);

                case "usernameError":
                    return new ResponseEntity<>("usernameError", HttpStatus.BAD_REQUEST); //username non esiste

                case "passwordError":
                    return new ResponseEntity<>("passwordError", HttpStatus.BAD_REQUEST); //password errata

                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }
}
