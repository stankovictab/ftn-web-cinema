package ftnwebcinema.Cinema.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ftnwebcinema.Cinema.entity.Film;
import ftnwebcinema.Cinema.entity.dto.FilmDTO;
import ftnwebcinema.Cinema.service.FilmService;

@RestController
@RequestMapping(value = "/film") 
public class FilmController {
	
	@Autowired 
	private FilmService filmService;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> getFilm(@PathVariable(name="id") Long id){
		Film temp = this.filmService.findOne(id);
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> getFilmovi() {
        List<Film> nadjenaLista = this.filmService.findAll();
        List<FilmDTO> novaLista = new ArrayList<>();
        for (Film g : nadjenaLista) {
        	FilmDTO tempFilmDTO = new FilmDTO(g.getIdFilm(), g.getNaziv(), g.getZanr());
        	novaLista.add(tempFilmDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FilmDTO> napraviFilm(@RequestBody Film dobijeni) throws Exception {
		Film zaUBazu = filmService.napravi(dobijeni); 
		FilmDTO nepotrebniDTO = new FilmDTO(zaUBazu.getIdFilm(), zaUBazu.getNaziv(), zaUBazu.getZanr());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/pretraga", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilmDTO>> pretragaFilma(@RequestBody Film dobijeni) throws ParseException{
		String keyword = dobijeni.getKeyword();
		String pretragaString = dobijeni.getPretraga(); // JSON uvek salje string
		int pretraga = Integer.parseInt(pretragaString);
		
		List<Film> listaFilmova = new ArrayList<>();
		List<FilmDTO> listaDTOFilmova = new ArrayList<>();
		FilmDTO dto = new FilmDTO();
		
		switch(pretraga) {
		case 1:
			listaFilmova = this.filmService.findByNaziv(keyword);
			break;
		case 2:
			listaFilmova = this.filmService.findByZanr(keyword);
			break;
		case 3:
			listaFilmova = this.filmService.findByOpis(keyword);
			break;
		case 4:
			listaFilmova = this.filmService.findByOcena(keyword);
			break;
		case 5: 
			listaFilmova = this.filmService.findByVremeProjekcije(keyword);
			break;
		case 6:
			listaFilmova = this.filmService.findByCena(keyword);
			break;
		default:
			System.out.println("Ne postoji takav upit.");
			break;
		}
		
		for(Film f : listaFilmova) {
			System.out.println(f.getNaziv()); // Debug
			dto = new FilmDTO(f.getIdFilm(), f.getNaziv(), f.getZanr());
			listaDTOFilmova.add(dto);
		}
		System.out.println("Test"); // Debug
		return new ResponseEntity<>(listaDTOFilmova, HttpStatus.OK);
		
	}
}
