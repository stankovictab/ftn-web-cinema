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
		public Administrator findOne(Long id) {
			Administrator nadjeni = this.administratorRepo.getOne(id); 
			// Povratna vrednost od getOne (nije findOne) iz JpaRepository je taj objekat sa tim id-em
	        return nadjeni;
		}
		
		@Override
		public List<Administrator> findAll() {
			List<Administrator> lista = this.administratorRepo.findAll(); 
			// findAll je predefinisana funkcija iz JpaRepository koja vraca listu iz baze
	        return lista;
		}
		
		// Ova metoda menja samo ime korisnika
		@Override
		public Administrator update(Administrator admin) throws Exception {
			Administrator neUpdatovan = this.administratorRepo.getOne(admin.getIdAdministrator());
	        if (neUpdatovan == null) {
	            throw new Exception("Administrator ne moze da se update-uje jer ne postoji.");
	        }

	        // Postavljanje novog imena
	        neUpdatovan.setIme(admin.getIme());

	        // Cuvanje u bazi
	        Administrator updatovan = this.administratorRepo.save(neUpdatovan);
	        return updatovan;
		}
		
		@Override
		public void delete(Long id) {
			this.administratorRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
		}
		
}
