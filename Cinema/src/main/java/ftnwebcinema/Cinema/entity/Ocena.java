package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Ocena implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOcena;
	
	@ManyToOne 
	// Mozes da budes vlasnik vise komentara, ali ne mozemo nas dvojica da budemo vlasnici istog komentara 
	private Gledalac gledalac;
	
	@ManyToOne // Zato sto vise ocena moze za jedan film, a ne mogu vise filmova da dele istu ocenu
	private Film film;
	
	@Column
	private int ocena;
	
}
