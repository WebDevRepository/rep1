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




import projet.model.Caissier;
import projet.model.Client;
import projet.model.Product;
import projet.repository.ClientRepository;




@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@RequestMapping(value="/lClients", method=RequestMethod.GET)
	public String loadClients(Client client,HttpSession session)
	{
		session.setAttribute("panierClients",clientRepository.findAll());
		return "listClients";	
	}
	
	@RequestMapping(value = "/fClient", method = RequestMethod.GET)
	public String createClient(Model model) {
		model.addAttribute("client", new Client());
		return "ficheClient";
	}
	
	@RequestMapping(value = "/fClient", method = RequestMethod.POST)
    public String ClientSubmit(@ModelAttribute Client client,HttpSession session, Model model){
		
		List<Client> panier = (List<Client>)session.getAttribute("panierClients");
		
		if(panier == null)
			
			panier = new ArrayList<Client>();
		
		panier.add(client);
		
		session.setAttribute("panierClients",panier);
		clientRepository.save(client);
		return "redirect:/p";
		
	}
	
	/*@RequestMapping(value = "/ficheClient", method = RequestMethod.POST)
	public String ajoutClient(@ModelAttribute Client client, Model model) {
		
		clientRepository.save(client);
		
		return "redirect:/";
	}*/
	
	
	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String listeClients(Client client,HttpSession session) {
		
		session.setAttribute("panierClients", clientRepository.findAll());
		return "listClients";
	}
	
	@RequestMapping(value = "/deleteC", method = RequestMethod.GET)
	public String deleteClient(@RequestParam("id") Long id, Model model) {
		
		clientRepository.delete(id);
		
		return "redirect:/lClients";
	}
	
	@RequestMapping(value = "/editC", method = RequestMethod.GET)
	public String editClient(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("client", clientRepository.findOne(id));
		return "ficheClient";
	}
	
	@RequestMapping(value = "/editC", method = RequestMethod.POST)
	public String editPostClient(@ModelAttribute Client client, Model model) {
		clientRepository.save(client);
		return "redirect:/lClients";
	}
	
}