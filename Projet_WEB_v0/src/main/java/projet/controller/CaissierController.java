package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.model.Caissier;
import projet.repository.CaissierRepository;


@Controller
public class CaissierController {
	
	@Autowired
	CaissierRepository caissierRepository;
	
	
	@RequestMapping(value="/caissiers", method=RequestMethod.GET)
	public String loadCaissiers(Caissier caissier)
	{
		return "formCaissiers";	
	}
	
	@RequestMapping(value="/caissiers", method=RequestMethod.POST)
	public String saveCaissier(@ModelAttribute Caissier caissier)
	{
		caissierRepository.save(caissier);
		return "redirect:/";	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listCaissiers(Model model) {
		
		model.addAttribute("listeCaissiers", caissierRepository.findAll());
		return "lesCaissiers";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCaissier(@RequestParam("id") Long id, Model model) {
		
		caissierRepository.delete(id);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("caissier", caissierRepository.findOne(id));
		return "formCaissiers";
	}
	
	@RequestMapping(value ="/GoToAdminHello", method = RequestMethod.GET)
	public String adminAccueil(Model model) {
		return "AdminHello";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Caissier caissier, Model model) {
		caissierRepository.save(caissier);
		return "redirect:/"; 
	}

}
