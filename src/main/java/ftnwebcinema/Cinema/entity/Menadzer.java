package ftnwebcinema.Cinema.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Menadzer extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenadzer; // Ne moze long
	
	// Ulogu iz SQL-a kao String (za sad)
	
	@OneToOne(mappedBy = "menadzerBioskopa")
	private Bioskop menadzerovBioskop;
	// Jedan menadzer za jedan bioskop, jedina OneToOne veza (moze da bude i Set, menadzeriBioskopa, pa da je odavde ManyToOne)
	// Kolona je u tabeli Bioskop zbog mappedBy
	// Menadzer se onda povezuje preko Bioskop u SQL komandi 
	// (ovde je bila greska Referential Integrity Constraint Violation, obrnuti mappedBy i obrnuta komanda u SQL popravili)
	
	public Long getIdMenadzer() {
		return idMenadzer;
	}
	
	public Bioskop getMenadzerovBioskop() {
		return this.menadzerovBioskop;
	}

}
