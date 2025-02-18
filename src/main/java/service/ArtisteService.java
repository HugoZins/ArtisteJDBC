package service;

import model.Artiste;
import repo.ArtisteRepository;

import java.sql.SQLException;
import java.util.List;

public class ArtisteService {

	private ArtisteRepository artisteRepository;

	public ArtisteService() {
		this.artisteRepository = new ArtisteRepository();
	}

	public ArtisteService(ArtisteRepository artisteRepository) {
		this.artisteRepository = new ArtisteRepository();
	}

	public List<Artiste> getAllArtistes() throws SQLException {
		return artisteRepository.getAll();
	}

	public void addArtiste(Artiste artiste) throws SQLException {
		artisteRepository.addArtiste(artiste);
	}

	public Artiste findByName(String nom) throws SQLException {
		return artisteRepository.findByName(nom);
	}
}
