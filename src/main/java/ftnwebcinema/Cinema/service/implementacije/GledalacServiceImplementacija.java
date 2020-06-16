package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Gledalac;
import ftnwebcinema.Cinema.repository.GledalacRepo;
import ftnwebcinema.Cinema.service.GledalacService;

@Service
public class GledalacServiceImplementacija implements GledalacService {
		
		// Veza ka repozitorijumu
		@Autowired 
		private GledalacRepo gledalacRepo;
		
		@Override
		public Gledalac napravi(Gledalac gledalac) throws Exception {
			if (gledalac.getIdGledalac() != null) {
	            throw new Exception("ID Gledaoca koji se pravi mora da bude null."); // Jer mu se id dodaje posle
	        }
			Gledalac novi = this.gledalacRepo.save(gledalac); 
			// Objekat novi uzima povratnu vrednost od predefinisane funkcije save iz repozitorijuma 
			// (iz JpaRepository koju implementira) koji je taj kreiran objekat
	        return novi;
	    }

		@Override
		public Gledalac findOne(Long id) {
			Gledalac nadjeni = this.gledalacRepo.getOne(id); 
			// Povratna vrednost od getOne (nije findOne) iz JpaRepository je taj objekat sa tim id-em
	        return nadjeni;
		}

		@Override
		public List<Gledalac> findAll() {
			List<Gledalac> lista = this.gledalacRepo.findAll(); 
			// findAll je predefinisana funkcija iz JpaRepository koja vraca listu iz baze
	        return lista;
		}

		// Bila je njena metoda ali samo za update imena
		@Override
		public Gledalac updateAktivnost(Gledalac gledalac) throws Exception {
			Gledalac nadjen = this.gledalacRepo.findByUsername(gledalac.getUsername()); // Ja trazio po id pa bacalo exception
			if(nadjen.getUsername() == null) {
				throw new Exception("Gledalac ne moze da se update-uje jer ne postoji.");
			}
	        // Postavljanje nove vrednosti
	        nadjen.setAktivan(true);
	        // Cuvanje u bazi, ne mora da napravi novog lika, ali je to tu zbog debug-a u kontroleru (gogi)
	        Gledalac updatovan = this.gledalacRepo.save(nadjen);
	        System.out.println("Korisnik prijavljen, Aktivan postavljen na true"); // Za debug
	        return updatovan;
		}

		@Override
		public void delete(Long id) {
			this.gledalacRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
		}

}
