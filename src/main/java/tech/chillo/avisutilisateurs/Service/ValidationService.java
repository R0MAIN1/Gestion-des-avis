package tech.chillo.avisutilisateurs.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.chillo.avisutilisateurs.Repository.ValidationRepository;
import tech.chillo.avisutilisateurs.entite.Utilisateur;
import tech.chillo.avisutilisateurs.entite.Validation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;



@AllArgsConstructor
@Service
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

        public void enregistrer(Utilisateur utilisateur){

            Validation validation = new Validation();
            validation.setUtilisateur(utilisateur);

            Instant creation = Instant.now();
            validation.setCreation(creation);
            Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
            validation.setExpiration(expiration);
            Random random = new Random();
            int randomInteger = random.nextInt(999999);
            String code = String.format("%06d", randomInteger);
            validation.setCode(code);

            this.validationRepository.save(validation);
            this.notificationService.envoyer(validation);

        }

    public Validation lireEnFonctionDuCode(String code) {
        return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("votre code est invalide"));
    }



}
