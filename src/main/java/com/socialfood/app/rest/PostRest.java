package com.socialfood.app.rest;

import com.socialfood.app.model.Post;
import com.socialfood.app.model.PostDTO;
import com.socialfood.app.model.Utente;
import com.socialfood.app.service.PostService;
import com.socialfood.app.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @PostMapping(path = {"/visualizzaPosts"})
    public ResponseEntity<List<PostDTO>> visualizzaPosts(@RequestBody Map<String,String> body) {
        try {
            String login = utenteService.doLogin(new Utente(body.get("username"), body.get("password")));

            if(login.equals("usernameError") || login.equals("passwordError"))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //username non esiste
            else if(login.equals("OK")){
                Utente daCercare = utenteService.getUtenteByUsername(body.get("daCercare"));
                if(daCercare != null) {
                    List<Post> posts;
                    if(body.get("dataInizio") != null && body.get("dataFine") != null)
                        posts = postService.selectByIntervalloData(daCercare.getIdUtente(), body.get("dataInizio"), body.get("dataFine"));
                    else if (body.get("testo") != null)
                        posts = postService.containsTesto(daCercare.getIdUtente(), body.get("testo"));
                    else
                        posts = postService.selectAll(daCercare.getIdUtente());

                    if(posts.isEmpty())
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    List<PostDTO> postsDTO = new ArrayList<>();
                    for (Post post : posts) {
                        postsDTO.add(new PostDTO(post));
                    }
                    return new ResponseEntity<>(postsDTO, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }

    @PostMapping(path = {"/aggiornaPost"})
    public ResponseEntity<String> aggiornaPost(@RequestBody Map<String,String> body) {
        try {
            String login = utenteService.doLogin(new Utente(body.get("username"), body.get("password")));

            if(login.equals("usernameError") || login.equals("passwordError"))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //username non esiste
            else if(login.equals("OK")){
                Integer idUtente = utenteService.getUtenteByUsername(body.get("username")).getIdUtente();
                Integer idPost = Integer.parseInt(body.get("idPost"));
                Post daModificare = postService.getPostById(idPost);
                if(daModificare != null) {
                    if(daModificare.getUtente().getIdUtente() != idUtente)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post non proprietario
                    if(body.get("titolo") != null)
                        postService.aggiornaTitolo(idPost, body.get("titolo"));
                    if(body.get("testo") != null)
                        postService.aggiornaTesto(idPost, body.get("testo"));

                    return new ResponseEntity<>(HttpStatus.OK); //post aggiornato
                }
                else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }

    @PostMapping(path = {"/like"})
    public ResponseEntity<String> like(@RequestBody Map<String,String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            Integer idPost = Integer.parseInt(body.get("idPost"));

            String login = utenteService.doLogin(new Utente(username, password));

            if(login.equals("usernameError") || login.equals("passwordError"))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //username o password errata
            else if(login.equals("OK")){
                Utente liker = utenteService.getUtenteByUsername(username);
                Integer idUtente = liker.getIdUtente();
                Post postLiked = postService.getPostById(idPost);
                if(postLiked != null) {
                    if(postLiked.getUtente().getIdUtente() == idUtente)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post proprietario, auto like non consentito

                    if(postLiked.getLikes().contains(liker))
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //like già inserito

                    if(postLiked.getDislikes().contains(liker))
                        postService.rimuoviDislike(postLiked,liker);

                    postService.aggiungiLike(postLiked,liker);
                    return new ResponseEntity<>(HttpStatus.OK); //like inserito
                }
                else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post non trovato
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }

    @PostMapping(path = {"/dislike"})
    public ResponseEntity<String> dislike(@RequestBody Map<String,String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            Integer idPost = Integer.parseInt(body.get("idPost"));

            String login = utenteService.doLogin(new Utente(username, password));

            if(login.equals("usernameError") || login.equals("passwordError"))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //username o password errata
            else if(login.equals("OK")){
                Utente disliker = utenteService.getUtenteByUsername(username);
                Integer idUtente = disliker.getIdUtente();
                Post postUnliked = postService.getPostById(idPost);
                if(postUnliked != null) {
                    if(postUnliked.getUtente().getIdUtente() == idUtente)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post proprietario, auto dislike non consentito

                    if(postUnliked.getDislikes().contains(disliker))
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //dislike già inserito

                    if(postUnliked.getLikes().contains(disliker))
                        postService.rimuoviLike(postUnliked,disliker);

                    postService.aggiungiDislike(postUnliked,disliker);
                    return new ResponseEntity<>(HttpStatus.OK); //like inserito
                }
                else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post non trovato
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }

    @DeleteMapping(path = {"/rimuoviPost"})
    public ResponseEntity<String> rimuoviPost(@RequestBody Map<String,String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            Integer idPost = Integer.parseInt(body.get("idPost"));

            String login = utenteService.doLogin(new Utente(username, password));

            if(login.equals("usernameError") || login.equals("passwordError"))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //username o password errata
            else if(login.equals("OK")){
                Utente remover = utenteService.getUtenteByUsername(username);
                Integer idUtente = remover.getIdUtente();
                Post postRemoved = postService.getPostById(idPost);
                if(postRemoved != null) {
                    if(postRemoved.getUtente().getIdUtente() == idUtente) {
                        postService.rimuoviPost(postRemoved);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //post rimosso
                    }
                    else if (remover.getRuolo().getAdmin()) {
                        postService.rimuoviPost(postRemoved);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //post rimosso
                    }
                    else
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //rimozione non autorizzata

                }
                else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //post non trovato
                }
            }
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //errore generico
        }
    }
}
