package ftnwebcinema.Cinema.entity;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass // Nije @Entity jer se nasledjuje, ne instancira se kao tabela
public class Korisnik implements Serializable { // Apstraktna?

	// Za sad enumeraciju ne koristim
	// protected enum Uloge{Gledalac, Menadzer, Administrator};
	
	// Korisnik nema @Id jer se ne instancira kao tabela
	// Sledece kolone se nasledjuju
	
	@Column
	protected String username;
	
	@Column
	protected String password;
	
	@Column
	protected String ime;
	
	@Column
	protected String prezime;
	
	@Column
	protected String telefon;
	
	@Column
	protected String email;
	
	@Column
	protected String dob;
	
	@Column
	protected String uloga;
	
	@Column
	protected Boolean aktivan;
	
	// Geteri za ID su u nasledjenim klasama
	
	// Sledeci geteri i seteri su za DTO
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	// Sledeci geteri i seteri su za obican objekat
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	
}
