package ftnwebcinema.Cinema.entity;

import javax.persistence.*;
import java.util.*;

@SuppressWarnings("serial")
@Entity
public class Gledalac extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ili AUTO?
	protected Long idGledalac; // Protected jer se nasledjuje
	
	// ID ne jer se klasa nasledjuje
	// Ulogu dodajemo iz sql-a
	
	// Jer lista moze da ima vise filmova, i film moze da bude u vise listi. mappedBy stavljam u Film klasi kao inverse end.	
	@ManyToMany // (mappedBy = "listaGledalaca") ovo ne treba, vezuje se za Film -> listaGledalaca
	private Set<Film> listaOdgledanihFilmova = new HashSet<>(); // Ili HashSet ili Lista
	
	// Rezervise projekcije a ne filmove
	@OneToMany(mappedBy = "rezervator")
	private Set<Projekcija> listaRezervisanihFilmova = new HashSet<>();
	
	// Gledalac ima vise ocena
	@OneToMany(mappedBy = "gledalac")
	private Set<Ocena> listaOcena = new HashSet<>();
	// Lista Ocena (nova klasa) ili lista filmova koji imaju atribut ocena?
	
	// Moze konstruktor koji postavlja ulogu na Gledalac? Iz enumeracije? Kako se onda konstruktor okida?
}
