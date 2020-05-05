package ftnwebcinema.Cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
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
	
	@ManyToMany
	private Set<Projekcija> terminskaListaProjekcija = new HashSet<>();
	// Pisalo Film umesto projekcija
}
