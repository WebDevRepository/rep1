package projet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caissier {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String refcli;
	private String nom;
	private String prenom;
	private int age;
	private String adresse;
	private String email;
	private String mdp;

	public String getRefcli() {
		return refcli;
	}
	public Caissier() {
		// TODO Auto-generated constructor stub
	}

	public void setRefcli(String refcli) {
		this.refcli = refcli;
	}

	public Caissier(Long id, String refcli, String nom, String prenom, int age,
			String adresse, String email, String mdp) {
		super();
		this.id = id;
		this.refcli = refcli;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
		this.email = email;
		this.mdp = mdp;
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
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	
	

}
