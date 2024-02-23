package tech.chillo.avisutilisateurs.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.chillo.avisutilisateurs.Service.AvisService;
import tech.chillo.avisutilisateurs.entite.Avis;

@AllArgsConstructor
@RestController
@RequestMapping(path = "avis")
public class AvisController {

    private final AvisService avisService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Avis avis){
        this.avisService.creer(avis);
    }

}