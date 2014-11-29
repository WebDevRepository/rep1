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
import org.springframework.web.bind.annotation.ResponseBody;

import projet.model.Bill;
import projet.model.Caissier;
import projet.model.Client;
import projet.model.Product;
import projet.repository.BillRepository;
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
	@Autowired
	private BillRepository billRepository;
	
	
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
	
	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public String facture(Model model,HttpSession session,@RequestParam("quantite") String qte) {
		int cpt =0;
		List<Product> listproduit = (List<Product>) productRepository.findAll();
		String[] tableau = qte.split("-");
		System.out.println(tableau.length);
		
		List<Bill> listfacture = (List<Bill>) billRepository.findAll();
		for (int j=0;j<listfacture.size();j++)
		{
			
				listfacture.get(j).setQte(Integer.parseInt(tableau[cpt]));
				cpt++;
			
		}
		for(int i=0;i<listproduit.size();i++) {
			for(int j=0;j<listfacture.size();j++) {
				if(listproduit.get(i).getName().equals(listfacture.get(j).getProduit()))
					listfacture.get(j).setPrix(listproduit.get(i).getPrice());
			}
		}
		model.addAttribute("OurProducts",listfacture);
			
		return "facture"; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/toCaisse", method = RequestMethod.POST)
	public String toCaisse(@RequestParam("name") String name) {
		Product p = productRepository.findByName(name);
		System.out.println(p.toString());
		return p.getPrice().toString(); 
	}

}
