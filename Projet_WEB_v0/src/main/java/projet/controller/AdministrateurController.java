package projet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.model.Administrateur;
import projet.model.Bill;
import projet.model.Caissier;
import projet.model.Client;
import projet.model.Facture;
import projet.model.Product;
import projet.repository.AdministrateurRepository;
import projet.repository.BillRepository;
import projet.repository.FactureRepository;
import projet.repository.ProductRepository;


@Controller
public class AdministrateurController {
	boolean trouve = false;
	List<Administrateur> listeAdmins = new ArrayList<Administrateur>();
	
	@Autowired
	private FactureRepository factureRepository;
	@Autowired
	private AdministrateurRepository administrateurRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BillRepository billRepository;
	
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
	
	
	/*
	@RequestMapping(value="/listProduits", method=RequestMethod.GET)
	public String loadProduits(Product product)
	{
		return "listProduits";	
	}
	
	@RequestMapping(value="/listClients", method=RequestMethod.GET)
	public String loadClients(Client client)
	{
		return "listClients";	
	}
	*/
	
	@RequestMapping(value="/signoutAdmin", method=RequestMethod.GET)
	public String DecoAdminGET(Administrateur administrateur,HttpSession session)
	{
		session.getAttribute("admin");
		session.invalidate();
		return "AccueilAdmin";	
	}
	
	@RequestMapping(value="/signoutCaissier", method=RequestMethod.GET)
	public String DecoCaissierGET(Administrateur administrateur,HttpSession session)
	{
		session.getAttribute("admin");
		session.invalidate();
		return "loginForm";	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Auth(@ModelAttribute Administrateur administrateur,Model model,HttpSession session) {
		//session.getAttribute("admin");
		//session.setAttribute("admin", administrateur.getEmail());
		return "loginForm";
	}
	
	@RequestMapping(value = "/pointVente", method = RequestMethod.GET)
	public String pointDeVente(@ModelAttribute Bill bill,@ModelAttribute Administrateur administrateur,HttpSession session,Model model) {
		List<Product> listProduit = (List<Product>) productRepository.findAll();
		List<Bill> listfacture = (List<Bill>) billRepository.findAll();
		model.addAttribute("produitsss",productRepository.findAll());
		if(listfacture!=null)
		{
			model.addAttribute("listeFacture",listfacture);
		}
		for(int i=0;i<listProduit.size();i++) {
			for(int j=0;j<listfacture.size();j++) {
				if(listProduit.get(i).getName().equals(listfacture.get(j).getProduit()))
					listfacture.get(j).setPrix(listProduit.get(i).getPrice());
			}
		}
		
		session.setAttribute("panierProduits",productRepository.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		session.getAttribute("caissier");
		session.setAttribute("caissier",auth.getName());
		return "CaissierHello";
	}
	
	@RequestMapping(value = "/administrateur", method = RequestMethod.GET)
	public String admin(Administrateur administrateur) {
		return "AccueilAdmin";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	
	
	

}
