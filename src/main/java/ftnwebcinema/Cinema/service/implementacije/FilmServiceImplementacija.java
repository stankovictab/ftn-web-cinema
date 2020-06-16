package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Film;
import ftnwebcinema.Cinema.repository.FilmRepo;
import ftnwebcinema.Cinema.service.FilmService;

@Service
public class FilmServiceImplementacija implements FilmService{

	@Autowired 
	private FilmRepo filmRepo;
	
	@Override
	public Film napravi(Film film) throws Exception {
		if (film.getIdFilm() != null) {
            throw new Exception("ID filma koji se pravi mora da bude null."); 
        }
		Film novi = this.filmRepo.save(film); 
		
        return novi;
	}

	@Override
	public Film findOne(Long id) {
		Film nadjeni = this.filmRepo.getOne(id); 
        return nadjeni;
	}

	@Override
	public List<Film> findAll() {
		List<Film> lista = this.filmRepo.findAll(); 
        return lista;
	}

	@Override
	public Film update(Film film) throws Exception {
		Film neUpdatovan = this.filmRepo.getOne(film.getIdFilm());
        if (neUpdatovan == null) {
            throw new Exception("Film ne moze da se update-uje jer ne postoji.");
        }

        // Postavljanje novog imena
        neUpdatovan.setNaziv(film.getNaziv());

        // Cuvanje u bazi
        Film updatovan = this.filmRepo.save(neUpdatovan);
        return updatovan;
	}

	@Override
	public void delete(Long id) {
		this.filmRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
	}

}
