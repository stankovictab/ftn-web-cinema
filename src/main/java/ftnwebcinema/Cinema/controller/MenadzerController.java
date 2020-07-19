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

import ftnwebcinema.Cinema.entity.Bioskop;
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.entity.Sala;
import ftnwebcinema.Cinema.entity.dto.MenadzerDTO;
import ftnwebcinema.Cinema.service.BioskopService;
import ftnwebcinema.Cinema.service.MenadzerService;
import ftnwebcinema.Cinema.service.SalaService;

@RestController
@RequestMapping(value = "/menadzer") 
public class MenadzerController {
	
	@Autowired 
	private MenadzerService menadzerService;
	
	@Autowired 
	private SalaService salaService;
	
	@Autowired 
	private BioskopService bioskopService;
	
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
	
	// Nije @PostMapping jer on zahteva JSON, a mi nista ne saljemo
	// Moze i da ne vraca nista, pa moze da bude void.
	// consumes i produces nema jer nista ne zahteva od AJAX-a i nista mu ne salje (iako to ovde tako izgleda, nista se ipak nece poslati)
	@GetMapping(value = "/aktivacija/{id}")
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
		// id dobijamo iz id-a dugmeta koji je pritisnut u tabeli aktivacije menadzera kod admina
		this.menadzerService.delete(id); // Bacice exception ako je menadzer vec u vezi sa nekim bioskopom, pa ne moze da se obrise
	}
	
	@PostMapping(value = "/sale/dodavanje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean dodavanjeSala(@RequestBody Sala dobijena) {
		Bioskop bioskop = this.bioskopService.findByNaziv(dobijena.getNazivBioskopa());
        Sala temp = new Sala(bioskop,
        		dobijena.getOznakaSale(),
        		dobijena.getKapacitet());
        if (this.salaService.napraviSalu(temp)) {
            return this.bioskopService.dodajSalu(temp, bioskop.getIdBioskop());
        }
        return false;
	}
	
	@PostMapping(value = "/sale/izmena", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izmenaSala(@RequestBody Sala dobijena) {
		// Treba da nadje salu koja sadrzi trenutno staro ime iz servisa promeni ga, i .save()-uje
		System.out.println(dobijena.getStaraOznakaSale());
		System.out.println(dobijena.getOznakaSale());
		
		Sala s = this.salaService.findByOznaka(dobijena.getStaraOznakaSale());		
		this.salaService.updateOznake(s, dobijena.getOznakaSale()); // Posto save() mora iz repo-a
	}
	
	@PostMapping(value = "/sale/brisanje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void brisanjeSala(@RequestBody Sala dobijena) {
		// Treba da nadje salu sa tom oznakom i da proba da je obrise, ali sta ako ne moze da je obrise zbog bioskopa?
		String oznaka = dobijena.getOznakaSale();
		System.out.println(oznaka);
		
		Sala s = this.salaService.findByOznaka(oznaka);
		this.salaService.delete(s);
	}
}
