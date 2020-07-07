package ftnwebcinema.Cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnwebcinema.Cinema.entity.Administrator;
import ftnwebcinema.Cinema.entity.Gledalac;
import ftnwebcinema.Cinema.entity.Korisnik;
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.service.AdministratorService;
import ftnwebcinema.Cinema.service.GledalacService;
import ftnwebcinema.Cinema.service.MenadzerService;

@RestController
@RequestMapping(value = "/prijava") 
public class PrijavaController {
	
	// Moramo da uvezemo sva 3 servisa
	@Autowired 
	private GledalacService gledalacService;
	@Autowired 
	private MenadzerService menadzerService;
	@Autowired 
	private AdministratorService administratorService;
	
	// Metoda za prijavu na sistem
	// Dobija od AJAX-a JSON (Ne-DTO objekat) sa username, password, i aktivan za tog korisnika postavljen na true u bazi (ovo ce se menjati)
	// Treba da gleda da li taj korisnik postoji, ako ne, izbaci alert za gresku
	// Value nema jer se koristi bazni URL
	// Povratna vrednost je String jer treba da zna gde da redirectuje posle prijave
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String prijavi(@RequestBody Korisnik korisnik) throws Exception { 
		String redirect = " ";
		// RequestBody se dobija iz forme u prijavi, bilo je Gledalac gledalac ali prosirujem
		// Ne DTO jer DTO nema username
		// Baca exception da bi aktivirao error u AJAX-u
		System.out.println("Prijavljivanje u toku..."); // Za debug
		
		// Treba da proveri da li korisnik postoji u sve 3 tabele, dakle korstimo sva 3 servisa
		// Dakle proveravamo u svakom i za svaki radimo exception handling
		
		// Treba da se radi provera da li je korisnik aktivan (confirmovan od strane admina) da bi mogao da se prijavi
		
		String user = korisnik.getUsername();
		String pass = korisnik.getPassword();
		Gledalac gledalac = new Gledalac();
		Menadzer menadzer = new Menadzer();
		Administrator administrator = new Administrator();
		gledalac.setUsername(user);
		gledalac.setPassword(pass);
		menadzer.setUsername(user);
		menadzer.setPassword(pass);
		administrator.setUsername(user);
		administrator.setPassword(pass);
		// Srecno
		try {
			redirect = "gledalac";
			gledalacService.updateAktivnost(gledalac); 
			// Ako updateAktivnost baci exception znaci da nije nasao korisnika kao gledaoca, 
			// pa ide u catch da ga trazi kao menadzera
			// Ako ga nadje preskace catch i ne zove nikakav novi exception da javi error AJAX-u, nego ide na return true dole
		} catch (Exception a) {
			
			try {
				redirect = "menadzer";
				menadzerService.updateAktivnost(menadzer); 
				// Ako updateAktivnost baci exception znaci da nije nasao korisnika kao menadzera, 
				// pa ide u catch da ga trazi kao administratora
				// Ako ga nadje preskace catch i ne zove nikakav novi exception da javi error AJAX-u, nego ide na return true dole
			} catch (Exception b) {
				
				try {
					redirect = "administrator";
					administratorService.updateAktivnost(administrator); 
					// Ako updateAktivnost baci exception znaci da nije nasao korisnika kao administratora, 
					// pa ide u catch da objavi da taj korisnik ne postoji nigde
					// Ako ga nadje preskace catch i ne zove nikakav novi exception da javi error AJAX-u, nego ide na return true dole
				} catch (Exception c) {
//					a.printStackTrace();
//					b.printStackTrace();
//					c.printStackTrace();
					throw new Exception(
							"Greska! Korisnik ne postoji ni kao gledalac, ni kao menadzer, ni kao administrator."
										);
					// Error u AJAX-u se ne aktivira ako se ovde posalje false, to je opet success kod njega
					// Error se aktivira ako se ne izvrsi dobro metoda kontrolera, odnosno ako se baci exception
				}
			}
		}
		
		// Svaki od ovih updateAktivnosti baca exception ako ne nadje korisnika sa tim username-om u bazi
		// Ako ne nadje nigde, odnosno ako budu 3 exception-a, onda treba da udje u error
		// Ako baca exception ide u catch
		// Ako ne baci exception nasao ga je i ide dalje
		// Za debug ovo moze na primer da se stavi kao Gledalac temp = gledalacService.updateAktivnost(gledalac); pa da se posle gleda sta je nasao 
		System.out.println("Korisnik prijavljen, izlazi iz metode kontrolera"); // Za debug
		return redirect; // Ulazi u success deo AJAX-a
	}
}
