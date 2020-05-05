package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Film implements Serializable{
	
	// Projekcija ima u sebi film, a ne pise se onetoone nego se samo stavi private Film film ? jer je to onetoone?
	
	// Film ima listu ocena one to many sa ocenom
	// ocena 
	// gledalacim alistu ocena one to many
	
	
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
	
	@OneToMany(mappedBy = "film") // Spojeno sa Film film u Projekcija. MappedBy da se ne pravi nova tabela.
	private Set<Projekcija> listaProjekcija = new HashSet<>();
	
	@ManyToMany
	private Set<Gledalac> listaGledalaca = new HashSet<>();

//	Otislo u Projekcija, jer je u Sala terminskaListaProjekcija 
//	@ManyToMany(mappedBy = "terminskaListaProjekcija")
//	private Set<Sala> listaSala = new HashSet<>();
	
	@OneToMany(mappedBy = "film") // Spojeno sa Film film iz Ocena
	private Set<Ocena> listaOcena = new HashSet<>();
}
