package ftnwebcinema.Cinema.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Administrator extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdministrator; // Ne moze long
	
	// Ulogu iz SQL-a kao String (za sad)
	
	public Long getIdAdministrator() {
		return idAdministrator;
	}
	
}
