package projet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import projet.model.Client;
import projet.repository.ClientRepository;




@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	
	
	@RequestMapping(value = "/ficheClient", method = RequestMethod.GET)
	public String createClient(Model model) {
		model.addAttribute("client", new Client());
		return "ficheClient";
	}
	
	
	@RequestMapping(value = "/ficheClient", method = RequestMethod.POST)
    public String ClientSubmit(@ModelAttribute Client client,HttpSession session, Model model){
		
		List<Client> panier = (List<Client>)session.getAttribute("panierClients");
		
		if(panier == null)
			
			panier = new ArrayList<Client>();
		
		panier.add(client);
		
		session.setAttribute("panierClients",panier);
		clientRepository.save(client);
		return "redirect:/i";
		
	}
	
	/*@RequestMapping(value = "/ficheClient", method = RequestMethod.POST)
	public String ajoutClient(@ModelAttribute Client client, Model model) {
		
		clientRepository.save(client);
		
		return "redirect:/";
	}*/
	
	@RequestMapping(value = "/i", method = RequestMethod.GET)
	public String listeClients(Model model) {
		
		model.addAttribute("clients", clientRepository.findAll());
		return "listClients";
	}
	
	@RequestMapping(value = "/deleteC", method = RequestMethod.GET)
	public String deleteClient(@RequestParam("id") Long id, Model model) {
		
		clientRepository.delete(id);
		
		return "listClients";
	}
	
	@RequestMapping(value = "/editC", method = RequestMethod.GET)
	public String editClient(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("client", clientRepository.findOne(id));
		return "ficheClient";
	}
	
	@RequestMapping(value = "/editC", method = RequestMethod.POST)
	public String editPostClient(@ModelAttribute Client client, Model model) {
		clientRepository.save(client);
		return "redirect:/";
	}
	
}