package tech.chillo.avisutilisateurs.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.chillo.avisutilisateurs.Repository.UtilisateurRepository;
import tech.chillo.avisutilisateurs.entite.Role;
import tech.chillo.avisutilisateurs.entite.TypeDeRole;
import tech.chillo.avisutilisateurs.entite.Utilisateur;
import tech.chillo.avisutilisateurs.entite.Validation;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;



    public void inscription(Utilisateur utilisateur) {

        if(!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("votre mail est invalide");
        } else if (!utilisateur.getEmail().contains(".")) {
            throw new RuntimeException("Votre mail est invalide");
        }

        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());

        if(utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre email est deja utilisé");
        }
        String mdpCrypte = this.passwordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypte);

        Role roleutilisateur = new Role();
        roleutilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(roleutilisateur);

        utilisateur = this.utilisateurRepository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);


    }

    public void activation(Map<String, String> activation) {

        Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));

        if(Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }
        Utilisateur utilisateurActiver = this.utilisateurRepository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));

        utilisateurActiver.setActif(true);

        this.utilisateurRepository.save(utilisateurActiver);

    }
}


