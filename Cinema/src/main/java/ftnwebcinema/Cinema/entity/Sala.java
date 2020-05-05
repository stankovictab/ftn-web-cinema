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
	
	// Ovde treba projekcija ne film?
	@ManyToMany
	private Set<Film> terminskaListaProjekcija = new HashSet<>();

}
