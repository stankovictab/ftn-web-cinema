package ftnwebcinema.Cinema.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Menadzer extends Korisnik {
	
	// ID ne jer se klasa nasledjuje
	// Ulogu dodajemo iz sql-a
	
	@ManyToOne
	// Vise menadzera mogu da budu za jedan Bioskop
	private Bioskop menadzerovBioskop;
	
}
