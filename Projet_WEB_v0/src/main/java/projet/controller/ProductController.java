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
import projet.repository.ProductRepository;




@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@RequestMapping(value="/lProduits", method=RequestMethod.GET)
	public String loadProducts(Product product,HttpSession session)
	{
		session.setAttribute("panierProduits",productRepository.findAll());
		return "listProduits";	
	}
	
	@RequestMapping(value = "/fProduit", method = RequestMethod.GET)
	public String createFormP(Model model) {
		model.addAttribute("product", new Product());
		return "ficheProduit";
	}
	
	
	
	@RequestMapping(value = "/fProduit", method = RequestMethod.POST)
    public String prodSubmit(@ModelAttribute Product product,HttpSession session, Model model){
		
		List<Product> panier = (List<Product>)session.getAttribute("panierProduits");
		
		if(panier == null)
			
			panier = new ArrayList<Product>();
		
		panier.add(product);
		
		session.setAttribute("panierProduits",panier);
		productRepository.save(product);
		return "redirect:/l";
		
	}
	
	
/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitFicheP(@ModelAttribute Product product, Model model) {
		
		productRepository.save(product);
		
		return "listProduits";
	}*/
	
	@RequestMapping(value = "/l", method = RequestMethod.GET)
	public String listeProducts(Model model,HttpSession session) {
		
		//model.addAttribute("products", productRepository.findAll());
		session.setAttribute("panierProduits",productRepository.findAll());
		return "listProduits";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "redirect:/l";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "ficheProduit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPostProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/l";
	}
	
}