package ftnwebcinema.Cinema.entity;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Projekcija implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjekcija;
	
	@Column
	private String datumProjekcije;
	
	// Koristi se u izmeni projekcije
	private String stariDatumProjekcije;
	
	@Column
	private double cenaProjekcije;
	
	// Sluzi za kreiranje projekcije
	private Long tempIDFilma;
	
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

	public Long getIdProjekcija() {
		return idProjekcija;
	}

	public void setIdProjekcija(Long idProjekcija) {
		this.idProjekcija = idProjekcija;
	}

	public String getDatumProjekcije() {
		return datumProjekcije;
	}

	public void setDatumProjekcije(String datumProjekcije) {
		this.datumProjekcije = datumProjekcije;
	}

	public double getCenaProjekcije() {
		return cenaProjekcije;
	}

	public void setCenaProjekcije(double cenaProjekcije) {
		this.cenaProjekcije = cenaProjekcije;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Gledalac getRezervator() {
		return rezervator;
	}

	public void setRezervator(Gledalac rezervator) {
		this.rezervator = rezervator;
	}

	public Set<Sala> getListaSala() {
		return listaSala;
	}

	public void setListaSala(Set<Sala> listaSala) {
		this.listaSala = listaSala;
	}

	public Set<Bioskop> getListaBioskopa() {
		return listaBioskopa;
	}

	public void setListaBioskopa(Set<Bioskop> listaBioskopa) {
		this.listaBioskopa = listaBioskopa;
	}

	public Long getTempIDFilma() {		
		return tempIDFilma;
	}

	public void setTempIDFilma(Long tempIDFilma) {
		this.tempIDFilma = tempIDFilma;
	}
	
	public Projekcija() {}

	public String getStariDatumProjekcije() {
		return stariDatumProjekcije;
	}

	public void setStariDatumProjekcije(String stariDatumProjekcije) {
		this.stariDatumProjekcije = stariDatumProjekcije;
	}
	
}
