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
import ftnwebcinema.Cinema.entity.dto.BioskopDTO;
import ftnwebcinema.Cinema.service.BioskopService;
import ftnwebcinema.Cinema.service.MenadzerService;

@RestController
@RequestMapping(value = "/bioskop") 
public class BioskopController {
	
	@Autowired 
	private BioskopService bioskopService;
	@Autowired 
	private MenadzerService menadzerService;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bioskop> getBioskop(@PathVariable(name="id") Long id){
		Bioskop temp = this.bioskopService.findOne(id);
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BioskopDTO>> getBioskopi() {
        List<Bioskop> nadjenaLista = this.bioskopService.findAll();
        List<BioskopDTO> novaLista = new ArrayList<>();
        for (Bioskop g : nadjenaLista) {
        	BioskopDTO tempBioskopDTO = new BioskopDTO(g.getIdBioskop(), g.getNaziv(), g.getAdresa());
        	novaLista.add(tempBioskopDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> napraviBioskop(@RequestBody Bioskop dobijeni) throws Exception {
		// Treba posebna provera za menadzer-polje
		String user = dobijeni.getMenadzerUsername();
		// Moramo da mu nadjemo id, pa moramo da uvedemo menadzerService
		Menadzer nadjen = menadzerService.findOneByUsername(user);
		Long id = nadjen.getIdMenadzer(); // Nadje
		// Sta onda?
		Bioskop zaUBazu = bioskopService.napravi(dobijeni); 
		BioskopDTO nepotrebniDTO = new BioskopDTO(zaUBazu.getIdBioskop(), zaUBazu.getNaziv(), zaUBazu.getAdresa());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateBioskop(@RequestBody String jsonceo) throws Exception {
		System.out.println(jsonceo);
		return "kita";
	}
}
