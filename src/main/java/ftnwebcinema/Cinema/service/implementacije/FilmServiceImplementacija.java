package ftnwebcinema.Cinema.service.implementacije;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Film;
import ftnwebcinema.Cinema.entity.Projekcija;
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

	@Override
	public List<Film> findByNaziv(String keyword) {
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		for(Film f : sviFilmovi) {
			if(f.getNaziv().contains(keyword)) { // !!!
				povratniFilmovi.add(f);
			}
		}
		return povratniFilmovi;
	}

	@Override
	public List<Film> findByZanr(String keyword) {
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		for(Film f : sviFilmovi) {
			if(f.getZanr().contains(keyword)) { // !!!
				povratniFilmovi.add(f);
			}
		}
		return povratniFilmovi;
	}

	@Override
	public List<Film> findByOpis(String keyword) {
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		for(Film f : sviFilmovi) {
			if(f.getOpis().contains(keyword)) { // !!!
				povratniFilmovi.add(f);
			}
		}
		return povratniFilmovi;
	}

	@Override
	public List<Film> findByOcena(String keyword) {
		double krit = Double.parseDouble(keyword);
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		for(Film f : sviFilmovi) {
			if(f.getSrednjaOcena() >= krit) { // !!!
				povratniFilmovi.add(f);
			}
		}
		return povratniFilmovi;
	}

	@Override
	public List<Film> findByVremeProjekcije(String keyword) throws ParseException {
		
		Set<Projekcija> projekcije = new HashSet<>();
		
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		
		java.util.Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(keyword);		
		
		for(Film f : sviFilmovi) {
			projekcije = f.getListaProjekcija();
			for(Projekcija p : projekcije) {
				java.util.Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(p.getDatumProjekcije());
				if(date1.after(date2)) { // !!!
					povratniFilmovi.add(f);
				}
			}
		}
		return povratniFilmovi;
	}

	@Override
	public List<Film> findByCena(String keyword) {
		int krit = Integer.parseInt(keyword);
		Set<Projekcija> projekcije = new HashSet<>();
		List<Film> sviFilmovi = this.filmRepo.findAll();
		List<Film> povratniFilmovi = new ArrayList<>();
		for(Film f : sviFilmovi) {
			projekcije = f.getListaProjekcija();
			for(Projekcija p : projekcije) {
				if(p.getCenaProjekcije() <= krit) { // !!!
					povratniFilmovi.add(f);
				}
			}
		}
		return povratniFilmovi;
	}

}
