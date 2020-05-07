package ftnwebcinema.Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftnwebcinema.Cinema.entity.Film;

public interface FilmRepo extends JpaRepository<Film, Long>{

}
