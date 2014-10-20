package projet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	
        registry.addViewController("/ficheProduit").setViewName("ficheProduit");
        registry.addViewController("/listPrdouits").setViewName("listProduits");
        registry.addViewController("/ficheClient").setViewName("ficheClient");
        registry.addViewController("/listClients").setViewName("listClients");
 //      registry.addViewController("/").setViewName("home");
//        registry.addViewController("/hello").setViewName("hello");
    	
        registry.addViewController("/login").setViewName("login");
    }

}