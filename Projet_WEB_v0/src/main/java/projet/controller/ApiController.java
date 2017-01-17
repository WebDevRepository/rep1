package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.model.Product;
import projet.repository.ProductRepository;



@RestController
@RequestMapping("/api")
public class ApiController {
    
	@Autowired
	private ProductRepository productRepository;
	
	
	@RequestMapping(value="/product/{id}", method = RequestMethod.GET)
	public Product getProduct(){
		
		
		return  productRepository.findOne(null);}
		
	@RequestMapping(value="/product/{id}/{nb}" , method = RequestMethod.GET)
	public Product putProduct(@PathVariable(value="id") Long id, @PathVariable(value ="nb") int nb){
		
		Product p = productRepository.findOne(id);
		
		if(p!=null) {
		//	p.getQte();
			}
		
		productRepository.save(p);
	
	return p ;

}
	
}