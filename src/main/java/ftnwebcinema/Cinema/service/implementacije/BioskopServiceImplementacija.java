package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
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
	public Bioskop napravi(Bioskop bioskop) throws Exception {
		if (bioskop.getIdBioskop() != null) {
            throw new Exception("ID bioskopa koji se pravi mora da bude null."); 
        }
		Bioskop novi = this.bioskopRepo.save(bioskop); 
		
        return novi;
	}

	@Override
	public Bioskop findOne(Long id) {
		Bioskop nadjeni = this.bioskopRepo.getOne(id); 
        return nadjeni;
	}

	@Override
	public List<Bioskop> findAll() {
		List<Bioskop> lista = this.bioskopRepo.findAll(); 
        return lista;
	}

	@Override
	public Bioskop update(Bioskop bioskop) throws Exception {
		Bioskop neUpdatovan = this.bioskopRepo.getOne(bioskop.getIdBioskop());
        if (neUpdatovan == null) {
            throw new Exception("Bioskop ne moze da se update-uje jer ne postoji.");
        }

        // Postavljanje novog imena
        neUpdatovan.setNaziv(bioskop.getNaziv());

        // Cuvanje u bazi
        Bioskop updatovan = this.bioskopRepo.save(neUpdatovan);
        return updatovan;
	}

	@Override
	public void delete(Long id) {
		this.bioskopRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
	}

}
