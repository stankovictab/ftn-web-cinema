package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnwebcinema.Cinema.entity.Bioskop;
import ftnwebcinema.Cinema.repository.BioskopRepo;
import ftnwebcinema.Cinema.service.BioskopService;

@Service
public class BioskopServiceImplementacija implements BioskopService{

	@Autowired 
	private BioskopRepo bioskopRepo;
	
	@Override
	public Bioskop napravi(Bioskop bioskop, Long idMenadzer) throws Exception {
		if (bioskop.getIdBioskop() != null) {
            throw new Exception("ID bioskopa koji se pravi mora da bude null."); 
        }
		this.bioskopRepo.save(bioskop); // Pravi id, pa ovo mora da bude ispod 
		Long tempID = bioskop.getIdBioskop();
		this.bioskopRepo.updateMenadzerovID(idMenadzer, tempID);
		// Mora da ga nadje pa da ga vrati jer ne moze samo od save, nema id menadzera, ovako ce vratiti updatovanog
		Optional<Bioskop> noviOptional = this.bioskopRepo.findById(tempID);
		Bioskop novi = noviOptional.get(); // Ovako se resava Optional, get() vraca vrednost ako nije null
        return novi;
	}

	@Override
	public Bioskop findOne(Long id) {
		Bioskop nadjeni = this.bioskopRepo.getOne(id); 
        return nadjeni;
	}
	
	@Override
	public Bioskop findByNaziv(String naziv) {
		Bioskop nadjeni = this.bioskopRepo.findByNaziv(naziv); 
        return nadjeni;
	}

	@Override
	public List<Bioskop> findAll() {
		List<Bioskop> lista = this.bioskopRepo.findAll(); 
        return lista;
	}

	@Override
	public Bioskop updateNaziv(Bioskop bioskop, String noviNaziv) throws Exception {
		Bioskop neUpdatovan = this.bioskopRepo.getOne(bioskop.getIdBioskop());
        if (neUpdatovan == null) {
            throw new Exception("Bioskop ne moze da se update-uje jer ne postoji.");
        }

        // Postavljanje novog imena
        neUpdatovan.setNaziv(noviNaziv);

        // Cuvanje u bazi
        Bioskop updatovan = this.bioskopRepo.save(neUpdatovan);
        return updatovan;
	}

	@Override
	public void delete(Long id) {
		this.bioskopRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
	}

}
