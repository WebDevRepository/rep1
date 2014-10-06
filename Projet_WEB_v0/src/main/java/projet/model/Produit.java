package projet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Produit {

	
	@Id
	@GeneratedValue
	private long id;
	private String nom_produit,description;
	
	
	
	public Produit() {}
	
	
	public Produit(long id, String nom_produit, String description) {
	
		this.id = id;
		this.nom_produit = nom_produit;
		this.description = description;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom_produit() {
		return nom_produit;
	}
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom_produit=" + nom_produit
				+ ", description=" + description + "]";
	}

	
	
	
	
}
