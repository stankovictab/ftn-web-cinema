package ftnwebcinema.Cinema.entity.dto;

public class AdministratorDTO {
	private Long idAdministrator;
	private String ime;
	private String prezime;
	private String uloga;
	public Long getIdAdministrator() {
		return idAdministrator;
	}
	public void setIdAdministrator(Long idAdministrator) {
		this.idAdministrator = idAdministrator;
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
	public AdministratorDTO() {}
	public AdministratorDTO(Long id, String ime, String prezime, String uloga) {
		this.idAdministrator = id;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
	}
	
}
