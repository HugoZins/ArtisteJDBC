package main;

import controller.ArtisteController;
import repo.ArtisteRepository;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		ArtisteRepository artisteRepository = new ArtisteRepository();

		try {

			artisteRepository.checkAndCreateTable();

			ArtisteController controller = new ArtisteController();
			controller.start();

		} catch (SQLException e) {
			System.out.println("Erreur lors de la vérification de la table ou de la création : " + e.getMessage());
		}
	}
}
