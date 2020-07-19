package ftnwebcinema.Cinema.service;

import ftnwebcinema.Cinema.entity.Projekcija;

public interface ProjekcijaService {

	Projekcija napravi(Projekcija p);
	
	Projekcija findByDatumProjekcije(String datum);

	boolean updateProjekcije(Projekcija nadjena, String datumProjekcije);
	
}
