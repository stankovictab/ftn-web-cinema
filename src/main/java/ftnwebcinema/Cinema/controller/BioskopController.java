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
import ftnwebcinema.Cinema.service.SalaService;

@RestController
@RequestMapping(value = "/bioskop") 
public class BioskopController {
	
	@Autowired 
	private BioskopService bioskopService;
	@Autowired 
	private MenadzerService menadzerService;
	@Autowired 
	private SalaService salaService;
	
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
		
		// Provera naziva Bioskop-a, da li vec postoji jedan pod tim nazivom
		List<Bioskop> lista = this.bioskopService.findAll();
		for(Bioskop b : lista) {
			// System.out.println("Nasao " + b.getNaziv());
			if(b.getNaziv().equals(dobijeni.getNaziv())) { // equals, ne ==
				throw new Exception("Bioskop sa tim nazivom vec postoji.");
			}
		}
		
		// Treba posebna provera za menadzer-polje
		String user = dobijeni.getMenadzerUsername();
//		System.out.println(user); // Nadje
		// Moramo da mu nadjemo id, pa moramo da uvedemo menadzerService
		Menadzer nadjen = menadzerService.findOneByUsername(user);
		
		// Nadje ga, ako postoji menadzer sa tim username-om, ako ne baci null pointer exception
		if(nadjen == null) { // Ne nadjen.getIdMenadzer == null
			throw new Exception("Menadzer sa tim username-om ne postoji.");
		}
		Long idMenadzer = nadjen.getIdMenadzer(); 
		
//		System.out.println(idMenadzer); // Nadje
//		System.out.println(dobijeni.getIdBioskop()); // null jer ga nije ni napravio, tako treba
		
		Bioskop zaUBazu = bioskopService.napravi(dobijeni, idMenadzer); // Pravi id
		BioskopDTO nepotrebniDTO = new BioskopDTO(zaUBazu.getIdBioskop(), zaUBazu.getNaziv(), zaUBazu.getAdresa());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateBioskop(@RequestBody Bioskop dobijeni) throws Exception {
		// dobijeni ima samo dva polja
		System.out.println(dobijeni.getStariNaziv()); // Vraca dobro prosledjen trazeni naziv
		System.out.println(dobijeni.getNaziv());
		String stari = dobijeni.getStariNaziv();
		String novi = dobijeni.getNaziv();
		
		Bioskop nadjen = this.bioskopService.findByNaziv(stari);
		
		// Nadje ga ako postoji, ako ne postoji baca null pointer exception
		if(nadjen == null) // Ne nadjen.getIdBioskop() == null
			throw new Exception("Nije nadjen Bioskop sa tim imenom."); // Izacice iz metode i uci ce u error u AJAX-u
		System.out.println(nadjen.getIdBioskop()); 
		
		this.bioskopService.updateNaziv(nadjen, novi);
		
		System.out.println(nadjen.getStariNaziv());
		System.out.println(nadjen.getNaziv());
		
		return;
	}
	
	@PostMapping(value="/brisanje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void obrisiBioskop(@RequestBody Bioskop dobijeni) throws Exception {
		// dobijeni ima samo jedno polje
		System.out.println(dobijeni.getNaziv());
		String naziv = dobijeni.getNaziv();
		
		Bioskop nadjen = this.bioskopService.findByNaziv(naziv);
		
		// Nadje ga ako postoji, ako ne postoji baca null pointer exception
		if(nadjen.getIdBioskop() == null)
			throw new Exception("Nije nadjen Bioskop sa tim imenom."); // Izacice iz metode i uci ce u error u AJAX-u
		System.out.println(nadjen.getIdBioskop()); 
		
		// Brisanje bioskopa mora da pozove i brisanje sala
		
		this.salaService.delete(nadjen.getIdBioskop()); // Brisanje sala se odvija po id-u bioskopa
		
		this.bioskopService.delete(nadjen.getIdBioskop());

		System.out.println(nadjen.getNaziv()); // Treba da vrati null
		
		return;
	}
}
