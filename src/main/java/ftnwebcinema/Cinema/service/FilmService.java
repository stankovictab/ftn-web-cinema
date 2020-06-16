package ftnwebcinema.Cinema.service;

import java.util.List;
import ftnwebcinema.Cinema.entity.Film;

public interface FilmService {
	Film napravi(Film film) throws Exception;
	
	Film findOne(Long id);
	
	List<Film> findAll();

	Film update(Film film) throws Exception; 

    void delete(Long id);
}
