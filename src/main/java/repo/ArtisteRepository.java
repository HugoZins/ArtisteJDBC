package repo;

import model.Artiste;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtisteRepository {

	private static final String URL = "jdbc:mysql://localhost:3306/artistes";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public void checkAndCreateTable() throws SQLException {
		String checkTableSQL = "SHOW TABLES LIKE 'artiste'";
		String createTableSQL = "CREATE TABLE artiste (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "nom VARCHAR(255) NOT NULL," + "prenom VARCHAR(255) NOT NULL," + "annee_naissance INT NOT NULL"
				+ ");";

		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(checkTableSQL)) {

			if (!rs.next()) {
				System.out.println("La table 'artiste' n'existe pas, création en cours...");
				stmt.executeUpdate(createTableSQL);
				System.out.println("Table 'artiste' créée avec succès !");
			}
		}
	}

	public List<Artiste> getAll() throws SQLException {
		String sql = "SELECT * FROM Artiste";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			List<Artiste> artistes = new ArrayList<>();
			while (rs.next()) {
				Artiste artiste = new Artiste();
				artiste.setId(rs.getInt("id"));
				artiste.setNom(rs.getString("nom"));
				artiste.setPrenom(rs.getString("prenom"));
				artiste.setAnneeNaissance(rs.getInt("annee_naissance"));
				artistes.add(artiste);
			}
			return artistes;
		}
	}

	public void addArtiste(Artiste artiste) throws SQLException {
		String sql = "INSERT INTO Artiste (nom, prenom, annee_naissance) VALUES (?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, artiste.getNom());
			pstmt.setString(2, artiste.getPrenom());
			pstmt.setInt(3, artiste.getAnneeNaissance());
			pstmt.executeUpdate();
		}
	}

	public Artiste findByName(String nom) throws SQLException {
		String sql = "SELECT * FROM Artiste WHERE nom = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nom);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Artiste artiste = new Artiste();
					artiste.setId(rs.getInt("id"));
					artiste.setNom(rs.getString("nom"));
					artiste.setPrenom(rs.getString("prenom"));
					artiste.setAnneeNaissance(rs.getInt("annee_naissance"));
					return artiste;
				}
			}
		}
		return null; // Aucun artiste trouvé
	}
}
