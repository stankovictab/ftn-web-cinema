package ftnwebcinema.Cinema.service;

import java.util.List;
import ftnwebcinema.Cinema.entity.Menadzer;

//Implementacije funkcija su u implementaciji servisa
public interface MenadzerService {
	
	Menadzer napravi(Menadzer menadzer) throws Exception; // Moze da baci exception ako ne moze da ga napravi (ako je prosledjen ID nesto drugo od null)
	
	Menadzer findOne(Long id);
	
	List<Menadzer> findAll();

	Menadzer update(Menadzer menadzer) throws Exception; // Moze da baci exception ako ne moze da ga updatuje (ako on ne postoji)

    void delete(Long id);

}
