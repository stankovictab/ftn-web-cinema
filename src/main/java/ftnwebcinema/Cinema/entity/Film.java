package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Film implements Serializable{
	
	// Film i Projekcija nisu isto - Projekcija ima Film zajedno sa cenom i datumom, i koristi se u salama i bioskopima
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;
	
	@Column
	private String naziv;
	
	@Column
	private String opis;
	
	@Column
	private String zanr;
	
	@Column
	private String trajanje; // Integer? Sekunde? Ili samo tekst?
	
	@Column
	private double srednjaOcena; // Za sad ce biti 0, sa kontrolerom i funkcijama ce se izracunati.
	
	@ManyToMany(mappedBy = "listaOdgledanihFilmova")
	private Set<Gledalac> listaGledalaca = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// ManyToMany jer lista moze da ima vise filmova, i film moze da bude u vise listi.
	// Vezuje se za Gledalac -> listaOdgledanihFilmova
	// Ime tabele je ODGLEDANI_FILMOVI
	
	@OneToMany(mappedBy = "film") 
	private Set<Projekcija> listaProjekcija = new HashSet<>();
	// Vise projekcija mogu da projektuju jedan film, ali ne moze jedan film da bude u vise projekcija
	// Mozda promenim u ManyToMany
	// Spojeno sa Projekcija -> Film film, tamo je kolona
	
	@OneToMany(mappedBy = "film") 
	private Set<Ocena> listaOcena = new HashSet<>();
	// Spojeno sa Film film iz Ocena
	// Tamo je kolona
}
