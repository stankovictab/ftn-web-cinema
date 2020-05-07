package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Sala implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSala;
	
	@Column
	private int kapacitet;
	
	@Column
	private String oznakaSale;
	
	@ManyToOne
	private Bioskop bioskop;
	// Svaki bioskop ima vise sala, svaka sala je samo za jedan bioskop
	// Kolona je u ovoj tabeli
	
	@ManyToMany
	@JoinTable(name = "salineProjekcije")
	private Set<Projekcija> terminskaListaProjekcija = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// Za salu se vezuju projekcije, ne filmovi
	// Sala moze da ima vise projekcija i svaka projekcija moze da bude u vise razlicitih sala razlicitih bioskopa
	// Ime tabele je SALINE_PROJEKCIJE
	
}
