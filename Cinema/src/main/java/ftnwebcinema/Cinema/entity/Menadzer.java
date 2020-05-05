package ftnwebcinema.Cinema.entity;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Menadzer extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ili AUTO?
	protected Long idMenadzer; // Protected jer se nasledjuje
	
	// ID ne jer se klasa nasledjuje
	// Ulogu dodajemo iz sql-a
	
	@OneToOne(mappedBy = "menadzerBioskopa")
	private Bioskop menadzerovBioskop;
	// Ovo sam stavio da bi se kolona prebacila u bioskop, 
	// da bi onda sql upit za menadzera bio u insert into bioskop (jer tako izgleda radi od te 4 permutacije).
	// Bio ManyToOne ali reko nema smisla da vise menadzera mogu da budu za jedan Bioskop.
	// Ovo je jedina OneToOne veza.
	
}
