package tech.chillo.avisutilisateurs.Repository;

import org.springframework.data.repository.CrudRepository;
import tech.chillo.avisutilisateurs.entite.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
Optional<Validation> findByCode(String code);

}