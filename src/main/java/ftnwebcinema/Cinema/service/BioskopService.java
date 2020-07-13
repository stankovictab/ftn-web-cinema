package ftnwebcinema.Cinema.service;

import java.util.List;

import ftnwebcinema.Cinema.entity.Bioskop;

public interface BioskopService {
	
	Bioskop napravi(Bioskop bioskop, Long idMenadzer) throws Exception;
	
	Bioskop findOne(Long id);
	
	List<Bioskop> findAll();

	Bioskop update(Bioskop bioskop) throws Exception; 

    void delete(Long id);
}
