package ftnwebcinema.Cinema.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ftnwebcinema.Cinema.entity.Gledalac;

public interface GledalacRepo extends JpaRepository<Gledalac, Long>{
	
	// Dodato
	
	List<Gledalac> findAllByUsername(String username);
	
	Gledalac getOne(Long id);
}
