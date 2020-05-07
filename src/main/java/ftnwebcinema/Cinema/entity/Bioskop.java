package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Bioskop implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBioskop;
	
	@Column
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column
	private String brojTelefonaCentrale; // Long?
	
	@Column
	private String email;
	
	@OneToOne
	private Menadzer menadzerBioskopa;
	// Jedan menadzer za jedan bioskop, jedina OneToOne veza
	// Ovde je kolona zbog mappedBy
	
	@OneToMany(mappedBy = "bioskop")
	private Set<Sala> listaSala = new HashSet<>();
	// Svaki bioskop ima vise sala, svaka sala je samo za jedan bioskop
	// Kolona je u tabeli Sala
	
	@ManyToMany
	@JoinTable(name = "bioskopoveProjekcije")
	private Set<Projekcija> rasporedProjekcija = new HashSet<>();
	// Ide u @JoinTable da se ne bi pravile 2 posebne tabele
	// Ovo je AKA Raspored Odrzavanja Filmova iz zadatka
	// Vezano sa Projekcija -> listaBioskopa
	// Ime tabele je BIOSKOPOVE_PROJEKCIJE
	
}
