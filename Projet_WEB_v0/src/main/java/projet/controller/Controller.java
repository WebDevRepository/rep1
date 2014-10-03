package projet.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.modele.Client;
import projet.repository.ClientRepository;

@Controller
public class Controller {
	
	@Autowired
	public ClientRepository clientR;
	List<Client> listeClients = new ArrayList<Client>();
	 
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String clientForm(Model modele)
	{
		modele.addAttribute("client",new Client());
		return "formClient";	
	}
	
	@RequestMapping(value="/clientEnregister",method=RequestMethod.POST)
	public String clientEnreg(@RequestParam String nomC,@RequestParam String prenomC,@RequestParam String villeC,Model modele)
	{
		Client client = new Client(nomC,prenomC,villeC);
		clientR.save(client);
		listeClients=(List<Client>) clientR.findAll();
		modele.addAttribute("listeClients",listeClients);
		return "formClient";	
		
	}
	

	
	

}
