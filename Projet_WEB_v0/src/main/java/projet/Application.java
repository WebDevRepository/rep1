package projet;



//
//
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import projet.model.Client;
import projet.repository.ClientRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		
		 	ConfigurableApplicationContext context = SpringApplication.run(Application.class);
	        ClientRepository repository = context.getBean(ClientRepository.class);
	        
	        System.out.println("Sauvegarde en cours ...");
	        repository.save(new Client("imad","boussouf","constantine"));
	        repository.save(new Client("tedj", "ammar","tripoli"));
	        repository.save(new Client("elmourabit", "anas","rabat"));
	        repository.save(new Client("ted", "bob","eljadida"));

	        
	        List<Client> listeClients = (List<Client>) repository.findAll();
	        System.out.println("Affichage de nos clients");
	        
	       System.out.println("Nom         "+"Prenom          "+"Ville          ");
	       for(Client c : listeClients) 
	       {
	    	   System.out.print(""+c.getNom()); System.out.print("         "+c.getPrenom());System.out.print("        "+c.getVille());
	           System.out.print("\n");
	       }
	        
	        
	        
	        

	}

}
