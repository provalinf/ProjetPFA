import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MachineConversion {
	private static Scanner scanner = new Scanner(System.in);

	private static final String EURO_FRANCS = "get-chf";
	private static final String FRANCS_EURO = "get-euros";
	private static final String END = "end";
	private static final String CANCEL = "cancel";
	private static final String FIN = "fin";
	private static final String PASSWORD = "master-players";

	private Euro euro;
	private Franc franc;

	public MachineConversion() {
		euro = new Euro();
		franc = new Franc();
	}

	public Euro getEuro() {
		return euro;
	}

	public Franc getFranc() {
		return franc;
	}

	public void conversion() {
		String saisie;
		do {
			System.out.println("En quelle devise faire la conversion ?");
			System.out.println("\t- Si Euro vers Franc-Suisse tapez : " + EURO_FRANCS);
			System.out.println("\t- Si Franc-Suisse vers Euro tapez : " + FRANCS_EURO);
			System.out.println("\t- Pour arreter la machine tapez  : " + FIN);
			saisie = scanner.nextLine();
			if (saisie.equals(FIN)) {

				System.out.println("Entrer le mot de passe ADMIN svp :");

				String password = scanner.nextLine();
				if (password.equals(PASSWORD)) {
					System.out.println();
					System.out.println("Je verifie toujours ce qu'il y a sous mon lit avant de dormir le soir :)");
					System.out.println("C'est encore pas ce projet qui nous aura fait pisser sur nos godasses !");
					System.out.println("Shutdown");
					System.exit(0);

				} else {
					System.out.println();
					System.out.println("This is not the F**Censured***G password, view the readMe file");
					System.out.println();
				}

			}

		} while (!(saisie.equals(EURO_FRANCS)) && !(saisie.equals(FRANCS_EURO)));

		// Avant d'entrer dans le switch, on est sur que l'utilisateur a bien précisé la monnaie
		List<Double> monnaieListUser = new ArrayList<>();
		Devise devise = saisie.equals(EURO_FRANCS) ? euro : franc;
		// devise inverse a celle des pieces introduites
		Devise deviseInv = saisie.equals(EURO_FRANCS) ? franc : euro;
		// une boucle pour introduire toutes les pieces dans la machine
		System.out.println("OK. Ce sont des " + devise.getNomDevise() + " qui vont être introduits par l’utilisateur");
		do {
			System.out.println("Veuillez introduire une piece ou un billet : (end pour terminer, cancel pour annuler)");
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

		// CONVERSION
		if (saisie.equals(END)) {
			double somme = monnaieListUser.stream().mapToDouble(Double::doubleValue).sum();

			if (convert(somme, deviseInv)) {
				maj_stock_pieces(devise, monnaieListUser);
			} else {
				System.out.println("Montant Indisponible");
				System.out.println("Transaction invalidée !!!");
				afficherListe(devise.getNomDevise(), monnaieListUser);
			}
		}

	}

	// affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
	protected void afficherListe(Devise.CodeISO typeMonnaie, List<Double> list) {
		System.out.print("(" + typeMonnaie + "\t");
		list.forEach(e -> {
			if (e < 1.0)
				System.out.print(e + " ");
			else
				System.out.print(e.intValue() + " ");
		});
		System.out.println(")");
	}

	// convert euro to franc
	protected boolean convert(double montant, Devise devise) {
		List<Double> result = new ArrayList<>();
		List<PieceQuantite> list = devise.getListPieces().stream().map(PieceQuantite::copyPiece).collect(Collectors.toList());
		double montantConv; // montant converti
		int nbPiecesCentimes;

		if (devise.getNomDevise() != Devise.CodeISO.EUR) {
			montantConv = montant * 1.12;
			nbPiecesCentimes = 1;
		} else {
			montantConv = montant / 1.12;
			nbPiecesCentimes = 3;
		}

		int partieEntiere = (int) montantConv;
		int partieFract = new Double(((montantConv - partieEntiere) * 100)).intValue();

		// Afficher le montant converti
		System.out.println(partieEntiere + "\t" + devise.getNomDevise() + ((partieFract == 0) ? "" : "\t" + partieFract + " Centimes"));

		PieceQuantite piece = new PieceQuantite();
		// retourne les pieces necessaires pour la partie entiere du montant converti
		for (int i = list.size() - 1; list.get(i).getSymbole() != Symbole.CENTIME; i--) {
			piece = list.get(i);

			while ((piece.getQuantite() > 0) && (partieEntiere - piece.getMontant() >= 0)) {
				partieEntiere -= piece.getMontant();
				piece.setQuantite(piece.getQuantite() - 1);
				result.add(Double.parseDouble(Integer.toString(piece.getMontant())));
			}
		}

		// traiter le reste de lapartie entiere avec des pieces de moins de 1 (des centimes)
		partieEntiere *= 100; // to match cents which are already multiplied by 100 in the reserve
		for (int i = nbPiecesCentimes - 1; i >= 0; i--) {
			piece = list.get(i);

			while ((piece.getQuantite() > 0) && ((partieEntiere - piece.getMontant()) >= 0)) {
				partieEntiere -= piece.getMontant();
				piece.setQuantite(piece.getQuantite() - 1);
				result.add(Double.parseDouble("0." + piece.getMontant()));
			}
		}

		// Montant indisponible
		if (partieEntiere != 0) return false;

		// retourne les pieces necessaires pour la partie fractionnaire du montant conerti
		for (int i = nbPiecesCentimes - 1; i >= 0; i--) {
			piece = list.get(i);

			while ((piece.getQuantite() > 0) && (partieFract - piece.getMontant() >= 0)) {
				partieFract -= piece.getMontant();
				piece.setQuantite(piece.getQuantite() - 1);
				result.add(Double.parseDouble("0." + piece.getMontant()));
			}
		}


        /*
            considérer le cas où nous sommes à moins de 2 cents du dixième d’euro supérieur
            ou à moins de 10 centimes du demi-franc suisse supérieur
         */
		switch (devise.getNomDevise()) {
			case EUR:
				// le cas où nous sommes à moins de 2 cents du dixième d’euro supérieur
				if ((piece.getMontant() - partieFract) < 2) {
					if ((piece.getMontant() - partieFract) >= 0) {
						if (piece.getQuantite() > 0) {
							piece.setQuantite(piece.getQuantite() - 1);
							result.add(Double.parseDouble("0." + piece.getMontant()));
						} else
							return false; // montant indisponible
					} else
						return false; // montant indisponible
				}
				break;
			case CHF:
				// le cas où nous sommes à moins de 10 centimes du demi-franc suisse supérieur
				if ((piece.getMontant() - partieFract) < 10) {
					if ((piece.getMontant() - partieFract) >= 0) {
						if (piece.getQuantite() > 0) {
							piece.setQuantite(piece.getQuantite() - 1);
							result.add(Double.parseDouble("0." + piece.getMontant()));
						} else
							return false; // montant indisponible
					} else
						return false; // montant indisponible

				}
				break;
		}

		// Afficher les pieces et billets rendus à l'utilisateur
		afficherListe(devise.getNomDevise(), result);
		// Mise à jour des quantités de pieces en enlevant celle qui ont été rendues à l'utilisateur
		devise.setListPieces(list);

		return true;
	}

	/*
		 rajouter les pieces introduites par l'utilisateur au stock de depart
	 */
	protected void maj_stock_pieces(Devise devise, List<Double> monnaieListUser) {

		for (Double monnaie : monnaieListUser) {
			PieceQuantite pieceQuantite = new PieceQuantite();
			pieceQuantite.setQuantite(1);

			if (monnaie >= 10.0) {
				pieceQuantite.setMontant(monnaie.intValue());
				pieceQuantite.setTypeMonnaie(TypeMonnaie.BILLET);
				pieceQuantite.setSymbole(devise.getNomDevise() == Devise.CodeISO.EUR ? Symbole.EURO : Symbole.FRANC);

			} else {
				pieceQuantite.setTypeMonnaie(TypeMonnaie.PIECE);

				if (monnaie < 1.0) {
					pieceQuantite.setSymbole(Symbole.CENTIME);
					Double montant = monnaie * 100;
					pieceQuantite.setMontant(montant.intValue());
				} else {
					pieceQuantite.setMontant(monnaie.intValue());
					switch (devise.getNomDevise()) {
						case EUR:
							if (monnaie == 5)
								pieceQuantite.setTypeMonnaie(TypeMonnaie.BILLET);
							pieceQuantite.setSymbole(Symbole.EURO);
							break;
						case CHF:
							pieceQuantite.setSymbole(Symbole.FRANC);
							break;
					}
				}
			}

			PieceQuantite p = devise.getListPieces().stream().
					filter(t -> (t.getTypeMonnaie() == pieceQuantite.getTypeMonnaie())
							&& (t.getSymbole() == pieceQuantite.getSymbole())
							&& (t.getMontant() == pieceQuantite.getMontant())).
					collect(Collectors.toList()).get(0);
			p.setQuantite(p.getQuantite() + 1);

		}
	}
}

