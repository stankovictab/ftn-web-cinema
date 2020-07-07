package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.repository.MenadzerRepo;
import ftnwebcinema.Cinema.service.MenadzerService;

@Service
public class MenadzerServiceImplementacija implements MenadzerService {
	
		// Veza ka repozitorijumu
		@Autowired 
		private MenadzerRepo menadzerRepo;
		
		@Override
		public Menadzer napravi(Menadzer menadzer) throws Exception {
			if (menadzer.getIdMenadzer() != null) {
	            throw new Exception("ID Menadzera koji se pravi mora da bude null."); // Jer mu se id dodaje posle
	        }
			Menadzer novi = this.menadzerRepo.save(menadzer); 
			// Objekat novi uzima povratnu vrednost od predefinisane funkcije save iz repozitorijuma (iz JpaRepository koju implementira) koji je taj kreiran objekat
	        return novi;
		}
		
		@Override
		public Menadzer findOneById(Long id) {
			Menadzer nadjeni = this.menadzerRepo.getOne(id); 
			// Povratna vrednost od getOne (nije findOne) iz JpaRepository je taj objekat sa tim id-em
	        return nadjeni;
		}
		
		@Override
		public Menadzer findOneByUsername(String username) {
			Menadzer nadjeni = this.menadzerRepo.findByUsername(username); 
	        return nadjeni;
		}
		
		@Override
		public List<Menadzer> findAll() {
			List<Menadzer> lista = this.menadzerRepo.findAll(); 
			// findAll je predefinisana funkcija iz JpaRepository koja vraca listu iz baze
	        return lista;
		}
		
		// Bila je njena metoda ali samo za update imena
		@Override
		public Menadzer updateAktivnost(Menadzer menadzer) throws Exception {
			Menadzer nadjen = this.menadzerRepo.findByUsername(menadzer.getUsername()); // Ja trazio po id pa bacalo exception
			// nadjen.getUsername() baca null pointer exception, pa ne moze da se poredi sa null, ali samo nadjen == null hoce
			if(nadjen == null) {  // Ova provera je dobra, izbaca dobar exception
				throw new Exception("Menadzer ne moze da se update-uje jer ne postoji.");
			}
			// Provera sifre
			if(!(nadjen.getPassword().equals(menadzer.getPassword()))) { 
				// Ne moze == jer on poredi reference
				// Da nisu iste sifre -> !
				throw new Exception("Sifra se ne poklapa!");
			}
	        // Postavljanje nove vrednosti
	        nadjen.setAktivan(true);
	        // Cuvanje u bazi, ne mora da napravi novog lika, ali je to tu zbog debug-a u kontroleru (gogi)
	        Menadzer updatovan = this.menadzerRepo.save(nadjen);
	        System.out.println("Korisnik prijavljen, Aktivan postavljen na true"); // Za debug
	        return updatovan;
		}
		
		@Override
		public void delete(Long id) {
			this.menadzerRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
		}
		
}
