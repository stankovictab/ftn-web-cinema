package ftnwebcinema.Cinema.entity.dto;


public class BioskopDTO {
	private Long idBioskop;
	private String naziv;
	private String adresa;
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public BioskopDTO() {}
	public BioskopDTO(Long id, String naziv, String adresa) {
		this.idBioskop = id;
		this.naziv = naziv;
		this.adresa = adresa;
	}
}
