package projet.config;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import projet.model.Administrateur;
import projet.model.Caissier;
import projet.repository.AdministrateurRepository;
import projet.repository.CaissierRepository;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/listClients","/signin","/lClients","/lProduits","/lCaissiers","/fProduit","/fClient","/lProduits","/caissiers","/","/administrateur","/login","/edit","/editC","/editCaissier","/delete","/deleteC","/deleteCaissier").permitAll()
				.anyRequest().authenticated();

		http.formLogin().loginPage("/login").usernameParameter("email")
				.passwordParameter("pwd").permitAll().and().logout();
		//	.logoutUrl("/bye").logoutSuccessUrl("/").permitAll();
		http.csrf().disable();
		  
	}
	
	
	
	
	
	

	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {
		//@Autowired
		//private AdministrateurRepository administrateurRepository;
		@Autowired
		private CaissierRepository caissierRepository;
		//ArrayList<Administrateur> listeAdms = new ArrayList<Administrateur>();
		ArrayList<Caissier> listeCaissiers = new ArrayList<Caissier>();
		Authentication auth;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			//administrateurRepository.save(new Administrateur("elf@gmail.com","elfelf"));
	        //administrateurRepository.save(new Administrateur("anas@gmail.com","anas"));
	        
			caissierRepository.save(new Caissier("el farouf","taoufik",20,"Rue ibnou habous","taoufik@hotmail.com","elf"));
	        caissierRepository.save(new Caissier("el Mourabit","Anas",20,"Rue du passage","anas@gmail.com","anas"));
			
	        listeCaissiers = (ArrayList<Caissier>) caissierRepository.findAll();
			System.out.println("taille :"+listeCaissiers.size());
			Administrateur administrateur=new Administrateur();
			int i=0;
			boolean verif=false;
			while (i<listeCaissiers.size() && verif==false) {
				auth.inMemoryAuthentication().withUser(listeCaissiers.get(i).getEmail()).password(listeCaissiers.get(i).getMdp()).roles("USER");
				
				//administrateur.setEmail(listeAdms.get(i).getEmail());administrateur.setMdp(listeAdms.get(i).getMdp());
				verif = true;
				i++;

			}
			
			
			
				
		
			
			
			
		}
		
		

	}
}
