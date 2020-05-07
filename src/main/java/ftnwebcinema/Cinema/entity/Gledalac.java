package ftnwebcinema.Cinema.entity;

import javax.persistence.*;
import java.util.*;

@SuppressWarnings("serial")
@Entity
public class Gledalac extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGledalac; // Ne moze long
	
	// Ulogu iz SQL-a kao String (za sad)
	
	@ManyToMany 
	@JoinTable(name = "odgledaniFilmovi")
	private Set<Film> listaOdgledanihFilmova = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// ManyToMany jer lista moze da ima vise filmova, i film moze da bude u vise listi.
	// Vezuje se za Film -> listaGledalaca
	// Ime tabele je ODGLEDANI_FILMOVI
	
	@OneToMany(mappedBy = "rezervator")
	private Set<Projekcija> listaRezervisanihFilmova = new HashSet<>();
	// Rezervise projekcije a ne filmove
	// Gledalac rezervise vise projekcija
	// Vezano za Projekcija -> Gledalac rezervator u Projekcija
	// Tamo je kolona jer je ovde mappedBy
	// Mozda preimenujem u listaRezervisanihProjekcija
	// Mozda promenim u ManyToMany gde se vezuje za listuRezervatora
	
	@OneToMany(mappedBy = "gledalac")
	private Set<Ocena> listaOcena = new HashSet<>();
	// Gledalac moze da da vise ocena, ali ne mogu vise gledalaca da daju istu ocenu (ali ocenu kao komentar, ne int ocena)
	// Ide ka Ocena -> Gledalac gledalac, tu je kolona
	
	
	// Konstruktor?
}
