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
import ftnwebcinema.Cinema.entity.Administrator;
import ftnwebcinema.Cinema.entity.dto.AdministratorDTO;
import ftnwebcinema.Cinema.service.AdministratorService;

@RestController
@RequestMapping(value = "/administrator") 
public class AdministratorController {
	@Autowired 
	private AdministratorService administratorService;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> getAdministrator(@PathVariable(name="id") Long id){
		Administrator temp = this.administratorService.findOneById(id);
		temp.setPassword("NEMATE PRISTUP SIFRI");
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministratorDTO>> getAdministratori() {
        List<Administrator> nadjenaLista = this.administratorService.findAll();
        List<AdministratorDTO> novaLista = new ArrayList<>();
        for (Administrator g : nadjenaLista) {
        	AdministratorDTO tempAdministratorDTO = new AdministratorDTO(g.getIdAdministrator(), g.getIme(), g.getPrezime(), g.getUloga());
        	novaLista.add(tempAdministratorDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdministratorDTO> napraviAdministratora(@RequestBody Administrator dobijeni) throws Exception {
		// Provera da li vec postoji korisnik sa tim username-om
		Administrator tester = administratorService.findOneByUsername(dobijeni.getUsername());
		if(tester != null) { // Odnosno ako ga nadje
			// Print za getUsername kojeg nema ce baciti null pointer exception (ustvari bilo koja operacija sa tester, jer je on null)
			throw new Exception("Administrator sa tim username-om vec postoji!");
		}
		// Za id i za upisivanje u bazu (dobijeni je ceo pun objekat iz AJAX-a, samo bez id-a)
		// zaUBazu je isto nepotreban
		Administrator zaUBazu = administratorService.napravi(dobijeni);
		// Vracamo dto od njega kao povratnu vrednost (da dto ima id), ali zasto? Nema potrebe, ne koristi se nigde?
		AdministratorDTO nepotrebniDTO = new AdministratorDTO(zaUBazu.getIdAdministrator(), 
													zaUBazu.getIme(), 
													zaUBazu.getPrezime(), 
													zaUBazu.getUloga());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
}
