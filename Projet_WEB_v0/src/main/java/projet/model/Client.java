package projet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String refc;
	private String nom;
	private String prenom;
	private int age;
	private String entreprise;
	private String adresse;
	private String ville;
	private String email;
	private String groupe;
	
	public Client(){}
	
	
	public Client(long id, String refc, String nom, String prenom, int age,
			String entreprise, String adresse, String ville, String email,
			String groupe) {
		super();
		this.id = id;
		this.refc = refc;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.entreprise = entreprise;
		this.adresse = adresse;
		this.ville = ville;
		this.email = email;
		this.groupe = groupe;
	}


	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}


	public String getRefc() {
		return refc;
	}


	public void setRefc(String refc) {
		this.refc = refc;
	}


	public String getGroupe() {
		return groupe;
	}


	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	
	
	
	
	

}

