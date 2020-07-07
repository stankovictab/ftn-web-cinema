package ftnwebcinema.Cinema.service;

import java.util.List;
import ftnwebcinema.Cinema.entity.Gledalac;

//Implementacije funkcija su u implementaciji servisa
public interface GledalacService {
	
	Gledalac napravi(Gledalac gledalac) throws Exception; // Moze da baci exception ako ne moze da ga napravi (ako je prosledjen ID nesto drugo od null)
	
	Gledalac findOneById(Long id);
	
	Gledalac findOneByUsername(String username);
	
	List<Gledalac> findAll();

	Gledalac updateAktivnost(Gledalac gledalac) throws Exception; // Moze da baci exception ako ne moze da ga updatuje (ako on ne postoji)

    void delete(Long id);

}
