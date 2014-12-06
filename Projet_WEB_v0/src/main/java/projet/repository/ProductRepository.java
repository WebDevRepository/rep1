package projet.repository;

import org.springframework.data.repository.CrudRepository;

import projet.model.Product;


public interface ProductRepository extends CrudRepository<Product, Long>{
	public Product findByName(String name);
	
// 
}
