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






import projet.model.Bill;
import projet.model.Caissier;
import projet.model.Client;
import projet.model.Product;
import projet.repository.BillRepository;
import projet.repository.ProductRepository;




@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BillRepository billRepository;
	
	
	@RequestMapping(value="/lProduits", method=RequestMethod.GET)
	public String loadProducts(Product product,HttpSession session)
	{
		session.setAttribute("panierProduits",productRepository.findAll());
		return "listProduits";	
	}
	
	@RequestMapping(value="/lProduits1", method=RequestMethod.GET)
	public String loadProductsPos(Product product,HttpSession session)
	{
		session.setAttribute("panierProduits1",productRepository.findAll());
		return "pointVente";	
	}
	
	@RequestMapping(value = "/fProduit", method = RequestMethod.GET)
	public String createFormP(Model model) {
		model.addAttribute("product", new Product(0, "","", null, null, null, null));
		return "ficheProduit";
	}
	
	
	
	@RequestMapping(value = "/fpos", method = RequestMethod.POST)
    public String prodPOS(@ModelAttribute Product product,HttpSession session, Model model){
		
		List<Product> panier = (List<Product>)session.getAttribute("panierProduits1");
		
		if(panier == null)
			
			panier = new ArrayList<Product>();
		
		panier.add(product);
		
		session.setAttribute("panierProduits1",panier);
		productRepository.save(product);
		
		
		return "redirect:/lProduits1";
		
	}
	
	
	@RequestMapping(value = "/fProduit", method = RequestMethod.POST)
    public String prodSubmit(@ModelAttribute Product product,HttpSession session, Model model){
		
		List<Product> panier = (List<Product>)session.getAttribute("panierProduits");
		
		if(panier == null)
			
			panier = new ArrayList<Product>();
		
		panier.add(product);
		
		session.setAttribute("panierProduits",panier);
		productRepository.save(product);
		return "redirect:/lProduits";
		
	}
	
/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitFicheP(@ModelAttribute Product product, Model model) {
		
		productRepository.save(product);
		
		return "listProduits";
	}*/
	
	
	@RequestMapping(value = "/ajout", method = RequestMethod.GET)
	public String ajoutProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/lProduits";
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "redirect:/lProduits";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "ficheProduit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPostProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/lProduits";
	}
	
	@RequestMapping(value = "/pointVente", method = RequestMethod.POST)
	public String choisirProduit(@ModelAttribute Bill bill, Model model) {
		billRepository.save(bill);
		return "redirect:/pointVente";
	}
	
}