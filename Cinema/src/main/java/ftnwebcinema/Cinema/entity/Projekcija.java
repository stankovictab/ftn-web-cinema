package ftnwebcinema.Cinema.entity;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Projekcija implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjekcija;
	
	@ManyToOne 
	private Film film;
	// Jer vise projekcija moze ka istom filmu ali obrnuto ne moze (jedan film u vise projekcija)
	
	@Column
	private String datumProjekcije;
	
	@Column
	private double cenaProjekcije;
	
	@ManyToMany
	private Set<Bioskop> listaBioskopovaUKojimaSePrikazuje = new HashSet<>(); 
	// Posto je manytomany mora oba da budu set
	
	@ManyToOne
	private Gledalac rezervator;
	// Ako je ovde set mora onda da bude ManyToMany jer je listaRezervisanih Filmova vec set
	// Ako samo stoji Gledalac rezervator onda projekcija ne moze da ima listu gledalaca koji su rezervisali
	// Mozda promenim jer ovako malo nema smisla realno
}