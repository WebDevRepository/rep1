package projet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String email;
	private String mdp;
	
	
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
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
	
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", email=" + email + ", mdp=" + mdp
				+ "]";
	}
	
	
}
