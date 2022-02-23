package com.socialfood.app.rest;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.Utente;
import com.socialfood.app.service.PostService;
import com.socialfood.app.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "/rest/post" }, produces = { "application/json" }, consumes = { "application/json" })
@CrossOrigin(origins = { "*" })
public class PostRest {
    @Autowired
    UtenteService utenteService;
    @Autowired
    PostService postService;

    @GetMapping(path = {"/creaPost"})
    public ResponseEntity<String> creaPost(@RequestBody Utente u, @RequestBody Post p) {
        try {
            String login = utenteService.doLogin(u);

            switch (login) {
                case "OK":
                    postService.creaPost(p);
                    return new ResponseEntity<>(HttpStatus.CREATED); //post creato

                case "usernameError":
                    return new ResponseEntity<>("usernameError",HttpStatus.BAD_REQUEST); //username non esiste

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
    public ResponseEntity<String> visualizzaPosts(@RequestBody Utente u) {
        try {
            String login = utenteService.doLogin(u);

            switch (login) {
                case "OK":
                    Integer id = utenteService.getId(u);
                    List<Post> posts = postService.selectAll(id);
                    return new ResponseEntity<>(HttpStatus.CREATED); //post creato

                case "usernameError":
                    return new ResponseEntity<>("usernameError",HttpStatus.BAD_REQUEST); //username non esiste

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
}
