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
import projet.repository.FactureRepository;
import projet.repository.ProductRepository;


@Controller
public class CaissierController {
	
	@Autowired
	CaissierRepository caissierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FactureRepository factureRepository;
	
	
	@RequestMapping(value="/lCaissiers", method=RequestMethod.GET)
	public String loadCaissiers(Caissier caissier,HttpSession session)
	{
		session.setAttribute("panierCaissiers",caissierRepository.findAll());
		return "lesCaissiers";	
	}
	
	@RequestMapping(value="/formCaissiers", method=RequestMethod.GET)
	public String loadformCaissiers(Caissier caissier)
	{
		return "formCaissiers";	
	}
	
	@RequestMapping(value="/formCaissiers", method=RequestMethod.POST)
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
	
	
	
	@RequestMapping(value = "/deleteCaissier", method = RequestMethod.GET)
	public String deleteCaissier(@RequestParam("id") Long id, Model model) {
		
		caissierRepository.delete(id);
		
		return "redirect:/lCaissiers";
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
		return "redirect:/lCaissiers"; 
	}
	
	@RequestMapping(value = "/facture", method = RequestMethod.POST)
	public String facture(Model model,HttpSession session) {
		session.setAttribute("panierProduits",productRepository.findAll());
		model.addAttribute("facture",factureRepository.findAll());
		return "facture"; 
	}

}
