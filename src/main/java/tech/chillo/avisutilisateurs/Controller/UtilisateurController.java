package tech.chillo.avisutilisateurs.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.chillo.avisutilisateurs.Service.UtilisateurService;
import tech.chillo.avisutilisateurs.entite.Utilisateur;

import java.awt.*;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/inscription")
    public void inscrription(@RequestBody Utilisateur utilisateur){
        this.utilisateurService.inscription(utilisateur);
        log.info("inscription");
    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        this.utilisateurService.activation(activation);
    }

}