package projet.repository;

import org.springframework.data.repository.CrudRepository;

import projet.model.Administrateur;

public interface AdministrateurRepository extends CrudRepository<Administrateur, Long> {
      Administrateur findByEmail(String email);
}
