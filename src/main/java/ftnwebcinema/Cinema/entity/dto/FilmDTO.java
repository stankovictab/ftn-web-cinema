package ftnwebcinema.Cinema.entity.dto;

public class FilmDTO {
	private Long idFilm;
	private String naziv;
	public Long getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public FilmDTO() {}
	public FilmDTO(Long id, String naziv) {
		this.idFilm = id;
		this.naziv = naziv;
	}
}
