package tech.chillo.avisutilisateurs.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.chillo.avisutilisateurs.entite.Avis;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Integer> {

}
