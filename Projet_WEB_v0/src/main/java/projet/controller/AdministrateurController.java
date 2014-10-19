package projet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.model.Administrateur;
import projet.model.Caissier;
import projet.repository.AdministrateurRepository;


@Controller
public class AdministrateurController {
	boolean trouve = false;
	List<Administrateur> listeAdmins = new ArrayList<Administrateur>();
	
	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String loadAdminForm(Administrateur administrateur)
	{
		return "AccueilAdmin";	
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String siginAdminObject(Administrateur administrateur)
	{
		return "AccueilAdmin";	 
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String AdminSecurity(Administrateur administrateur,HttpSession session) {
		session.removeAttribute("admin");
		return "AccueilAdmin";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signinAdmin(@ModelAttribute Administrateur administrateur,HttpSession session)
	{
		int i = 0;
		
		listeAdmins=(List<Administrateur>) administrateurRepository.findAll();
		
		

		while(i<listeAdmins.size()&&trouve==false){
			if(administrateur.getEmail().equals(listeAdmins.get(i).getEmail())
				&& administrateur.getMdp().equals(listeAdmins.get(i).getMdp())) {
				trouve = true;
			}
			else
			{
				i=i+1;
			}
		}
		if(trouve==true)
		{
			session.getAttribute("admin");
			session.setAttribute("admin", administrateur.getEmail());
			return "AdminHello";
			
			
		}
		else
		{
			return "AccueilAdmin";
		}
			
	}
	
	
	
	@RequestMapping(value="/produits", method=RequestMethod.GET)
	public String loadProduits()
	{
		return "formProduits";	
	}
	
	@RequestMapping(value="/signout", method=RequestMethod.GET)
	public String DecoAdminGET(Administrateur administrateur,HttpSession session)
	{
		session.removeAttribute("admin");
		return "AccueilAdmin";	
	}
	
	
	
	

}
