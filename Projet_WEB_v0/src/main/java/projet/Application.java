package projet;




import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import projet.model.Administrateur;
import projet.model.Caissier;
import projet.model.Client;
import projet.repository.AdministrateurRepository;
import projet.repository.CaissierRepository;
import projet.repository.ClientRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		
		 	ConfigurableApplicationContext context = SpringApplication.run(Application.class);
	        ClientRepository clientRepository = context.getBean(ClientRepository.class);
	        AdministrateurRepository admRepository=context.getBean(AdministrateurRepository.class);
	        CaissierRepository caissierRepository=context.getBean(CaissierRepository.class);
	        
	        System.out.println("Sauvegarde en cours ...");
	      
	        
	        
	        admRepository.save(new Administrateur("elf@gmail.com","elfelf"));
	        admRepository.save(new Administrateur("anas@gmail.com","anas"));
	        caissierRepository.save(new Caissier("el farouf","taoufik",20,"Rue ibnou habous","taoufik@hotmail.com","elf"));
	        
//	        List<Client> listClients = (List<Client>) clientRepository.findAll();
//	        System.out.println("Affichage de nos clients");
	        
//	       System.out.println("Nom         "+"Prenom          "+"Ville          ");
//	       for(Client c : listeClients) 
//	       {
//	    	   System.out.print(""+c.getNom()); System.out.print("         "+c.getPrenom());System.out.print("        "+c.getVille());
//	           System.out.print("\n");
//	       }
//	        
	        
	        
	        

	}

}
