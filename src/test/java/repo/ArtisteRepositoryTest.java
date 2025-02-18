package repo;

import model.Artiste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArtisteRepositoryTest {

	private ArtisteRepository artisteRepository;

	@BeforeEach
	void setUp() throws SQLException {
		artisteRepository = new ArtisteRepository();
		artisteRepository.resetDatabase();
	}

	@Test
	void testGetAll() throws SQLException {
		List<Artiste> artistes = artisteRepository.getAll();
		assertEquals(6, artistes.size(), "On doit récupérer 6 artistes de la base.");
	}

	@Test
	void testAddArtiste() throws SQLException {
		Artiste artiste = new Artiste("Molière", "Jean-Baptiste", 1622);
		artisteRepository.addArtiste(artiste);

		List<Artiste> artistes = artisteRepository.getAll();
		assertEquals(7, artistes.size(), "On doit avoir 7 artistes après l'ajout.");
	}

	@Test
	void testFindByName() throws SQLException {
		Artiste artiste = artisteRepository.findByName("Hugo");
		assertNotNull(artiste, "L'artiste Victor Hugo doit être trouvé.");
		assertEquals("Hugo", artiste.getNom());
		assertEquals("Victor", artiste.getPrenom());
	}
}
