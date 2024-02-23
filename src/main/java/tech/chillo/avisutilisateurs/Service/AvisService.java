package tech.chillo.avisutilisateurs.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.chillo.avisutilisateurs.Repository.AvisRepository;
import tech.chillo.avisutilisateurs.entite.Avis;

@AllArgsConstructor
@Service
public class AvisService {

    private final AvisRepository avisRepository;
    public void creer(Avis avis) {
        this.avisRepository.save(avis);
    }
}
