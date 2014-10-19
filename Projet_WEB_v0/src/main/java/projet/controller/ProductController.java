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
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Auth(Model model) {
	//model.addAttribute("product", new Product());
		return "login";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("product", new Product());
		return "create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Product product, Model model) {
		
		productRepository.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listProducts(Model model) {
		
		model.addAttribute("products", productRepository.findAll());
		return "list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "form";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "create";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/";
	}
	
}