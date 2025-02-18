package controller;

import model.Artiste;
import service.ArtisteService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ArtisteController {

	private static final ArtisteService artisteService = new ArtisteService();

	public void start() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Menu :");
			System.out.println("1. Afficher tous les artistes");
			System.out.println("2. Ajouter un artiste");
			System.out.println("3. Trouver un artiste par nom");
			System.out.println("4. Quitter");
			System.out.print("Choisissez une option : ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consommer le '\n'

			switch (choice) {
			case 1:
				afficherTousLesArtistes();
				break;
			case 2:
				ajouterArtiste(scanner);
				break;
			case 3:
				trouverArtisteParNom(scanner);
				break;
			case 4:
				System.out.println("Au revoir !");
				return;
			default:
				System.out.println("Option invalide.");
			}
		}
	}

	private static void afficherTousLesArtistes() {
		try {
			List<Artiste> artistes = artisteService.getAllArtistes();
			for (Artiste artiste : artistes) {
				System.out.println(artiste.getNom() + " " + artiste.getPrenom());
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération des artistes : " + e.getMessage());
		}
	}

	private static void ajouterArtiste(Scanner scanner) {
		System.out.print("Nom de l'artiste : ");
		String nom = scanner.nextLine();
		System.out.print("Prénom de l'artiste : ");
		String prenom = scanner.nextLine();
		System.out.print("Année de naissance de l'artiste : ");
		int anneeNaissance = scanner.nextInt();

		try {
			Artiste artiste = new Artiste(nom, prenom, anneeNaissance);
			artisteService.addArtiste(artiste);
			System.out.println("Artiste ajouté avec succès.");
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout de l'artiste : " + e.getMessage());
		}
	}

	private static void trouverArtisteParNom(Scanner scanner) {
		System.out.print("Nom de l'artiste : ");
		String nom = scanner.nextLine();

		try {
			Artiste artiste = artisteService.findByName(nom);
			if (artiste != null) {
				System.out.println("Artiste trouvé : " + artiste.getNom() + " " + artiste.getPrenom());
			} else {
				System.out.println("Aucun artiste trouvé avec ce nom.");
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la recherche de l'artiste : " + e.getMessage());
		}
	}
}
