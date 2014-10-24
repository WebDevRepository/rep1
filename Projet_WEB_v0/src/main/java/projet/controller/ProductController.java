package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import projet.model.Product;
import projet.repository.ProductRepository;




@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	@RequestMapping(value = "/ficheProduit", method = RequestMethod.GET)
	public String createFormP(Model model) {
		model.addAttribute("product", new Product());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitFicheP(@ModelAttribute Product product, Model model) {
		
		productRepository.save(product);
		
		return "listProduits";
	}
	
	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String listeProducts(Model model) {
		
		model.addAttribute("products", productRepository.findAll());
		return "listProduits";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "listProduits";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "ficheProduit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPostProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/";
	}
	
}