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
import projet.repository.CaissierRepository;


@Controller
public class CaissierController {
	
	@Autowired
	CaissierRepository caissierRepository;
	
	
	@RequestMapping(value="/caissiers", method=RequestMethod.GET)
	public String loadCaissiers(Caissier caissier)
	{
		return "lesCaissiers";	
	}
	
	@RequestMapping(value="/formCaissiers", method=RequestMethod.GET)
	public String loadformCaissiers(Caissier caissier)
	{
		return "formCaissiers";	
	}
	
	@RequestMapping(value="/caissiers", method=RequestMethod.POST)
	public String saveCaissier(@ModelAttribute Caissier caissier,HttpSession session)
	{
		List<Caissier> panier = (List<Caissier>)session.getAttribute("panierCaissiers");
		
		if(panier == null)
			
			panier = new ArrayList<Caissier>();
		
		panier.add(caissier);
		
		session.setAttribute("panierCaissiers",panier);
		caissierRepository.save(caissier);
		return "redirect:/c";	
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.GET)
	public String listCaissiers(Model model) {
		
		model.addAttribute("listeCaissiers", caissierRepository.findAll());
		return "lesCaissiers";
	}
	
	@RequestMapping(value = "/deleteCaissier", method = RequestMethod.GET)
	public String deleteCaissier(@RequestParam("id") Long id, Model model) {
		
		caissierRepository.delete(id);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/editCaissier", method = RequestMethod.GET)
	public String editFormCaissier(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("caissier", caissierRepository.findOne(id));
		return "formCaissiers";
	}
	
	@RequestMapping(value ="/GoToAdminHello", method = RequestMethod.GET)
	public String adminAccueil(Model model) {
		return "AdminHello";
	}
	
	@RequestMapping(value = "/editCaissier", method = RequestMethod.POST)
	public String editPostCaissier(@ModelAttribute Caissier caissier, Model model) {
		caissierRepository.save(caissier);
		return "redirect:/"; 
	}

}
