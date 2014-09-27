package projet.ctr;


import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.modele.Client;
import projet.repository.ClientRepository;

@Controller
public class Control {
	
	ClientRepository clientR;
	 
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String clientForm(Model modele)
	{
		modele.addAttribute("client",new Client());
		return "formClient";	
	}
	
	@RequestMapping(value="/clientEnregister", method=RequestMethod.GET)
	public String clientEnreg(Model modele)
	{
		modele.addAttribute("client",clientR.save(new Client()));
		return "formClient";	
	}
	
	

}
