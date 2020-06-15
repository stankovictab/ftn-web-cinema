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
@RequestMapping(value = "/api/menadzeri") 
public class MenadzerController {
	@Autowired 
	private MenadzerService menadzerService;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menadzer> getMenadzer(@PathVariable(name="id") Long id){
		Menadzer temp = this.menadzerService.findOne(id);
		temp.setPassword("NEMATE PRISTUP SIFRI");
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenadzerDTO>> getMenadzeri() {
        List<Menadzer> nadjenaLista = this.menadzerService.findAll();
        List<MenadzerDTO> novaLista = new ArrayList<>();
        for (Menadzer g : nadjenaLista) {
        	MenadzerDTO tempMenadzerDTO = new MenadzerDTO(g.getIdMenadzer(), g.getIme(), g.getPrezime(), g.getUloga());
        	novaLista.add(tempMenadzerDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenadzerDTO> napraviMenadzera(@RequestBody Menadzer dobijeni) throws Exception {
		Menadzer zaUBazu = menadzerService.napravi(dobijeni); 
		MenadzerDTO nepotrebniDTO = new MenadzerDTO(zaUBazu.getIdMenadzer(), 
													zaUBazu.getIme(), 
													zaUBazu.getPrezime(), 
													zaUBazu.getUloga());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
}
