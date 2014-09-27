package projet.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import projet.modele.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	
	  List<Client> findByPrenom(String prenom);
	

}
