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
			// Gledalac je po default-u aktivan, pa se on prijavljuje uvek ako su kredencijali tacni
			Gledalac noviGledalac = gledalacService.findOneByUsername(user); // Moze i gledalac.getUsername()
			if(noviGledalac != null) { // Ako postoji (mora ovako jer noviGledalac.getUsername() != null nece, bilo bi null pointer exception)
				// Provera sifre
				if(!(noviGledalac.getPassword().equals(pass))) { 
					// Ne moze == jer on poredi reference
					// Da nisu iste sifre -> !
					throw new Exception("\nSifra gledaoca se ne poklapa!\n");
				}
				System.out.println("\nGledalac prijavljen, izlazi iz metode kontrolera\n");
				return "gledalac";
			}else 
				throw new Exception("\nGledalac ne moze da se nadje jer ne postoji pod tim username-om.\n");
			// Ako ne nadje korisnika kao gledaoca bacice exception koji ce se uhvatiti, i trazice se menadzer
			// Ako ga nadje radi return i ne ulazi u AJAX-ov error
		} catch (Exception a) {
			try {
				// Menadzeru treba da se proverava aktivnost nakon provere kredencijala
				Menadzer noviMenadzer = menadzerService.findOneByUsername(user);
				if(noviMenadzer != null) { // Ako postoji (mora ovako jer noviGledalac.getUsername() != null nece, bilo bi null pointer exception)
					// Provera sifre
					if(!(noviMenadzer.getPassword().equals(pass))) { 
						// Ne moze == jer on poredi reference
						// Da nisu iste sifre -> !
						throw new Exception("\nSifra menadzera se ne poklapa!\n");
					}
					if(noviMenadzer.getAktivan() == true) {
						System.out.println("\nMenadzer prijavljen, izlazi iz metode kontrolera\n");
						return "menadzer";
					}else 
						throw new Exception("\nMenadzer nije aktivan!\n");
				}else 
					throw new Exception("\nMenadzer ne moze da se nadje jer ne postoji pod tim username-om.\n");
				// Ako ne nadje korisnika kao menadzera bacice exception koji ce se uhvatiti, i trazice se administrator
				// Ako ga nadje radi return i ne ulazi u AJAX-ov error
			} catch (Exception b) {
				try {
					// Administrator je po defaultu aktiviran, samo se gledaju kredencijali
					Administrator noviAdministrator = administratorService.findOneByUsername(user);
					if(noviAdministrator != null) { // Ako postoji (mora ovako jer noviGledalac.getUsername() != null nece, bilo bi null pointer exception)
						// Provera sifre
						if(!(noviAdministrator.getPassword().equals(pass))) { 
							// Ne moze == jer on poredi reference
							// Da nisu iste sifre -> !
							throw new Exception("\nSifra administratora se ne poklapa!\n");
						}
						System.out.println("\nAdministrator prijavljen, izlazi iz metode kontrolera\n");
						return "administrator";
					}else 
						throw new Exception("\nAdministrator ne moze da se nadje jer ne postoji pod tim username-om.\n");
					// Ako ne nadje korisnika kao admina bacice exception koji ce se uhvatiti, i javice da korisnik sa tim podacima ne postoji
					// Ako ga nadje radi return i ne ulazi u AJAX-ov error
				} catch (Exception c) {
					a.printStackTrace();
					b.printStackTrace();
					c.printStackTrace();
					throw new Exception(
							"\nGreska! Korisnik ne postoji ni kao gledalac, ni kao menadzer, ni kao administrator.\n"
										);
					// Error u AJAX-u se ne aktivira ako se ovde posalje false, to je opet success kod njega
					// Error se aktivira ako se ne izvrsi dobro metoda kontrolera, odnosno ako se baci exception
				}
			}
		}
	}
}
