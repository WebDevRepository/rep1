package projet.repository;

import org.springframework.data.repository.CrudRepository;

import projet.model.Facture;

public interface FactureRepository extends CrudRepository<Facture, Long> {

}
