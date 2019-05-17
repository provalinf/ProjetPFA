package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineConversion {
	private static Scanner scanner = new Scanner(System.in);

	private static final String EURO_FRANCS = "get-chf";
	private static final String FRANCS_EURO = "get-euros";
	private static final String END = "end";
	private static final String CANCEL = "cancel";

	public void conversion() {
		String saisie;
		do {
			System.out.println("En quelle devise faire la conversion ?");
			System.out.println("\t- Si Euro vers Franc-Suisse tapez : " + EURO_FRANCS);
			System.out.println("\t- Si Franc-Suisse vers Euro tapez : " + FRANCS_EURO);
			saisie = scanner.nextLine();
		} while (!(saisie.equals(EURO_FRANCS)) && !(saisie.equals(FRANCS_EURO)));

		// Avant d'entrer dans le switch, on est sur que l'utilisateur a bien précisé la monnaie
		List<Double> monnaieListUser = new ArrayList<>();
		Devise devise = saisie.equals(EURO_FRANCS) ? new Euro() : new Franc();

		// une boucle pour introduire toutes les pieces dans la machine
		System.out.println("OK. Ce sont des " + devise.getNomDevise() + " qui vont être introduits par l’utilisateur");
		do {
			System.out.println("Veuillez introduire une piece ou un billet :");
			saisie = scanner.nextLine();

			try {
				if (devise.verification(saisie)) {
					// appel de la methode affichageEtAjout dans l'objet pieceQuantite
					devise.affichageEtAjout(Double.parseDouble(saisie), monnaieListUser);
				} else {
					System.out.println("Mauvais montant !!!");
					System.out.println("Transaction invalidée !!!");
					afficherListe(devise.getNomDevise(), monnaieListUser);
					break;
				}
			} catch (NumberFormatException e) {
				if (saisie.equals(END)) {
					System.out.println("Conversion en cours ...");

				} else if (saisie.equals(CANCEL)) {
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

		} while (!saisie.equals(END) && !saisie.equals(CANCEL));

		// CONVERSION A FAIRE ICI
	}

	// affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
	private void afficherListe(String typeMonnaie, List<Double> list) {
		System.out.print("(" + typeMonnaie + "\t");
		list.forEach(e -> System.out.print(e + " "));
		System.out.println(")");
	}


}
