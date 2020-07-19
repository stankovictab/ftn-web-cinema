package ftnwebcinema.Cinema.service;

import java.text.ParseException;
import java.util.List;
import ftnwebcinema.Cinema.entity.Film;

public interface FilmService {
	Film napravi(Film film) throws Exception;
	
	Film findOne(Long id);
	
	List<Film> findAll();

	Film update(Film film) throws Exception; 

    void delete(Long id);

	List<Film> findByNaziv(String keyword);

	List<Film> findByZanr(String keyword);

	List<Film> findByOpis(String keyword);

	List<Film> findByOcena(String keyword);

	List<Film> findByVremeProjekcije(String keyword) throws ParseException ;

	List<Film> findByCena(String keyword);
}
