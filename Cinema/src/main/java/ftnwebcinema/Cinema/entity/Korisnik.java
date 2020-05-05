package ftnwebcinema.Cinema.entity;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass // @Entity NECE
public class Korisnik implements Serializable { // Apstraktna?

	// protected enum Uloge{Gledalac, Menadzer, Administrator}; // 0, 1 i 2
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // Ili AUTO?
//	protected Long idKorisnik; // Protected jer se nasledjuje
	
	@Column
	protected String username;
	
	@Column
	protected String password;
	
	@Column
	protected String ime;
	
	@Column
	protected String prezime;
	
	@Column
	protected String telefon; // int?
	
	@Column
	protected String email;
	
	@Column
	protected String dob;
	
	@Column
	protected String uloga; // enum Uloge? Za sada je String jer mi treba kontroler da bi ih razlikovao.
	
	@Column
	protected Boolean aktivan;
	
	// Geteri i Seteri, mozda toString?
	
}
