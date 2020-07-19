package ftnwebcinema.Cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ftnwebcinema.Cinema.entity.Film;
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.entity.Projekcija;
import ftnwebcinema.Cinema.service.BioskopService;
import ftnwebcinema.Cinema.service.FilmService;
import ftnwebcinema.Cinema.service.MenadzerService;
import ftnwebcinema.Cinema.service.ProjekcijaService;

@RestController
@RequestMapping(value = "/projekcija") 
public class ProjekcijaController {
	
	@Autowired 
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private MenadzerService menadzerService;
	
	@PostMapping(value = "/kreiranje/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean kreiranjeProjekcije(@RequestBody Projekcija dobijena, @PathVariable(name="username") String username) throws Exception {
		// Film ce biti samo string na pocetku
		System.out.println(dobijena.getDatumProjekcije());
		System.out.println(dobijena.getCenaProjekcije());
		System.out.println(dobijena.getTempIDFilma());
		
		Film f = this.filmService.findOne(dobijena.getTempIDFilma());
		
		Projekcija saFilmom = new Projekcija();
		saFilmom.setDatumProjekcije(dobijena.getDatumProjekcije());
		saFilmom.setCenaProjekcije(dobijena.getCenaProjekcije());
		saFilmom.setFilm(f);
		
		Projekcija nova = this.projekcijaService.napravi(saFilmom);
		// nova ce imati id
		
		if(nova == null) {
			throw new Exception("Greska u kreiranju projekcije.");
		}
		
		Menadzer m = this.menadzerService.findOneByUsername(username);
		
		if(this.bioskopService.dodajProjekciju(nova, m.getMenadzerovBioskop().getIdBioskop())) {
			return true;
		}
		return false;
	}
	
	@PostMapping(value = "/izmena/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean izmenaProjekcije(@RequestBody Projekcija dobijena, @PathVariable(name="username") String username) throws Exception {
		System.out.println(dobijena.getStariDatumProjekcije());
		System.out.println(dobijena.getDatumProjekcije());
		
		// Menadzer m = this.menadzerService.findOneByUsername(username);
		// Menadzer se ne koristi
		
		Projekcija nadjena = this.projekcijaService.findByDatumProjekcije(dobijena.getStariDatumProjekcije());
		
		System.out.println(nadjena.getIdProjekcija());
		
		if(this.projekcijaService.updateProjekcije(nadjena, dobijena.getDatumProjekcije())) {
			return true;
		}
		return false;
	}
	
}
