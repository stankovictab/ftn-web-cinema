package ftnwebcinema.Cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ftnwebcinema.Cinema.entity.Gledalac;
import ftnwebcinema.Cinema.entity.dto.GledalacDTO;
import ftnwebcinema.Cinema.service.GledalacService;
import org.springframework.http.ResponseEntity;

// Backup bez modifikacija na Notion
// Modifikacije:
// 1. getGledalac vraca Gledalac (temp), ne GledalacDTO (dto)
// 2. Tu isto obrisao pravljenje dto-a od temp-a
// 3. napraviGledaoca dobija Gledalac, ne GledalacDTO
// 4. Tu tog sto dobije stavlja u bazu da bi napravio novog gledaoca sa id-em, pa ostalo isto, pravi dto sa tim id-em
// 5. Dodao @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) u Gledalac jer bez toga nece, niko ne zna sto

@RestController // Ne @Controller (ne znam da li moze sa tim) jer hocemo kontroler koji funkcionise kao endpoint RESTful servisa, rezultat svake metode kontrolera treba da se vrati kao JSON u body-u
@RequestMapping(value = "/api/gledaoci") 
// RequestMapping ima za value "bazni url koji klasa obradjuje" odnosno homepage za kontroler, od ovoga ide mapiranje na npr /{id}
// Ovo ustvari msm da i nije toliko bitno jer moze za svaku metodu kontrolera da se stavi value (ovo je default mapiranje za one koji nemaju value)
public class GledalacController {
	
	// Veze ka servisima (ne ka implementacijama) 
	// (nije constructor-based DI, mozda treba?)
	@Autowired 
	private GledalacService gledalacService;
	
	// Ne moze @GetMapping na / pa da vrati homepage.html jer to zahteva Thymeleaf, vratio bi bukvalno samo tekst homepage.html ovako	
	// Do pocetne stranice ona dolazi preko index.html koji je default-ni homepage za spring projekte, pa odatle dugmetom do stranica koje nam trebaju, ne preko kontrolera (mada bi bilo bolje tako)
	
	// Kontroler da dobije jednog korisnika, salje ga preko AJAX-a i JSON-a, koristi se u prikazivanju profila (on click na dugme see more kod nje)
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gledalac> getGledalac(@PathVariable(name="id") Long id){
		// PathVariable je ovaj {id} koji koristimo
		Gledalac temp = this.gledalacService.findOne(id);
		temp.setPassword("NEMATE PRISTUP SIFRI"); // Da se ne bi sifra videla iz konzole na F12, ovo ne menja nista u bazi
		// Saljemo temp, a ne dto
		return new ResponseEntity<>(temp, HttpStatus.OK);
		// ResponseEntity je povratna vrednost metode, i to je JSON objekat koji smo napunili sa DTO-om i Http statusom koji ide u AJAX (to je data u AJAX-u)
	}
	
	// Kontroler da dobije sve gledaoce, isto preko DTO-a
	// Value nema jer koristimo bazni URL (!) pa ce se na toj adresi aktivirati (jer se kontroler aktivira kada dodjemo na stranicu, valjda)
	// Ovde nema sta da se menja, jer se ovo koristi pri kreiranju tabele koja zahteva DTO objekte (za to i sluze)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GledalacDTO>> getGledaoci() {
        List<Gledalac> nadjenaLista = this.gledalacService.findAll();
        List<GledalacDTO> novaLista = new ArrayList<>();
        for (Gledalac g : nadjenaLista) {
        	GledalacDTO tempGledalacDTO = new GledalacDTO(g.getIdGledalac(), g.getIme(), g.getPrezime(), g.getUloga());
        	novaLista.add(tempGledalacDTO);
        }
        return new ResponseEntity<>(novaLista, HttpStatus.OK);
    }
	
	// Kontroler metoda za kreiranje gledaoca
	// Radi se POST jer saljemo na server
	// Metoda i prima i salje JSON
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GledalacDTO> napraviGledaoca(@RequestBody Gledalac dobijeni) throws Exception {
		// Za id i za upisivanje u bazu (dobijeni je ceo pun objekat iz AJAX-a, samo bez id-a)
		Gledalac zaUBazu = gledalacService.napravi(dobijeni);
		// Vracamo dto od njega kao povratnu vrednost (da dto ima id), ali zasto? Nema potrebe, ne koristi se nigde?
		GledalacDTO nepotrebniDTO = new GledalacDTO(zaUBazu.getIdGledalac(), 
													zaUBazu.getIme(), 
													zaUBazu.getPrezime(), 
													zaUBazu.getUloga());
		return new ResponseEntity<>(nepotrebniDTO, HttpStatus.OK);
	}
	
	// !!! NIJE PREKOPIRANO U OSTALE KONTROLERE JER NE FUNKCIONISE
	// Nova metoda za prijavu
	// Dobija od AJAX-a JSON sa username, password, i aktivan postavljen na true
	// Treba da gleda da li taj korisnik postoji, ako ne, izbaci alert za gresku
	// ? Onaj if sa uslovom getByUsername && getByPassword, pa da menja aktivan status
	// Rade valjda i path i value za mapiranje
	@PostMapping(value="/prijava", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean prijavi(@RequestBody Gledalac gledalac) { // Ne DTO jer nema username
		System.out.println("usao");
		try {
			Gledalac gogi = gledalacService.updateAktivnost(gledalac);
			System.out.println(gogi.getAktivan());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n");
			System.out.println("Greska! Gledalac ne postoji.");
			return false; // Vrati false da bi se aktivirao error u AJAX-u
		}
	}
	
}