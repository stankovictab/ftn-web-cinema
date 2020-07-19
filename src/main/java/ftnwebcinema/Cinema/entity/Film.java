package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Film implements Serializable{
	
	// Film i Projekcija nisu isto - Projekcija ima Film zajedno sa cenom i datumom, i koristi se u salama i bioskopima
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;
	
	// Sluze za pretragu
	private String keyword;
	private String pretraga;
	
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

	public Long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}

	public double getSrednjaOcena() {
		return srednjaOcena;
	}

	public void setSrednjaOcena(double srednjaOcena) {
		this.srednjaOcena = srednjaOcena;
	}

	public Film() {}
	
	public Film(String naziv, String opis, String zanr, String trajanje, double srednjaOcena) {
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.srednjaOcena = srednjaOcena;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPretraga() {
		return pretraga;
	}

	public void setPretraga(String pretraga) {
		this.pretraga = pretraga;
	}

	public Set<Projekcija> getListaProjekcija() {
		return listaProjekcija;
	}

	public void setListaProjekcija(Set<Projekcija> listaProjekcija) {
		this.listaProjekcija = listaProjekcija;
	}
	
	
	
}
