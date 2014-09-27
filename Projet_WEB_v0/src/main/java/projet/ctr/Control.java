package projet.ctr;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.modele.Client;
import projet.repository.ClientRepository;

@Controller
public class Control {
	
	@Autowired
	public ClientRepository clientR;
	List<Client> listeClients = new ArrayList<Client>();
	 
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String clientForm(Model modele)
	{
		modele.addAttribute("client",new Client());
		return "formClient";	
	}
	
	@RequestMapping(value="/clientEnregister")
	public String clientEnreg(Model modele,@Valid Client  client,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) 
		{
			return "formClient";
		}
		else 
		{
		modele.addAttribute("client",clientR.save(client));
		listeClients=(List<Client>) clientR.findAll();
		modele.addAttribute("listeClients",listeClients);
		return "lesClients";	
		}
	}
	
	

}
