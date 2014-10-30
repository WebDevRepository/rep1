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



import projet.model.Client;
import projet.model.Product;
import projet.repository.ProductRepository;




@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	@RequestMapping(value = "/ficheProduit", method = RequestMethod.GET)
	public String createFormP(Model model) {
		model.addAttribute("product", new Product());
		return "ficheProduit";
	}
	
	
	
	@RequestMapping(value = "/ficheProduit", method = RequestMethod.POST)
    public String prodSubmit(@ModelAttribute Product product,HttpSession session, Model model){
		
		List<Product> panier = (List<Product>)session.getAttribute("panierProducts");
		
		if(panier == null)
			
			panier = new ArrayList<Product>();
		
		panier.add(product);
		
		session.setAttribute("panierProducts",panier);
		productRepository.save(product);
		return "redirect:/p";
		
	}
	
	
/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitFicheP(@ModelAttribute Product product, Model model) {
		
		productRepository.save(product);
		
		return "listProduits";
	}*/
	
	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String listeProducts(Model model,HttpSession session) {
		
		//model.addAttribute("products", productRepository.findAll());
		session.setAttribute("panierProducts",productRepository.findAll());
		return "listProduits";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "redirect:/p";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "ficheProduit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPostProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/p";
	}
	
}