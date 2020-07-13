package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
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
	
	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	
}
