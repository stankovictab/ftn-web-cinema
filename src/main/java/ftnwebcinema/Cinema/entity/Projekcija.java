package ftnwebcinema.Cinema.entity;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Projekcija implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjekcija;
	
	@Column
	private String datumProjekcije;
	
	@Column
	private double cenaProjekcije;
	
	@ManyToOne 
	private Film film;
	// Vise projekcija mogu da projektuju jedan film, ali ne moze jedan film da bude u vise projekcija
	// Mozda promenim u ManyToMany
	// Ovde je kolona
	
	@ManyToOne
	private Gledalac rezervator;
	// Projekcija nema listu gledalaca koji su rezervisali film
	// Mozda ovo promenim u listaRezervatora
	// Da je ovde Set bila bi ManyToMany veza, posebna tabela (osim ako ne uradim mapiranje)
	// Ovde je kolona
	
	// TODO Ove sledece dve znace da se Projekcija povezuje i sa Salom i sa Bioskopom?
	
	@ManyToMany(mappedBy = "terminskaListaProjekcija")
	private Set<Sala> listaSala = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// Projekcije se vezuju za sale, ne filmovi
	// Sala moze da ima vise projekcija i svaka projekcija moze da bude u vise razlicitih sala razlicitih bioskopa
	// Ime tabele je SALINE_PROJEKCIJE
	
	@ManyToMany(mappedBy = "rasporedProjekcija")
	private Set<Bioskop> listaBioskopa = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// Ovo je lista bioskopa u kojima se projekcija prikazuje
	// Vezano sa Bioskop -> rasporedProjekcija
	// Ime tabele je BIOSKOPOVE_PROJEKCIJE
	
}
