package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Administrator;
import ftnwebcinema.Cinema.repository.AdministratorRepo;
import ftnwebcinema.Cinema.service.AdministratorService;

@Service
public class AdministratorServiceImplementacija implements AdministratorService {
	
		// Veza ka repozitorijumu
		@Autowired 
		private AdministratorRepo administratorRepo;
		
		@Override
		public Administrator napravi(Administrator admin) throws Exception {
			if (admin.getIdAdministrator() != null) {
	            throw new Exception("ID Administratora koji se pravi mora da bude null."); // Jer mu se id dodaje posle
	        }
			Administrator novi = this.administratorRepo.save(admin); 
			// Objekat novi uzima povratnu vrednost od predefinisane funkcije save iz repozitorijuma (iz JpaRepository koju implementira) koji je taj kreiran objekat
	        return novi;
		}
		
		@Override
		public Administrator findOneById(Long id) {
			Administrator nadjeni = this.administratorRepo.getOne(id); 
			// Povratna vrednost od getOne (nije findOne) iz JpaRepository je taj objekat sa tim id-em
	        return nadjeni;
		}
		
		@Override
		public Administrator findOneByUsername(String username) {
			Administrator nadjeni = this.administratorRepo.findByUsername(username); 
	        return nadjeni;
		}
		
		@Override
		public List<Administrator> findAll() {
			List<Administrator> lista = this.administratorRepo.findAll(); 
			// findAll je predefinisana funkcija iz JpaRepository koja vraca listu iz baze
	        return lista;
		}
		
		// Bila je njena metoda ali samo za update imena
		@Override
		public Administrator updateAktivnost(Administrator administrator) throws Exception {
			Administrator nadjen = this.administratorRepo.findByUsername(administrator.getUsername()); // Ja trazio po id pa bacalo exception
			// nadjen.getUsername() baca null pointer exception, pa ne moze da se poredi sa null, ali samo nadjen == null hoce
			if(nadjen == null) {  // Ova provera je dobra, izbaca dobar exception
				throw new Exception("Administrator ne moze da se update-uje jer ne postoji.");
			}
			// Provera sifre
			if(!(nadjen.getPassword().equals(administrator.getPassword()))) { 
				// Ne moze == jer on poredi reference
				// Da nisu iste sifre -> !
				throw new Exception("Sifra se ne poklapa!");
			}
	        // Postavljanje nove vrednosti
	        nadjen.setAktivan(true);
	        // Cuvanje u bazi, ne mora da napravi novog lika, ali je to tu zbog debug-a u kontroleru (gogi)
	        Administrator updatovan = this.administratorRepo.save(nadjen);
	        System.out.println("Korisnik prijavljen, Aktivan postavljen na true"); // Za debug
	        return updatovan;
		}
		
		@Override
		public void delete(Long id) {
			this.administratorRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
		}
		
}
