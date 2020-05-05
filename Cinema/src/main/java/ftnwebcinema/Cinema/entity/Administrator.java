package ftnwebcinema.Cinema.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Administrator extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ili AUTO?
	protected Long idAdministrator; // Protected jer se nasledjuje
	
	// ID ne jer se klasa nasledjuje
	// Ulogu dodajemo iz sql-a
	
}
