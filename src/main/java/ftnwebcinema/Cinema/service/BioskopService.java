package ftnwebcinema.Cinema.service;

import java.util.List;

import ftnwebcinema.Cinema.entity.Bioskop;
import ftnwebcinema.Cinema.entity.Sala;

public interface BioskopService {
	
	Bioskop napravi(Bioskop bioskop, Long idMenadzer) throws Exception;
	
	Bioskop findOne(Long id);
	
	Bioskop findByNaziv(String naziv);
	
	List<Bioskop> findAll();

	Bioskop updateNaziv(Bioskop bioskop, String noviNaziv) throws Exception; 

    void delete(Long id);
    
    boolean dodajSalu(Sala sala, Long id);
}
