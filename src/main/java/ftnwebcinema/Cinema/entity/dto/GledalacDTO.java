package ftnwebcinema.Cinema.entity.dto;

public class GledalacDTO {
	
	private Long idGledalac;
	private String ime;
	private String prezime;
	private String uloga;
	
	public Long getIdGledalac() {
		return idGledalac;
	}
	public void setIdGledalac(Long idGledalac) {
		this.idGledalac = idGledalac;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public GledalacDTO() {}
	public GledalacDTO(Long id, String ime, String prezime, String uloga) {
		this.idGledalac = id;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
	}
	
	
	
	
	
}
