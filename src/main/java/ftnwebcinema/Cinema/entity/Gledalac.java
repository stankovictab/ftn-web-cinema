package ftnwebcinema.Cinema.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Gledalac extends Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGledalac; // Ne moze long
	
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

	////////////////////////////////////////////////////
	
	public Long getIdGledalac() {
		return idGledalac;
	}
	
	// Seter za id ne, to radi baza
	
	// Resilo veliki problem ali ne znam kako, preporuceno mi je da stavim u nekoj konzoli ne znam gde
	public Gledalac() {} 
	
	// DTO konstruktor
	public Gledalac(String ime, String prezime, String uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
    }
	
	// Treba konstruktor za sve sto se salje iz AJAX-a preko JSON-a (metoda za kreiranje gledaoca u kontroleru, treca, izmenjena)
	public Gledalac(String username, String password, String ime, String prezime, String telefon, String email, String dob, String uloga, Boolean aktivan) {
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.dob = dob;
		this.uloga = uloga;
		this.aktivan = aktivan;
	}
	
}
