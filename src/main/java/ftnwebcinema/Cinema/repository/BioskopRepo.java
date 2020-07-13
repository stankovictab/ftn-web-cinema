package ftnwebcinema.Cinema.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftnwebcinema.Cinema.entity.Bioskop;

public interface BioskopRepo extends JpaRepository<Bioskop, Long> {
	
	@Transactional
	@Modifying
	@Query("UPDATE Bioskop SET MENADZER_BIOSKOPA_ID_MENADZER = :idMenadzer WHERE ID_BIOSKOP = :idBioskop")
	// Bioskop je case sensitive, parametri se dodaju sa :, ne stavlja se B.
	void updateMenadzerovID (@Param("idMenadzer") Long idMenadzer, @Param("idBioskop") Long idBioskop);
	
}
