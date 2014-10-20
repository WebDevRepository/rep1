package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.model.Administrateur;
import projet.model.Client;
import projet.model.Product;
import projet.repository.ClientRepository;
import projet.repository.ProductRepository;




@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	@RequestMapping(value = "/ficheClient", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("client", new Client());
		return "ficheClient";
	}
	
	@RequestMapping(value = "/ficheClient", method = RequestMethod.POST)
	public String ajoutClient(@ModelAttribute Client client, Model model) {
		
		clientRepository.save(client);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listeClients(Model model) {
		
		model.addAttribute("clients", clientRepository.findAll());
		return "listProduits";
	}
	
	@RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
	public String deleteClient(@RequestParam("id") Long id, Model model) {
		
		clientRepository.delete(id);
		
		return "listProduits";
	}
	
	@RequestMapping(value = "/editClient", method = RequestMethod.GET)
	public String editClient(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("client", clientRepository.findOne(id));
		return "ficheClient";
	}
	
	@RequestMapping(value = "/editClient", method = RequestMethod.POST)
	public String editPostProduct(@ModelAttribute Client client, Model model) {
		clientRepository.save(client);
		return "redirect:/";
	}
	
}