package ftnwebcinema.Cinema.controller;

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
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.entity.dto.MenadzerDTO;
import ftnwebcinema.Cinema.service.MenadzerService;

@RestController
@RequestMapping(value = "/menadzer") 
public class MenadzerController {
	@Autowired 
	private MenadzerService menadzerService;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menadzer> getMenadzer(@PathVariable(name="id") Long id){
		Menadzer temp = this.menadzerService.findOneById(id);
		temp.setPassword("NEMATE PRISTUP SIFRI");
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenadzerDTO>> getMenadzeri() {
        List<Menadzer> nadjenaLista = this.menadzerService.findAll();
        List<MenadzerDTO> novaLista = new ArrayList<>();
        for (Menadzer g : nadjenaLista) {
        	MenadzerDTO tempMenadzerDTO = new MenadzerDTO(g.getIdMenadzer(), g.getIme(), g.getPrezime(), g.getUloga(), g.getAktivan());
        	novaLista.add(tempMenadzerDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenadzerDTO> napraviMenadzera(@RequestBody Menadzer dobijeni) throws Exception {
		// Provera da li vec postoji korisnik sa tim username-om
		Menadzer tester = menadzerService.findOneByUsername(dobijeni.getUsername());
		if(tester != null) { // Odnosno ako ga nadje
			// Print za getUsername kojeg nema ce baciti null pointer exception (ustvari bilo koja operacija sa tester, jer je on null)
			throw new Exception("Menadzer sa tim username-om vec postoji!");
		}
		// Za id i za upisivanje u bazu (dobijeni je ceo pun objekat iz AJAX-a, samo bez id-a)
		// zaUBazu je isto nepotreban
		Menadzer zaUBazu = menadzerService.napravi(dobijeni);
		// Vracamo dto od njega kao povratnu vrednost (da dto ima id), ali zasto? Nema potrebe, ne koristi se nigde?
		MenadzerDTO nepotrebniDTO = new MenadzerDTO(zaUBazu.getIdMenadzer(), 
													zaUBazu.getIme(), 
													zaUBazu.getPrezime(), 
													zaUBazu.getUloga(),
													zaUBazu.getAktivan());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	// Nije @PostMapping jer on zahteva JSON, a mi nista ne saljemo\
	// Moze i da ne vraca nista? Pa moze i da bude void?
	// Ako se obrise consumes, onda mozda ne mora da se stavlja contentType
	@GetMapping(value = "/aktivacija/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenadzerDTO> aktivacijaMenadzera(@PathVariable(name="id") Long id) throws Exception {
		// id dobijamo iz id-a dugmeta koji je pritisnut u tabeli aktivacije menadzera kod admina
		Menadzer dobijeni = this.menadzerService.findOneById(id); // Nece bacati exception jer sigurno postoji, cim je u tabeli
		this.menadzerService.updateAktivnost(dobijeni);
		
		MenadzerDTO nepotrebniDTO = new MenadzerDTO(dobijeni.getIdMenadzer(), 
													dobijeni.getIme(), 
													dobijeni.getPrezime(), 
													dobijeni.getUloga(),
													dobijeni.getAktivan());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/brisanje/{id}")
	public void brisanjeMenadzera(@PathVariable(name="id") Long id) throws Exception {
		System.out.println(id);
		
		Menadzer dobijeni = this.menadzerService.findOneById(id);
		System.out.println(dobijeni);
		
		// id dobijamo iz id-a dugmeta koji je pritisnut u tabeli aktivacije menadzera kod admina
		this.menadzerService.delete(id); // Nece bacati exception jer sigurno postoji, cim je u tabeli
	}
}
