package ftnwebcinema.Cinema.service;

import java.util.List;
import ftnwebcinema.Cinema.entity.Administrator;

//Implementacije funkcija su u implementaciji servisa
public interface AdministratorService {
	
	Administrator napravi(Administrator admin) throws Exception; // Moze da baci exception ako ne moze da ga napravi (ako je prosledjen ID nesto drugo od null)
	
	Administrator findOne(Long id);
	
	List<Administrator> findAll();

	Administrator updateAktivnost(Administrator admin) throws Exception; // Moze da baci exception ako ne moze da ga updatuje (ako on ne postoji)

    void delete(Long id);

}
