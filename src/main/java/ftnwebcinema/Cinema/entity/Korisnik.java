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
	protected String telefon; // Long?
	
	@Column
	protected String email;
	
	@Column
	protected String dob;
	
	@Column
	protected String uloga; // Za sada je String jer mi treba kontroler da bi ih razlikovao
	
	@Column
	protected Boolean aktivan;
	
	// Geteri, seteri i konstruktori kod nasledjivaca za drugu tacku
	
}
