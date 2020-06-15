package ftnwebcinema.Cinema.entity.dto;


public class BioskopDTO {
	private Long idBioskop;
	private String naziv;
	public Long getIdBioskop() {
		return idBioskop;
	}
	public void setIdBioskop(Long idBioskop) {
		this.idBioskop = idBioskop;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public BioskopDTO() {}
	public BioskopDTO(Long id, String naziv) {
		this.idBioskop = id;
		this.naziv = naziv;
	}
}
