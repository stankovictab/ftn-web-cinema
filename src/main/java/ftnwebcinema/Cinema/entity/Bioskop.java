package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Bioskop implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBioskop;
	
	@Column
	private String naziv;
	
	// Pomocan stari naziv za izmenu bioskopa
	// Nije kolona (ali se opet pojavljuje)
	private String stariNaziv;
	
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
	
	// Pomocni String koji pomaze u pravljenju bioskopa
	// Nije kolona
	private String menadzerUsername;
	
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

	public Long getIdBioskop() {
		return idBioskop;
	}

	public void setIdBioskop(Long idBioskop) {
		this.idBioskop = idBioskop;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefonaCentrale() {
		return brojTelefonaCentrale;
	}

	public void setBrojTelefonaCentrale(String brojTelefonaCentrale) {
		this.brojTelefonaCentrale = brojTelefonaCentrale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMenadzerUsername() {
		return menadzerUsername;
	}

	public void setMenadzerUsername(String menadzerUsername) {
		this.menadzerUsername = menadzerUsername;
	}
	
	public String getStariNaziv() {
		return stariNaziv;
	}

	public void setStariNaziv(String stariNaziv) {
		this.stariNaziv = stariNaziv;
	}
	
	public Bioskop(){}

	public Bioskop(String naziv, String adresa, String brojTelefonaCentrale, String email, String menadzerUsername) {
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojTelefonaCentrale = brojTelefonaCentrale;
		this.email = email;
		this.menadzerUsername = menadzerUsername; // Mislim da treba i ovo
	}

}
