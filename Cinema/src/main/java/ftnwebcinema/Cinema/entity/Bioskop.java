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
	private String brojTelefonaCentrale; // int?
	
	@Column
	private String email;
	
	@OneToOne
	private Menadzer menadzerBioskopa;
	
//  Predomislio sam se
//	@OneToMany(mappedBy = "menadzerovBioskop") 
//	// Sigurno je OneToMany iz Bioskop ka Menadzer. 
//	// Vise menadzera za jedan bioskop, minimalno jedan, uvek jedan bioskop za menadzera
//	// Ako moze vise menadzera onda je to HashSet (tamo gde je One, tu je lista)
//	// mappedBy je naziv atributa sa kojim je u vezi i stavlja se kod One.
//	private Set<Menadzer> menadzeriBioskopa = new HashSet<>();
//	// Ne moze u sql da se pise zato sto je Set
	
	@OneToMany(mappedBy = "bioskop")
	private Set<Sala> listaSala = new HashSet<>();
	
	@ManyToMany
	private Set<Projekcija> rasporedOdrzavanjaFilmova = new HashSet<>();
	// Mislio sam prvo da treba da se napravi posebna klasa ROF koja ima u sebi idBioskopa idFilma datum projekcije i cenu, ali vec imam Projekciju kao klasu, koja mi omogucava da to uradim odavde. Posto hocu novu tabelu u bazi ili ManyToMany ovde ili bez mappedBy.
	// Vezano sa listaBioskopovaUKojimaSePrikazuje u Projekcija
	// Ime tabele je BIOSKOP_RASPORED_ODRZAVANJA_FILMOVA
	
}
