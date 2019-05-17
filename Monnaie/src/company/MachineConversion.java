package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineConversion {

	private static Scanner scanner = new Scanner(System.in);

	private Devise devise;
	private static final String EURO_FRANCS = "get-chef";
	private static final String FRANCS_EURO = "get-euros";
	private final String end = "end";
	private String saisie;

	private List<Integer> monnaieListUser = new ArrayList<>();

	public int conversion() {
		do {
			System.out.println("En quelle devise faire la conversion ?");
			System.out.println("\t- Si Euro vers Franc-Suisse tapez : " + EURO_FRANCS);
			System.out.println("\t- Si Franc-Suisse vers Euro tapez : " + FRANCS_EURO);
			saisie = scanner.nextLine();
		} while (!(saisie.equals(EURO_FRANCS)) && !(saisie.equals(FRANCS_EURO)));

		// Avant d'entrer dans le switch, on est sur que l'utilisateur a bien précisé la monnaie
		monnaieListUser = new ArrayList<>();
		devise = saisie.equals(EURO_FRANCS) ? new Euro() : new Franc();

		// une boucle pour introduire toutes les pieces dans la machine
		System.out.println("OK. Ce sont des " + devise.getNomDevise() + " qui vont être introduits par l’utilisateur");
		do {
			System.out.println("Veuillez introduire une piece ou un billet :");
			saisie = scanner.nextLine();

			try {
				int piece = Integer.parseInt(saisie);
				if (devise.verification(piece)) {
					// appel de la methode affichage dans l'objet pieceQuantite
					devise.affichage(piece, monnaieListUser);
					monnaieListUser.add(piece);
				} else {
					System.out.println("Mauvais montant !!!");
					System.out.println("Transaction invalidée !!!");
					afficherListe(devise.getNomDevise(), monnaieListUser);
					break;
				}
			} catch (NumberFormatException e) {
				if (saisie.equals("end")) {
					System.out.println("Conversion en cours ...");

				} else if (saisie.equals("cancel")) {
					System.out.println("Vous avez demandé l'annulation de la conversion");
					System.out.println("Transaction invalidée !!!");
					afficherListe(devise.getNomDevise(), monnaieListUser);
					break;
				} else {
					System.out.println("Symbole inconnu !!!");
					System.out.println("Transaction invalidée !!!");
					afficherListe(devise.getNomDevise(), monnaieListUser);
					break;
				}
			}

		} while (!end.equals(saisie));

		// CONVERSION A FAIRE ICI

		return 0;
	}

	// affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
	public void afficherListe(String typeMonnaie, List<Integer> list) {
		System.out.print("(" + typeMonnaie + "\t");
		list.forEach(e -> System.out.print(e + " "));
		System.out.println(")");
	}


}
