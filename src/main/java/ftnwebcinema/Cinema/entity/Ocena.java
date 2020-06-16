package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Dodato zbog ?? ali sa ovim radi kontrolerska metoda getGledalac
@Entity
public class Ocena implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOcena;
	
	@Column
	private int ocena;
	
	@ManyToOne 
	private Gledalac gledalac;
	// Gledalac moze da da vise ocena, ali ne mogu vise gledalaca da daju istu ocenu (ocenu kao komentar, ne int ocena)
	// Vezuje se za Gledalac -> listaOcena
	// Ovde je kolona
	
	@ManyToOne 
	private Film film;
	// Vise ocena moze za jedan film, a ne mogu vise filmova da dele istu ocenu (ocenu kao komentar, ne int ocena)
	// Vezuje se za Film -> listaOcena
	// Ovde je kolona
	
}
